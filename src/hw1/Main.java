package hw1;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("task1.in"));
        String s = in.readLine();
        Parser parser = new Parser(s);
        Expression expr = parser.parse();
        PrintWriter out = new PrintWriter(new FileWriter("task2.in"));
        out.println(expr.toString());
    }
}
