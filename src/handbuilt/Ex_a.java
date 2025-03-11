package handbuilt;

import ast.*;
import java.util.ArrayList;
import java.util.List;

public class Ex_a {

    public static Program buildAST() {
        TypeInt intType = new TypeInt();

        // Declare a variable "x" of type int
        VarDecl varDecl = new VarDecl(intType,"x");

        List<VarDecl> varDecls = new ArrayList<>();
        varDecls.add(varDecl);  // Add variable declaration to list

        // Create expressions for initialization
        ExpVar varX = new ExpVar("x");  // Referencing the variable "x"
        ExpInt int3 = new ExpInt(3);    // Integer literal 3

        // Assignment statement: x = 3
        StmAssign assignX = new StmAssign("x", int3);

        // Create the multiplication expression: x * 9
        ExpInt int9 = new ExpInt(9);    // Integer literal 9
        ExpTimes multExp = new ExpTimes(varX, int9); // Multiply x by 9

        // Create a print statement to print the result of x * 9
        StmPrint print = new StmPrint(multExp); // Print the multiplication result

        // List of statements to be executed
        List<Stm> statements = new ArrayList<>();
        statements.add(assignX);  // Add assignment to list of statements
        statements.add(print);    // Add print statement to list

        // Create and return the full program with the variable declarations and statements
        return new Program(varDecls, statements);
    }

    public static void main(String[] args) {
        Program program = buildAST();

        // Print the AST structure, which will show how the program is built
        System.out.println(program);  // Debugging output
    }
}
