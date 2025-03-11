package handbuilt;

import ast.*;
import java.util.ArrayList;
import java.util.List;

public class Ex_b {

    public static Program buildAST() {
        // Declare variable types
        TypeInt intType = new TypeInt();

        // Declare variables: part and y
        VarDecl varPart = new VarDecl(intType, "part");
        VarDecl varY = new VarDecl(intType, "y");

        List<VarDecl> varDecls = new ArrayList<>();
        varDecls.add(varPart);
        varDecls.add(varY);

        // Create variable expressions
        ExpVar varPartExp = new ExpVar("part");
        ExpVar varYExp = new ExpVar("y");

        // part = 6 + y;
        ExpInt int6 = new ExpInt(6);
        ExpPlus sumExp = new ExpPlus(int6, varYExp);
        StmAssign assignPart = new StmAssign("part", sumExp);

        // y = part - 3;
        ExpInt int3 = new ExpInt(3);
        ExpMinus subtractExp = new ExpMinus(varPartExp, int3);
        StmAssign assignY = new StmAssign("y", subtractExp);

        // printch (part + y) * 10;
        ExpPlus sumPartY = new ExpPlus(varPartExp, varYExp);
        ExpTimes finalExp = new ExpTimes(sumPartY, new ExpInt(10));
        StmPrintChar printStatement = new StmPrintChar(finalExp);

        // Collect all statements
        List<Stm> statements = new ArrayList<>();
        statements.add(assignPart);
        statements.add(assignY);
        statements.add(printStatement);

        return new Program(varDecls, statements);
    }

    public static void main(String[] args) {
        Program program = buildAST();
        System.out.println(program);
    }
}
