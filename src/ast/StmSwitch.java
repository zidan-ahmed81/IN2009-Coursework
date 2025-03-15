package ast;

import compile.SymbolTable;
import java.util.ArrayList;
import java.util.List;

public class StmSwitch extends Stm {

    public final Exp caseExp;
    public final Stm defaultCase;
    public final List<Case> cases;

    public StmSwitch(Exp caseExp, Stm defaultCase, List<Case> cases) {
        this.caseExp = caseExp;
        this.defaultCase = defaultCase;
        this.cases = cases;
    }

    @Override
    public void compile(SymbolTable st) {
        // Evaluate the switch expression and store it in a temporary variable.
        caseExp.compile(st);
        String tempLabel = st.freshLabel("switchTemp");
        // Store the switch value into a temporary location.
        emit("storei " + tempLabel);

        // Generate labels for the default case and for the exit of the switch.
        String defaultLabel = st.freshLabel("default");
        String exitLabel = st.freshLabel("switchExit");

        // Prepare a list for case labels.
        List<String> caseLabels = new ArrayList<>();
        for (Case c : cases) {
            String caseLabel;
            if (c.caseNumber < 0) {
                caseLabel = st.freshLabel("case_neg" + (-c.caseNumber));
            } else {
                caseLabel = st.freshLabel("case" + c.caseNumber);
            }
            caseLabels.add(caseLabel);

            // For each case, reload the switch value and compare it to the case literal.
            emit("loadi " + tempLabel);         // Reload the switch value.
            emit("push " + c.caseNumber);         // Push the case literal.
            emit("sub");                         // Compute (switch - literal)
            emit("test_z");                      // If (switch - literal)==0, pushes 1; else 0.
            emit("push 1");                      // Push 1.
            emit("sub");                         // Now, if equal: 1-1 = 0; if not: 0-1 = -1.
            // If the result is zero (match), jump to this case.
            emit("jumpi_z " + caseLabel);
            // (No pop needed because we reload the switch value for each case.)
        }

        // If none of the cases matched, reload the switch value and jump to the default.
        emit("loadi " + tempLabel);
        emit("jumpi " + defaultLabel);

        // Emit code for each case.
        for (int i = 0; i < cases.size(); i++) {
            String caseLabel = caseLabels.get(i);
            emit(caseLabel + ":");
            // Compile the statement for this case.
            cases.get(i).stm.compile(st);
            // After executing a case, jump to the exit.
            emit("jumpi " + exitLabel);
        }

        // Emit default case.
        emit(defaultLabel + ":");
        defaultCase.compile(st);

        // Emit exit label.
        emit(exitLabel + ":");
    }

    @Override
    public <T> T accept(ast.util.Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public static class Case {
        public final int caseNumber;
        public final Stm stm;
        public String label; // Optionally record the label if needed.

        public Case(int caseNumber, Stm stm) {
            this.caseNumber = caseNumber;
            this.stm = stm;
        }
    }
}
