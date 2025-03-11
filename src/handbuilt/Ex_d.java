package handbuilt;

import ast.*;
import java.util.ArrayList;
import java.util.List;

public class Ex_d {

    public static Program buildAST() {
        // Declare variable x of type int
        TypeInt intType = new TypeInt();
        VarDecl varX = new VarDecl(intType, "x");

        // Create a list for variable declarations
        List<VarDecl> varDecls = new ArrayList<>();
        varDecls.add(varX);

        // Create expressions for the assignments and conditionals
        ExpVar varXExp = new ExpVar("x");
        ExpInt int20 = new ExpInt(20);
        ExpInt int7 = new ExpInt(7);
        ExpInt int30 = new ExpInt(30);
        ExpInt int77 = new ExpInt(77);
        ExpInt int88 = new ExpInt(88);

        // Assign x = 20
        StmAssign assignX = new StmAssign("x", int20); // x = 20

        // If statement: if (x < 20)
        ExpLessThan condition1 = new ExpLessThan(varXExp, int20); // x < 20
        StmAssign assignXAfterIf = new StmAssign("x", new ExpMinus(varXExp, int7)); // x = x - 7

        // Wrap the first statement (x = x - 7) inside a block (curly braces)
        List<Stm> blockStatements = new ArrayList<>();
        blockStatements.add(assignXAfterIf);
        StmBlock blockAfterIf = new StmBlock(blockStatements); // x = x - 7 wrapped inside a block

        // Second if-else for println 77 or 88
        StmPrintln print77 = new StmPrintln(int77); // println 77
        StmPrintln print88 = new StmPrintln(int88); // println 88
        ExpLessThan condition2 = new ExpLessThan(varXExp, int30); // x < 30
        StmIf innerIf = new StmIf(condition2, print77, print88); // if (x < 30) println 77 else println 88

        // Final print: println x
        StmPrintln printX = new StmPrintln(varXExp); // println x

        // Wrap the inner statements in a StmBlock for else (curly braces for the else)
        List<Stm> innerStatements = new ArrayList<>();
        innerStatements.add(innerIf);
        StmBlock blockInner = new StmBlock(innerStatements);

        // Create the outer if statement: if (x < 20) { ... } else { ... }
        StmIf outerIf = new StmIf(condition1, blockAfterIf, blockInner); // if (x < 20) { ... } else { ... }

        // Create the list of statements
        List<Stm> statements = new ArrayList<>();
        statements.add(assignX); // x = 20
        statements.add(outerIf); // outer if statement
        statements.add(printX); // println x

        // Return the program with variable declarations and statements
        return new Program(varDecls, statements);
    }

    public static void main(String[] args) {
        Program program = buildAST();
        System.out.println(program);  // Print the AST structure
    }
}
