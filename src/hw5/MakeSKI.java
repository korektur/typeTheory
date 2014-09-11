package hw5;

import hw1.Abstraction;
import hw1.Expression;
import hw1.Usage;
import hw1.Variable;
import hw2.Utils;

import java.util.List;

public class MakeSKI {
    Expression expr;


    public MakeSKI(Expression expr) {
        this.expr = expr;
    }

    public Expression make() {
        return make(expr);
    }

    private Expression make(Expression expr) {
        if (checkI(expr))
            return new Basis(Basis.BasisTypes.I);
        if (checkK(expr))
            return new Basis(Basis.BasisTypes.K);
        if (checkS(expr))
            return new Basis(Basis.BasisTypes.S);



        if (expr instanceof Variable) {
            return expr;
        }

        if (expr instanceof Usage) {
            Expression left = ((Usage) expr).getLeft();
            Expression right = ((Usage) expr).getRight();
            return new Usage(make(left), make(right));
        }

        if (expr instanceof Abstraction) {
            List<Variable> freeVariables = Utils.getFreeVariables(((Abstraction) expr).getExpr());
            if (!freeVariables.contains(((Abstraction) expr).getVar())) {
                return new Usage(new Basis(Basis.BasisTypes.K), make(((Abstraction) expr).getExpr()));
            }
        }

        if (expr instanceof Abstraction) {
            Abstraction abstraction1 = (Abstraction) expr;
            Variable v1 = abstraction1.getVar();
            Expression expr1 = abstraction1.getExpr();
            if (expr1 instanceof Abstraction) {
                Abstraction abstraction2 = (Abstraction) expr1;
                Variable v2 = abstraction2.getVar();
                Expression expr2 = abstraction2.getExpr();
                if (!v2.equals(v1)) {
                    return make(new Abstraction(v1, make(new Abstraction(v2, expr2))));
                }
            }
        }

        if (expr instanceof Abstraction) {
            Abstraction abstraction1 = (Abstraction) expr;
            Variable v1 = abstraction1.getVar();
            Expression expr1 = abstraction1.getExpr();
            if (expr1 instanceof Usage) {
               return new Usage(new Usage(new Basis(Basis.BasisTypes.S),
                       make(new Abstraction(v1, ((Usage) expr1).getLeft()))),
                       make(new Abstraction(v1, ((Usage) expr1).getRight())));
            }
        }

        return expr;
    }

    private boolean checkK(Expression expr) {
        if (expr instanceof Abstraction) {
            Abstraction abstraction1 = (Abstraction) expr;
            Variable v1 = abstraction1.getVar();
            Expression expr1 = abstraction1.getExpr();
            if (expr1 instanceof Abstraction) {
                Abstraction abstraction2 = (Abstraction) expr1;
                Variable v2 = abstraction2.getVar();
                Expression expr2 = abstraction2.getExpr();
                if (expr2 instanceof Variable && !v2.equals(expr2) && !v1.equals(expr2) && !v2.equals(v1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkS(Expression expr) {
        if (expr instanceof Abstraction) {
            Abstraction abstraction1 = (Abstraction) expr;
            Variable v1 = abstraction1.getVar();
            Expression expr1 = abstraction1.getExpr();
            if (expr1 instanceof Abstraction) {
                Abstraction abstraction2 = (Abstraction) expr1;
                Variable v2 = abstraction2.getVar();
                Expression expr2 = abstraction2.getExpr();
                if (expr2 instanceof Abstraction) {
                    Abstraction abstraction3 = (Abstraction) expr2;
                    Variable v3 = abstraction3.getVar();
                    Expression expr3 = abstraction3.getExpr();
                    if (expr3 instanceof Usage) {
                        Expression left1 = ((Usage) expr3).getLeft();
                        Expression right1 = ((Usage) expr3).getRight();
                        if (left1 instanceof Usage) {
                            Expression left2 = ((Usage) left1).getLeft();
                            Expression right2 = ((Usage) left1).getRight();
                            if (left2.equals(v1) && right2.equals(v3) && right1 instanceof Usage) {
                                Expression left3 = ((Usage) right1).getLeft();
                                Expression right3 = ((Usage) right1).getRight();
                                if (left3.equals(v2) && right3.equals(v3))
                                    return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean checkI(Expression expr) {
        if (expr instanceof Abstraction) {
            Abstraction abstraction1 = (Abstraction) expr;
            Variable v1 = abstraction1.getVar();
            Expression expr1 = abstraction1.getExpr();
            if (v1.equals(expr1))
                return true;
        }
        return false;
    }
}
