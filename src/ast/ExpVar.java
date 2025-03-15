package ast;

import compile.SymbolTable;

public class ExpVar extends Exp {
    public final String varName;

    public ExpVar(String varName) {
        this.varName = varName;
    }

    @Override
    public void compile(SymbolTable st) {
        // This forces an exception if the variable is not declared.
        st.getVarType(varName);
        emit("loadi " + SymbolTable.makeVarLabel(varName));
    }

    @Override
    public <T> T accept(ast.util.Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
