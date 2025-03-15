package handbuilt;

import ast.*;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;
import java.io.IOException;

public class Ex_c {

    public static Program buildAST() {
        // 1. Global variable declarations
        List<VarDecl> decls = new ArrayList<>();
        decls.add(new VarDecl(new TypeInt(), "count"));

        // 2. Statements in the main program body
        List<Stm> stms = new ArrayList<>();

        // Statement: count = 3;
        stms.add(new StmAssign("count", new ExpInt(3)));

        // Build the while loop condition: 0 < (count + 1)
        Exp condition = new ExpLessThan(
                new ExpInt(0),
                new ExpPlus(new ExpVar("count"), new ExpInt(1))
        );

        // Build the while loop body:
        List<Stm> whileBody = new ArrayList<>();
        // Statement: printch 32;  (prints the character with code 32, i.e. a space)
        whileBody.add(new StmPrintChar(new ExpInt(32)));
        // Statement: print count; (prints the current value of count)
        whileBody.add(new StmPrint(new ExpVar("count")));
        // Statement: count = count - 1;
        whileBody.add(new StmAssign("count", new ExpMinus(new ExpVar("count"), new ExpInt(1))));

        // Wrap the while body statements in a block
        StmBlock whileBlock = new StmBlock(whileBody);

        // Create the while statement (using the two-argument constructor)
        Stm whileStmt = new StmWhile(condition, whileBlock);
        stms.add(whileStmt);

        return new Program(decls, stms);
    }

    public static void main(String[] args) {
        Program program = buildAST();
        System.out.println(program); // Pretty-print the AST
        program.compile();
        try {
            AST.write(Paths.get("ex_c.ssma"));
            System.out.println("Compilation complete. Code written to ex_c.ssma");
        } catch (IOException e) {
            System.err.println("Error writing SSM code: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
