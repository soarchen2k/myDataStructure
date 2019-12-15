package ca.monor.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * 双泛型类，同时包含两个泛型
 * @param <V> Value
 * @param <E> Weight
 */
public abstract class Graph<V, E> {
    protected WeightManager<E> weightManager;


    /*
    接口，WeightManager 维护权重
     */
    public interface WeightManager<E> {
        int compare(E w1, E w2);
        E add(E w1, E w2);
        E zero();
    }

    /*
    接口 VertexVisitor 维护对 Vertex 的访问
     */
    public interface VertexVisitor<V> {
        boolean visit(V v);
    }

    public static class PathInfo<V, E> {
        protected E weight;
        protected List<EdgeInfo<V, E>> edgeInfos = new LinkedList<>();
        public PathInfo(){}

        public PathInfo(E weight) {
            this.weight = weight;
        }

        public E getWeight() {
            return weight;
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }

        public List<EdgeInfo<V, E>> getEdgeInfos() {
            return edgeInfos;
        }

        public void setEdgeInfos(List<EdgeInfo<V, E>> edgeInfos) {
            this.edgeInfos = edgeInfos;
        }

        @Override
        public String toString() {
            return "PathInfo [" +
                    "weight=" + weight +
                    ", edgeInfos=" + edgeInfos +
                    ']';
        }
    }

    public static class EdgeInfo<V, E> {
        private V from;
        private V to;
        private E weight;

        public EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public V getFrom() {
            return from;
        }

        public void setFrom(V from) {
            this.from = from;
        }

        public V getTo() {
            return to;
        }

        public void setTo(V to) {
            this.to = to;
        }

        public E getWeight() {
            return weight;
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "EdgeInfo [" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    ']';
        }
    }
}
