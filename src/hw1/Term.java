package hw1;

public class Term implements Expression {

    private Expression expr;

    public Term(Expression expr) {
        this.expr = expr;
    }

    public Expression getExpr() {
        return expr;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Term))
            return false;
        Term other = (Term)o;
        return expr.equals(other.getExpr());
    }

    @Override
    public String toString() {
        if (expr instanceof Variable)
            return expr.toString();
        return "(" + expr + ")";
    }
}
