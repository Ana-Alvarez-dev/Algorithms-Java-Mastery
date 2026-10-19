package org.anaalvarezdev.algorithms.trees;

/**
 * Unbalanced strict BST with iterative insertion, keyed deletion and queries.
 * Path operations cost O(h+1), worst O(n); sorted insertion produces a spine.
 * No operation uses recursion. Height itself scans all nodes in O(n) time.
 */
public final class IntBinarySearchTree extends AbstractIntSearchTree {
    @Override public boolean add(int value) {
        if (root == null) { root = new Node(value); size++; return true; }
        Node node = root;
        while (true) {
            if (value == node.value) { return false; }
            if (value < node.value) {
                if (node.left == null) { node.left = new Node(value); break; }
                node = node.left;
            } else {
                if (node.right == null) { node.right = new Node(value); break; }
                node = node.right;
            }
        }
        size++; return true;
    }

    @Override public boolean remove(int value) {
        Node parent = null; Node node = root;
        while (node != null && node.value != value) {
            parent = node; node = value < node.value ? node.left : node.right;
        }
        if (node == null) { return false; }
        if (node.left != null && node.right != null) {
            Node successorParent = node; Node successor = node.right;
            while (successor.left != null) { successorParent = successor; successor = successor.left; }
            node.value = successor.value;
            parent = successorParent; node = successor;
        }
        Node child = node.left != null ? node.left : node.right;
        if (parent == null) { root = child;
        } else if (parent.left == node) { parent.left = child;
        } else { parent.right = child; }
        size--; return true;
    }
}
