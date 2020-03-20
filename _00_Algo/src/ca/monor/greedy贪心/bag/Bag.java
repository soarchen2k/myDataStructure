package ca.monor.greedy贪心.bag;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Bag {
    public static void main(String[] args) {

        select((Article a1, Article a2) -> a1.weight - a2.weight);
        select((Article a1, Article a2) -> a2.value - a1.value);
        select((Article a1, Article a2) -> Double.compare(a2.valueDensity, a1.valueDensity));
    }

    private static void select(Comparator<Article> articleComparator) {
        Article[] articles = new Article[]{
                new Article(35, 10),
                new Article(30, 40),
                new Article(60, 30),
                new Article(50, 50),
                new Article(40, 35),
                new Article(10, 40),
                new Article(25, 30)
        };

        Arrays.sort(articles, articleComparator);

        int capacity = 150;
        int totalValue = 0;
        int totalWeight = 0;
        List<Article> selectedArticles = new LinkedList<>();
        for (int i = 0; i < articles.length; i++) {
            int newWeight = totalWeight + articles[i].weight;
            if (newWeight <= capacity) {
                totalWeight = newWeight;
                totalValue += articles[i].value;
                selectedArticles.add(articles[i]);
            }
        }
        System.out.println("Value = " + totalValue + ", Weight = " + totalWeight);
    }
}
