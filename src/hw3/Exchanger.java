package hw3;

import hw1.Abstraction;
import hw1.Expression;
import hw1.Usage;
import hw1.Variable;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Exchanger {

    private Expression expr;
    private Variable v;
    private Expression toInsert;
    private Set<Expression> vars;

    public Exchanger(Expression expr, Variable v, Expression toInsert) {
        this.expr = expr;
        this.v = v;
        this.toInsert = toInsert;
        vars = getVariables(toInsert);
    }

    public Expression exchange() throws IllegalArgumentException {
        return exchange(expr, new ArrayList<>());
    }

    public static Set<Expression> getVariables(Expression expr) {
        return getVariables(expr, new TreeSet<>());
    }

    private static Set<Expression> getVariables(Expression expr, Set<Expression> acc) throws IllegalArgumentException {
        if (expr instanceof Abstraction) {
            acc.add(((Abstraction) expr).getVar());
            return getVariables(((Abstraction) expr).getExpr(), acc);
        }
        if (expr instanceof Usage) {
            Usage usage = (Usage) expr;
            acc.addAll(getVariables(usage.getLeft()));
            acc.addAll(getVariables(usage.getRight()));
            return acc;
        }
        if (expr instanceof Variable) {
            acc.add(expr);
            return acc;
        }
        throw new IllegalArgumentException("Wrong Expression");
    }

    private Expression exchange(Expression expr, ArrayList<Variable> chained) throws IllegalArgumentException {
        if (expr instanceof Abstraction) {
            Abstraction abstraction = (Abstraction) expr;
            if (abstraction.getVar().equals(v)) {
                return abstraction;
            }
            chained.add(abstraction.getVar());
            return new Abstraction(abstraction.getVar(), exchange(abstraction.getExpr(), chained));
        }
        if (expr instanceof Usage) {
            Usage usage = (Usage) expr;
            return new Usage(
                    exchange(usage.getLeft(), chained), exchange(usage.getRight(), chained));
        }
        if (expr instanceof Variable) {
            Variable variable = (Variable) expr;
            if (variable.equals(v)) {
                for (Variable var : chained) {
                    if (vars.contains(var)) {
                        throw new IllegalArgumentException(
                                "Выражение " + toInsert + " не свободно для подстоновки вместо " + v + ".");
                    }
                }
                return toInsert;
            }
            return variable;
        }
        throw new IllegalArgumentException("Wrong Expression");
    }
}
