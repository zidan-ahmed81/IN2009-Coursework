package handbuilt;

import ast.*;
import java.util.ArrayList;
import java.util.List;

public class Ex_e {

    public static Program buildAST() {
        // Declare variables (not as expressions)
        Type intType = new TypeInt(); // Assuming TypeInt class exists to represent integer types
        VarDecl varX = new VarDecl(intType, "x");
        VarDecl varZZ = new VarDecl(intType, "zz");

        // Create a list for variable declarations
        List<VarDecl> varDecls = new ArrayList<>();
        varDecls.add(varX);
        varDecls.add(varZZ);

        // Create expressions for the assignments and the switch
        ExpVar varXExp = new ExpVar("x");
        ExpVar varZZExp = new ExpVar("zz");  // Added to represent zz
        ExpInt int1 = new ExpInt(1);
        ExpInt int55 = new ExpInt(55);
        ExpInt int7 = new ExpInt(7);
        ExpInt intMinus1 = new ExpInt(-1);
        ExpInt int99 = new ExpInt(99);

        // Assignment: x = x - 1
        StmAssign assignX = new StmAssign("x", new ExpMinus(varXExp, int1)); // x = x - 1

        // Assignment: zz = 55
        StmAssign assignZZ = new StmAssign("zz", int55); // zz = 55

        // Create statements for the print actions (println)
        StmPrintln print99 = new StmPrintln(int99); // println 99
        StmPrintln printXPlusZZ = new StmPrintln(new ExpPlus(varXExp, varZZExp)); // println x + zz
        StmPrintln printX = new StmPrintln(varXExp); // println x

        // Switch statement
        List<StmSwitch.Case> cases = new ArrayList<>();
        cases.add(new StmSwitch.Case(7, print99)); // case 7: println 99
        cases.add(new StmSwitch.Case(-1, printXPlusZZ)); // case -1: println x + zz

        // Create default case for switch
        StmSwitch switchStm = new StmSwitch(varXExp, printX, cases); // switch (x) { case 7: ... case -1: ... default: ... }

        // Create the list of statements
        List<Stm> statements = new ArrayList<>();
        statements.add(assignX); // x = x - 1
        statements.add(assignZZ); // zz = 55
        statements.add(switchStm); // switch statement

        // Return the program with variable declarations and statements
        return new Program(varDecls, statements);
    }

    public static void main(String[] args) {
        Program program = buildAST();
        System.out.println(program);  // Print the AST structure
    }
}
