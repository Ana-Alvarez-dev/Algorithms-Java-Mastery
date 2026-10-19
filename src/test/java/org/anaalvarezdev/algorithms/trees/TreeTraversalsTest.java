package org.anaalvarezdev.algorithms.trees;

import org.junit.jupiter.api.Test;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class TreeTraversalsTest {
    @Test void shouldTreatNullAsEmptyForAllOrders() {
        assertThat(TreeTraversals.preOrder(null)).isEmpty(); assertThat(TreeTraversals.inOrder(null)).isEmpty();
        assertThat(TreeTraversals.postOrder(null)).isEmpty(); assertThat(TreeTraversals.levelOrder(null)).isEmpty();
    }
    @Test void shouldReturnTheSpecifiedFourOrders() {
        var root = SmallTrees.example();
        assertThat(TreeTraversals.preOrder(root)).containsExactly(4,2,1,3,6,7);
        assertThat(TreeTraversals.inOrder(root)).containsExactly(1,2,3,4,6,7);
        assertThat(TreeTraversals.postOrder(root)).containsExactly(1,3,2,7,6,4);
        assertThat(TreeTraversals.levelOrder(root)).containsExactly(4,2,6,1,3,7);
        assertThat(root.left().value()).isEqualTo(2); assertThat(root.right().right().value()).isEqualTo(7);
    }
    @Test void shouldKeepDuplicateValuesAndIntegerExtremes() {
        var root = new BinaryTreeNode(0,new BinaryTreeNode(Integer.MIN_VALUE),
                new BinaryTreeNode(0,null,new BinaryTreeNode(Integer.MAX_VALUE)));
        assertThat(TreeTraversals.inOrder(root)).containsExactly(Integer.MIN_VALUE,0,0,Integer.MAX_VALUE);
        assertThat(TreeTraversals.postOrder(root)).containsExactly(Integer.MIN_VALUE,Integer.MAX_VALUE,0,0);
    }
    @Test void shouldReturnIndependentMutableListsIncludingTheEmptyResult() {
        var root = SmallTrees.example(); var values = TreeTraversals.inOrder(root);
        values.clear(); values.add(100); assertThat(TreeTraversals.inOrder(root)).containsExactly(1,2,3,4,6,7);
        var empty = TreeTraversals.preOrder(null); empty.add(1); assertThat(TreeTraversals.preOrder(null)).isEmpty();
    }
    @Test void shouldMatchIndependentSmallTreeOraclesForEveryShapeThroughSevenNodes() {
        var roots = SmallTrees.all(); assertThat(roots).hasSize(626);
        for (var root : roots) {
            for (int order = 0; order < 3; order++) {
                List<Integer> expected = new ArrayList<>(); recursive(root,order,expected);
                List<Integer> actual = switch (order) {
                    case 0 -> TreeTraversals.preOrder(root);
                    case 1 -> TreeTraversals.inOrder(root);
                    default -> TreeTraversals.postOrder(root);
                };
                assertThat(actual).isEqualTo(expected);
            }
            assertThat(TreeTraversals.levelOrder(root)).isEqualTo(levelOracle(root));
        }
    }
    @Test void shouldProcessDeepLeftAndRightSpinesWithoutRecursion() {
        for (boolean left : new boolean[]{true,false}) {
            BinaryTreeNode root = null;
            for (int value = 99999; value >= 0; value--) {
                root = new BinaryTreeNode(value,left ? root : null,left ? null : root);
            }
            assertThat(TreeTraversals.preOrder(root)).hasSize(100000).startsWith(0,1,2).endsWith(99999);
            assertThat(TreeTraversals.levelOrder(root)).hasSize(100000).startsWith(0,1,2).endsWith(99999);
            assertThat(TreeTraversals.postOrder(root)).hasSize(100000).startsWith(99999,99998).endsWith(1,0);
            var inorder = TreeTraversals.inOrder(root);
            assertThat(inorder).hasSize(100000);
            assertThat(inorder.get(0)).isEqualTo(left ? 99999 : 0);
            assertThat(inorder.get(99999)).isEqualTo(left ? 0 : 99999);
        }
    }
    private static void recursive(BinaryTreeNode node, int order, List<Integer> result) {
        if (node == null) { return; }
        if (order == 0) { result.add(node.value()); }
        recursive(node.left(),order,result); if (order == 1) { result.add(node.value()); }
        recursive(node.right(),order,result); if (order == 2) { result.add(node.value()); }
    }
    private static List<Integer> levelOracle(BinaryTreeNode root) {
        record AtDepth(BinaryTreeNode node,int depth) { }
        List<AtDepth> ordered = new ArrayList<>(); ArrayDeque<AtDepth> stack = new ArrayDeque<>();
        if (root != null) { stack.push(new AtDepth(root,0)); }
        while (!stack.isEmpty()) {
            var entry = stack.pop(); ordered.add(entry);
            if (entry.node().right() != null) { stack.push(new AtDepth(entry.node().right(),entry.depth()+1)); }
            if (entry.node().left() != null) { stack.push(new AtDepth(entry.node().left(),entry.depth()+1)); }
        }
        ordered.sort(java.util.Comparator.comparingInt(AtDepth::depth));
        return ordered.stream().map(entry -> entry.node().value()).toList();
    }
}
