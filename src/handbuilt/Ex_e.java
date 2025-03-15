package handbuilt;

import ast.*;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;
import java.io.IOException;

public class Ex_e {

    public static Program buildAST() {
        // Global variable declarations for x and zz.
        List<VarDecl> decls = new ArrayList<>();
        decls.add(new VarDecl(new TypeInt(), "x"));
        decls.add(new VarDecl(new TypeInt(), "zz"));

        // Statements in the program body.
        List<Stm> stms = new ArrayList<>();

        // x = x - 1;
        stms.add(new StmAssign("x", new ExpMinus(new ExpVar("x"), new ExpInt(1))));

        // zz = 55;
        stms.add(new StmAssign("zz", new ExpInt(55)));

        // Build the switch statement on x.
        // The switch expression is simply x.
        Exp switchExp = new ExpVar("x");

        // Build the list of cases.
        List<StmSwitch.Case> cases = new ArrayList<>();
        // case 7: println 99;
        cases.add(new StmSwitch.Case(7, new StmPrintln(new ExpInt(99))));
        // case -1: println (x + zz);
        cases.add(new StmSwitch.Case(-1, new StmPrintln(new ExpPlus(new ExpVar("x"), new ExpVar("zz")))));

        // Default case: println x;
        Stm defaultCase = new StmPrintln(new ExpVar("x"));

        // Create the switch statement.
        stms.add(new StmSwitch(switchExp, defaultCase, cases));

        // Build and return the complete Program AST.
        return new Program(decls, stms);
    }

    public static void main(String[] args) {
        // Build the AST for Ex_e
        Program program = buildAST();
        System.out.println(program);
        program.compile();
        try {
            AST.write(Paths.get("ex_e.ssma"));
            System.out.println("Compilation complete. Code written to ex_e.ssma");
        } catch (IOException e) {
            System.err.println("Error writing SSM code: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
