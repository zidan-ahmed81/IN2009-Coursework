package handbuilt;

import ast.*;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;
import java.io.IOException;

public class Ex_d {

    public static Program buildAST() {
        // Declarations: int x;
        List<VarDecl> decls = new ArrayList<>();
        decls.add(new VarDecl(new TypeInt(), "x"));

        // Main body statements
        List<Stm> stms = new ArrayList<>();

        // x = 20;
        stms.add(new StmAssign("x", new ExpInt(20)));

        // if (x < 20) { x = x - 7; } else { if (x < 30) println 77; else println 88; }
        // 1) Outer if condition
        Exp condition1 = new ExpLessThan(new ExpVar("x"), new ExpInt(20));

        // 2) Then block: { x = x - 7; }
        List<Stm> thenStms1 = new ArrayList<>();
        thenStms1.add(new StmAssign("x", new ExpMinus(new ExpVar("x"), new ExpInt(7))));
        Stm thenBlock1 = new StmBlock(thenStms1);

        // 3) Else block: { if (x < 30) println 77; else println 88; }
        //    Nested if:
        Exp condition2 = new ExpLessThan(new ExpVar("x"), new ExpInt(30));
        Stm thenBranch2 = new StmPrintln(new ExpInt(77));
        Stm elseBranch2 = new StmPrintln(new ExpInt(88));
        Stm nestedIf = new StmIf(condition2, thenBranch2, elseBranch2);

        // Wrap the nested if in a block as well
        List<Stm> elseStms1 = new ArrayList<>();
        elseStms1.add(nestedIf);
        Stm elseBlock1 = new StmBlock(elseStms1);

        // 4) Outer if statement
        Stm ifStatement = new StmIf(condition1, thenBlock1, elseBlock1);
        stms.add(ifStatement);

        // println x;
        stms.add(new StmPrintln(new ExpVar("x")));

        // Build and return the complete Program node
        return new Program(decls, stms);
    }

    public static void main(String[] args) {
        // Build the AST for Ex_d
        Program program = buildAST();
        System.out.println(program);
        program.compile();
        try {
            AST.write(Paths.get("ex_d.ssma"));
            System.out.println("Compilation complete. Code written to ex_d.ssma");
        } catch (IOException e) {
            System.err.println("Error writing SSM code: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
