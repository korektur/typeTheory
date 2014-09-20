package hw6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("task6.in"));
        PrintWriter out = new PrintWriter("task6.out");

        out.close();
        in.close();
    }

}
