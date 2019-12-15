package ca.monor.graph;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListGraph<V, E> extends Graph<V, E> {
    @Override
    public int edgesSize() {
        return 0;
    }

    @Override
    public int verticesSize() {
        return 0;
    }

    @Override
    public void addVertex(V v) {

    }

    @Override
    public void addEdge(V from, V to) {

    }

    @Override
    public void addEdge(V from, V to, E weight) {

    }

    @Override
    public void removeVertex(V v) {

    }

    @Override
    public void removeEdge(V from, V to) {

    }

    @Override
    public void bfs(V begin, VertexVisitor<V> visitor) {

    }

    @Override
    public void dfs(V begin, VertexVisitor<V> visitor) {

    }

    @Override
    public Set<EdgeInfo<V, E>> mst() {
        return null;
    }

    @Override
    public List<V> topologicalSort() {
        return null;
    }

    @Override
    public Map<V, PathInfo<V, E>> shortestPath(V begin) {
        return null;
    }

    @Override
    public Map<V, Map<V, PathInfo<V, E>>> shortestPath() {
        return null;
    }
}
