package handbuilt;

import ast.*;
import compile.SymbolTable;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ex_a {

    public static Program buildAST() {
        List<VarDecl> varDecls = new ArrayList<>();
        varDecls.add(new VarDecl(new TypeInt(), "x"));

        List<Stm> stms = new ArrayList<>();
        stms.add(new StmAssign("x", new ExpInt(3)));
        stms.add(new StmPrint(new ExpTimes(new ExpVar("x"), new ExpInt(9))));

        return new Program(varDecls, stms);
    }

    public static void main(String[] args) {
        // Build the AST
        Program program = buildAST();
        System.out.println(program); // for debugging

        // Compile the program (assumes compile() takes no arguments)
        program.compile();

        // Write the generated code to ex_a.ssma, handling potential IOException
        try {
            AST.write(Paths.get("ex_a.ssma"));
            System.out.println("Compilation complete. Code written to ex_a.ssma");
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
