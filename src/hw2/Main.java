package hw2;

import hw1.Expression;
import hw1.Parser;
import hw1.Variable;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("task2.in"));
        String s = in.readLine();
        Parser parser = new Parser(s);
        Expression expr = parser.parse();
        List<Variable> freeVariables = Utils.getFreeVariables(expr);
        Collections.sort(freeVariables);
        PrintWriter out = new PrintWriter(new FileWriter("task2.out"));
        freeVariables.forEach(out::println);
        out.close();
    }
}
