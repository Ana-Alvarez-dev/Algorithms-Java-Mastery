package org.anaalvarezdev.algorithms.trees;

import org.junit.jupiter.api.Test;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class TreeAnalysisTest {
    @Test void shouldDefineEmptyAndLeafMetricsInEdges() {
        assertThat(TreeAnalysis.analyse(null)).isEqualTo(new TreeAnalysis.Summary(0,-1,0,true));
        assertThat(TreeAnalysis.analyse(new BinaryTreeNode(8))).isEqualTo(new TreeAnalysis.Summary(1,0,0,true));
    }
    @Test void shouldFindAPathWhoseLongestRouteCrossesTheRoot() {
        assertThat(TreeAnalysis.analyse(SmallTrees.example())).isEqualTo(new TreeAnalysis.Summary(6,2,4,true));
    }
    @Test void shouldFindADiameterInsideASubtreeAndDetectDeepImbalance() {
        var arm = new BinaryTreeNode(1,new BinaryTreeNode(2),null);
        var other = new BinaryTreeNode(1,null,new BinaryTreeNode(2));
        var subtree = new BinaryTreeNode(3,arm,other);
        var root = new BinaryTreeNode(4,subtree,null);
        assertThat(TreeAnalysis.analyse(root)).isEqualTo(new TreeAnalysis.Summary(6,3,4,false));
        var hidden = new BinaryTreeNode(0,new BinaryTreeNode(0,new BinaryTreeNode(0,new BinaryTreeNode(0),null),null),
                new BinaryTreeNode(0,null,new BinaryTreeNode(0)));
        assertThat(TreeAnalysis.analyse(hidden).balanced()).isFalse();
    }
    @Test void shouldMatchDistanceAndBreadthFirstOraclesForAllSmallShapes() {
        var roots = SmallTrees.all(); assertThat(roots).hasSize(626);
        for (var root : roots) {
            var nodes = nodes(root); int diameter = 0; boolean balanced = true;
            var edges = new IdentityHashMap<BinaryTreeNode,List<BinaryTreeNode>>();
            for (var node : nodes) { edges.put(node,new ArrayList<>()); }
            for (var node : nodes) {
                balanced &= Math.abs(height(node.left()) - height(node.right())) <= 1;
                for (var child : children(node)) { edges.get(node).add(child); edges.get(child).add(node); }
            }
            for (var start : nodes) {
                record Visit(BinaryTreeNode node,int distance) { }
                var seen = new IdentityHashMap<BinaryTreeNode,Boolean>();
                ArrayDeque<Visit> queue = new ArrayDeque<>(); queue.add(new Visit(start,0)); seen.put(start,true);
                while (!queue.isEmpty()) {
                    var visit = queue.remove(); diameter = Math.max(diameter,visit.distance());
                    for (var next : edges.get(visit.node())) if (!seen.containsKey(next)) {
                        seen.put(next,true); queue.add(new Visit(next,visit.distance()+1));
                    }
                }
            }
            assertThat(TreeAnalysis.analyse(root)).isEqualTo(new TreeAnalysis.Summary(nodes.size(),height(root),diameter,balanced));
        }
    }
    @Test void shouldAnalyseADeepSpineWithoutRecursiveCalls() {
        BinaryTreeNode root = null;
        for (int value = 0; value < 100000; value++) { root = new BinaryTreeNode(value,null,root); }
        assertThat(TreeAnalysis.analyse(root)).isEqualTo(new TreeAnalysis.Summary(100000,99999,99999,false));
    }
    private static int height(BinaryTreeNode root) {
        if (root == null) { return -1; }
        record Visit(BinaryTreeNode node,int depth) { }
        ArrayDeque<Visit> queue = new ArrayDeque<>(); queue.add(new Visit(root,0)); int height = 0;
        while (!queue.isEmpty()) {
            var next = queue.remove(); height = Math.max(height,next.depth());
            for (var child : children(next.node())) { queue.add(new Visit(child,next.depth()+1)); }
        }
        return height;
    }
    private static List<BinaryTreeNode> nodes(BinaryTreeNode root) {
        List<BinaryTreeNode> result = new ArrayList<>();
        if (root != null) {
            result.add(root);
            for (int index = 0; index < result.size(); index++) { result.addAll(children(result.get(index))); }
        }
        return result;
    }
    private static List<BinaryTreeNode> children(BinaryTreeNode node) {
        List<BinaryTreeNode> output = new ArrayList<>();
        if (node.left() != null) { output.add(node.left()); }
        if (node.right() != null) { output.add(node.right()); }
        return output;
    }
}
