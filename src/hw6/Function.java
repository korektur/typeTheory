package hw6;

import hw1.Expression;

import java.util.List;

public class Function implements Expression{
    String name;
    List<Expression> expressions;

    public Function(String name, List<Expression> expressions) {
        this.name = name;
        this.expressions = expressions;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(name);
        res.append('(');
        for (Expression e : expressions) {
            res.append(e.toString()).append(',');
        }
        res.replace(res.length() - 1, res.length(), ")");
        return res.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Function) {
            Function other = (Function) o;
            return name.equals(other.name) && expressions.size() == other.expressions.size();
        }
        return false;
    }
}
