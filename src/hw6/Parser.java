package hw6;

import hw1.Expression;
import hw1.Variable;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    String s;
    Expression cur;

    public Parser(String s) {
        cur = null;
        this.s = s.replaceAll("[[\\s]]", "");
    }


    private Expression parse() {
        int equals_index = s.indexOf('=');
        return new Equals(parseTerm(0, equals_index), parseTerm(equals_index + 1, s.length()));
    }

    private Expression parseTerm(int begin, int end) {
        int index = begin;
        if (s.charAt(index) >= 'a' && s.charAt(index) <= 'h') {
            StringBuilder name = new StringBuilder();
            while (s.charAt(index) != '(') {
                name.append(s.charAt(index++));
            }
            List<Expression> expressions = new ArrayList<>();
            ++index;
            int balance = 0;
            int start = index;
            while(index < end) {
                if ((s.charAt(index) == ',' || s.charAt(index) == ')') && balance == 0) {
                    expressions.add(parseTerm(start, index));
                    start = index + 1;
                } else if (s.charAt(index) == '(') {
                    ++balance;
                } else if (s.charAt(index) == ')') {
                    --balance;
                }
                ++index;
            }
            return new Function(name.toString(), expressions);
        }
        return new Variable(s.substring(begin, end));
    }

}
