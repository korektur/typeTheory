package hw5;

import hw1.Expression;
import hw1.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("task5.in"));
        PrintWriter out = new PrintWriter("task5.out");
        Parser parser = new Parser(in.readLine());
        MakeSKI ski = new MakeSKI(parser.parse());
        Expression res = ski.make();
        out.println(res.toString());
        out.close();
    }
}
