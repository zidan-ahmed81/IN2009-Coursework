package ast;

import compile.SymbolTable;

public class StmWhile extends Stm {
    public final Exp condition;
    public final Stm body;

    public StmWhile(Exp condition, Stm body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void compile(SymbolTable st) {
        String startLabel = st.freshLabel("whileStart");
        String exitLabel = st.freshLabel("whileExit");

        // Emit the start label where the condition is evaluated.
        emit(startLabel + ":");

        // Compile the condition: it should leave a value on the stack.
        condition.compile(st);

        // Push the exit label address (symbolic label, resolved later) onto the stack.
        emit("push " + exitLabel);

        // Emit the conditional jump: if the condition is zero, jump to exit.
        emit("jump_z");

        // Compile the loop body.
        body.compile(st);

        // Unconditionally jump back to the start: push the start label address and jump.
        emit("push " + startLabel);
        emit("jump");

        // Emit the exit label.
        emit(exitLabel + ":");
    }


    @Override
    public <T> T accept(ast.util.Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
