package hw2;

import hw1.Abstraction;
import hw1.Expression;
import hw1.Usage;
import hw1.Variable;

import java.util.*;

public class Utils {

    public static List<Variable> getFreeVariables(Expression expr) {
        return getFreeVariables_(expr, new TreeSet<>());
    }

    private static List<Variable> getFreeVariables_(Expression expr, Set<Variable> chained) {
        if (expr instanceof hw1.Abstraction) {
            Abstraction abstraction = (Abstraction) expr;
            chained.add(abstraction.getVar());
            return getFreeVariables_(abstraction.getExpr(), chained);
        }
        List<Variable> res = new ArrayList<>();
        if (expr instanceof Usage) {
            Usage usage = (Usage) expr;
            List<Variable> tmp = new LinkedList<>();
            tmp.addAll(getFreeVariables_(usage.getLeft(), chained));
            tmp.addAll(getFreeVariables_(usage.getRight(), chained));
            tmp.stream().filter(v -> !res.contains(v)).forEach(res::add);
        } else {
            Variable variable = (Variable) expr;
            if (!chained.contains(variable))
                res.add(variable);
        }
        return res;
    }
}
