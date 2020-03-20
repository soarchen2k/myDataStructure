package ca.monor.greedy贪心.bag;

public class Article {
    public int weight;
    public int value;
    public double valueDensity;

    public Article(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.valueDensity = value * 1.0 / weight;
    }

    public Article() { }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getValueDensity() {
        return valueDensity;
    }

    public void setValueDensity(double valueDensity) {
        this.valueDensity = valueDensity;
    }

    @Override
    public String toString() {
        return "[" + "weight=" + weight + ", value=" + value + ']';
    }
}
