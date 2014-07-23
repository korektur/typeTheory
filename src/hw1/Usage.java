package hw1;

import java.util.ArrayList;

public class Usage implements Expression {

    private ArrayList<Expression> terms;

    public Usage(ArrayList<Expression> terms) {
        this.terms = terms;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usage))
            return false;
        Usage other = (Usage)o;
        if (terms.size() != other.terms.size())
            return false;
        for(int i = 0; i < terms.size(); ++i) {
            if (!terms.get(i).equals(other.terms.get(i)))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Expression expr : terms)
            sb.append(expr);
        return sb.toString();
    }

}
