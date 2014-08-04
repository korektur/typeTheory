package hw3;

import hw1.Expression;
import hw1.Parser;
import hw1.Variable;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("task3.in"));
        PrintWriter out = new PrintWriter(new FileWriter("task3.out"));
        String s = in.readLine();
        int index = s.indexOf('[');
        Parser parser = new Parser(s.substring(0, index));
        Expression expr = parser.parse();
        int index1 = s.indexOf(":=");
        Variable v = new Variable(s.substring(index + 1, index1));
        parser = new Parser(s.substring(index1 + 2, s.length()));
        Expression toInsert = parser.parse();
        Exchanger exchanger = new Exchanger(expr, v, toInsert);
        try {
            Expression res = exchanger.exchange();
            out.println(res);
        } catch (IllegalArgumentException e) {
            out.println(e.getMessage());
        } finally {
            out.close();
        }
    }
}
