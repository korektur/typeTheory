package hw6;

import hw1.Expression;

public class Equals implements Expression {
    Expression left;
    Expression right;

    public Equals(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return left + "=" + right;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Equals) {
            Equals other = (Equals) o;
            return other.left.equals(left) && other.right.equals(right);
        }
        return false;
    }
}
