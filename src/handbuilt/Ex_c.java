package handbuilt;

import ast.*;
import java.util.ArrayList;
import java.util.List;

public class Ex_c {

    public static Program buildAST() {
        // Declare variable type
        TypeInt intType = new TypeInt();

        // Declare variables: count
        VarDecl varCount = new VarDecl(intType, "count");

        List<VarDecl> varDecls = new ArrayList<>();
        varDecls.add(varCount);

        // Create variable expression for count
        ExpVar varCountExp = new ExpVar("count");

        // Assign count = 3
        StmAssign assignCount = new StmAssign("count", new ExpInt(3));

        // Create the condition of the while loop: 0 < (count + 1)
        ExpInt zero = new ExpInt(0);
        ExpPlus countPlusOne = new ExpPlus(varCountExp, new ExpInt(1));
        ExpLessThan condition = new ExpLessThan(zero, countPlusOne);

        // Inside the loop: printch 32, print count, count = count - 1
        StmPrintChar printChar = new StmPrintChar(new ExpInt(32));
        StmPrint printCount = new StmPrint(varCountExp);
        ExpMinus countMinusOne = new ExpMinus(varCountExp, new ExpInt(1));
        StmAssign decrementCount = new StmAssign("count", countMinusOne);

        // Group all statements inside the while loop into a block
        List<Stm> bodyStatements = new ArrayList<>();
        bodyStatements.add(printChar);
        bodyStatements.add(printCount);
        bodyStatements.add(decrementCount);

        // Wrap the body statements in a block
        StmBlock whileBody = new StmBlock(bodyStatements);

        // Create the while statement
        StmWhile whileStmt = new StmWhile(condition, whileBody);

        // Create list of all statements in the program
        List<Stm> statements = new ArrayList<>();
        statements.add(assignCount);
        statements.add(whileStmt);

        return new Program(varDecls, statements);
    }

    public static void main(String[] args) {
        Program program = buildAST();
        System.out.println(program);  // Print the AST structure
    }
}
