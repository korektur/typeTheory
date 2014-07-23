package hw1;

import hw1.Abstraction;
import hw1.Expression;

import java.util.ArrayList;

public class Parser {
    String s;
    Expression cur;

    public Parser(String s) {
        this.s = s;
        cur = null;
    }
    public Expression parse() {
        return parseAbstraction(0, s.length());
    }

    private Expression parseAbstraction(int begin, int end) {
        if (s.charAt(begin) == '\\') {
            int i = begin + 1;
            StringBuilder sb = new StringBuilder("");
            while(s.charAt(i) != '.') {
                sb.append(s.charAt(i));
                ++i;
            }
            return new Abstraction(new Variable(sb.toString()), parseAbstraction(i + 1, end));
        }
        return parseTerm(begin, end);
    }

    private Expression parseTerm(int begin, int end) {
        int index = begin;
        ArrayList<Expression> terms = new ArrayList<Expression>();
        while(index != end) {
            if (s.charAt(index) == '(') {
                int i = index;
                while (s.charAt(i) != ')') ++i;
                terms.add(parseAbstraction(begin + 1, i));
                index = i + 1;
                continue;
            }
            StringBuilder sb = new StringBuilder();
            while(index != end && s.charAt(index) != '(') {
                sb.append(s.charAt(index++));
            }
            terms.add(new Variable(sb.toString()));
        }
        return new Usage(terms);
    }

}
