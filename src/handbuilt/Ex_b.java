package handbuilt;

import ast.*;
import compile.SymbolTable;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ex_b {

    public static Program buildAST() {
        // Create variable declarations for "part" and "y"
        List<VarDecl> decls = new ArrayList<>();
        decls.add(new VarDecl(new TypeInt(), "part"));
        decls.add(new VarDecl(new TypeInt(), "y"));

        // Create a list for statements
        List<Stm> stms = new ArrayList<>();

        // Statement: part = 6 + y;
        // Build the expression 6 + y as ExpPlus(ExpInt(6), ExpVar("y"))
        stms.add(new StmAssign("part", new ExpPlus(new ExpInt(6), new ExpVar("y"))));

        // Statement: y = part - 3;
        // Build the expression part - 3 as ExpMinus(ExpVar("part"), ExpInt(3))
        stms.add(new StmAssign("y", new ExpMinus(new ExpVar("part"), new ExpInt(3))));

        // Statement: printch (part + y) * 10;
        // Build the expression (part + y) * 10:
        // First, (part + y) as ExpPlus(ExpVar("part"), ExpVar("y"))
        // Then, multiply by 10 using ExpTimes(...)
        stms.add(new StmPrintChar(new ExpTimes(new ExpPlus(new ExpVar("part"), new ExpVar("y")), new ExpInt(10))));

        // Return a new Program node constructed from declarations and statements.
        return new Program(decls, stms);
    }

    public static void main(String[] args) {
        // Build the AST for Ex_b
        Program program = buildAST();

        // Optionally print the AST for debugging/inspection
        System.out.println(program);

        // Compile the program (make sure your compile methods in each AST node are implemented correctly)
        program.compile();

        // Write the generated SSM code to a file named "ex_b.ssma"
        try {
            AST.write(Paths.get("ex_b.ssma"));
            System.out.println("Compilation complete. Code written to ex_b.ssma");
        } catch (IOException e) {
            System.err.println("Error writing SSM code to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
