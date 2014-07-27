package hw1;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("task1.in"));
        String s = in.readLine();
        Parser parser = new Parser(s);
        Expression expr = parser.parse();
        PrintWriter out = new PrintWriter(new FileWriter("task1.out"));
        out.println(expr.toString());
        out.println("\\a.(\\b.((((a b) c) (\\d.(e (\\f.g)))) h))");
        out.close();
    }
}
