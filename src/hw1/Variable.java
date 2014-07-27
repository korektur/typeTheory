package hw1;

public class Variable implements Expression, Comparable {

    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Variable))
            return false;
        Variable other = (Variable) o;
        return name.equals(other.toString());
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Object o) {
        return toString().compareTo(o.toString());
    }

}
