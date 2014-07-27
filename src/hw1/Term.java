package hw1;

public class Term implements Expression{

    private Expression left;
    private Expression right;

    public Term(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Term))
            return false;
        Term other = (Term)o;
        return other.left.equals(left) && other.right.equals(right);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        if (left instanceof Abstraction)
            sb.append('(').append(left).append(')');
        else
            sb.append(left);
        sb.append(' ');
        if (right instanceof Abstraction)
            sb.append('(').append(right).append(')');
        else
            sb.append(right);
        sb.append(')');
        return sb.toString();
    }
}
