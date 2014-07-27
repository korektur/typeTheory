package hw1;

public class Abstraction implements Expression {

    private Variable var;
    private Expression expr;

    public Abstraction(Variable var, Expression expr) {
        this.expr = expr;
        this.var = var;
    }

    public Expression getExpr() {
        return expr;
    }

    public Variable getVar() {
        return var;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Abstraction))
            return false;
        Abstraction other = (Abstraction) o;
        return expr.equals(other.getExpr()) && var.equals(other.getVar());
    }

    @Override
    public String toString() {
        return "\\" + var + "." + expr + "";
    }
}
