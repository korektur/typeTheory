package hw1;

import java.util.*;

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
            while (s.charAt(i) != '.') {
                sb.append(s.charAt(i));
                ++i;
            }
            return new Abstraction(new Variable(sb.toString()), parseAbstraction(i + 1, end));
        }
        return parseUsage(begin, end);
    }

    private Expression parseUsage(int begin, int end) {
        int index = begin;
        Deque<Expression> expressions = new ArrayDeque<Expression>();
        while (index < end) {
            if (s.charAt(index) == '(') {
                int balance = 1;
                int i = index + 1;
                while (balance != 0) {
                    if (s.charAt(i) == '(') ++balance;
                    else if (s.charAt(i) == ')') --balance;
                    ++i;
                }
                expressions.add(parseAbstraction(index + 1, i));
                index = i + 1;
                if (index < end && s.charAt(index) == ' ') index++;
                continue;
            }
            StringBuilder sb = new StringBuilder();
            while (index < end && (Character.isAlphabetic(s.charAt(index)) ||
                    Character.isDigit(s.charAt(index)) || s.charAt(index) == '`')) {
                sb.append(s.charAt(index++));
            }
            expressions.add(new Variable(sb.toString()));
            if (index < end && (s.charAt(index) == ' ' || s.charAt(index) == ')')) index++;
        }
        while(expressions.size() > 1) {
            Expression left = expressions.pollFirst();
            Expression right = expressions.pollFirst();
            expressions.addFirst(new Term(left, right));
        }
        return expressions.poll();
    }
}
