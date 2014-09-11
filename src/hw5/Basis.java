package hw5;

import hw1.Expression;

public class Basis implements Expression{

    public enum BasisTypes  {
        S, K, I
    }

    private BasisTypes basis;

    public Basis(BasisTypes basis) {
        this.basis = basis;
    }

    @Override
    public String toString() {
        if (basis == BasisTypes.I)
            return "I";
        if (basis == BasisTypes.K)
            return "K";
        return "S";
    }

}
