package org.anaalvarezdev.algorithms.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Iterative traversals of a proper standalone tree. Null root is empty; values
 * may repeat. Each returns an independent mutable list, leaving nodes unchanged.
 * Theta(n) time/output space. Extra frontier: O(h+1) for DFS, O(w) for BFS.
 */
public final class TreeTraversals {
    private TreeTraversals() { }
    public static List<Integer> preOrder(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        ArrayDeque<BinaryTreeNode> stack = new ArrayDeque<>();
        if (root != null) { stack.push(root); }
        while (!stack.isEmpty()) {
            BinaryTreeNode node = stack.pop(); result.add(node.value());
            if (node.right() != null) { stack.push(node.right()); }
            if (node.left() != null) { stack.push(node.left()); }
        }
        return result;
    }
    public static List<Integer> inOrder(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        ArrayDeque<BinaryTreeNode> stack = new ArrayDeque<>(); BinaryTreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) { stack.push(node); node = node.left(); }
            node = stack.pop(); result.add(node.value()); node = node.right();
        }
        return result;
    }
    public static List<Integer> postOrder(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        ArrayDeque<BinaryTreeNode> stack = new ArrayDeque<>();
        BinaryTreeNode node = root; BinaryTreeNode previous = null;
        while (node != null || !stack.isEmpty()) {
            if (node != null) { stack.push(node); node = node.left();
            } else {
                BinaryTreeNode top = stack.peek();
                if (top.right() != null && previous != top.right()) { node = top.right();
                } else { result.add(top.value()); previous = stack.pop(); }
            }
        }
        return result;
    }
    public static List<Integer> levelOrder(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        ArrayDeque<BinaryTreeNode> queue = new ArrayDeque<>();
        if (root != null) { queue.add(root); }
        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.remove(); result.add(node.value());
            if (node.left() != null) { queue.add(node.left()); }
            if (node.right() != null) { queue.add(node.right()); }
        }
        return result;
    }
}
