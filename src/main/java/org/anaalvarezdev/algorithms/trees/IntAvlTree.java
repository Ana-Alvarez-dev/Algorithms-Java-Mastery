package org.anaalvarezdev.algorithms.trees;

/**
 * Strict AVL int set with cached edge heights and single/double rotations.
 * Path operations have worst-case O(log(n+1)) time and recursive updates use
 * O(log(n+1)) call frames. Height is O(1); snapshots still visit every node.
 */
public final class IntAvlTree extends AbstractIntSearchTree {
    @Override public int height() { return height(root); }

    @Override public boolean add(int value) {
        int before = size; root = insert(root, value); return size != before;
    }

    @Override public boolean remove(int value) {
        int before = size; root = delete(root, value); return size != before;
    }

    private Node insert(Node node, int value) {
        if (node == null) { size++; return new Node(value); }
        if (value < node.value) { node.left = insert(node.left, value);
        } else if (value > node.value) { node.right = insert(node.right, value);
        } else { return node; }
        return balance(node);
    }

    private Node delete(Node node, int value) {
        if (node == null) { return null; }
        if (value < node.value) { node.left = delete(node.left, value);
        } else if (value > node.value) { node.right = delete(node.right, value);
        } else {
            if (node.left == null || node.right == null) {
                size--; return node.left != null ? node.left : node.right;
            }
            Node successor = node.right;
            while (successor.left != null) { successor = successor.left; }
            node.value = successor.value;
            node.right = delete(node.right, successor.value);
        }
        return balance(node);
    }

    private static int height(Node node) { return node == null ? -1 : node.height; }
    private static void update(Node node) { node.height = 1 + Math.max(height(node.left), height(node.right)); }
    private static int skew(Node node) { return height(node.left) - height(node.right); }

    private static Node balance(Node node) {
        update(node);
        if (skew(node) > 1) {
            if (skew(node.left) < 0) { node.left = rotateLeft(node.left); }
            return rotateRight(node);
        }
        if (skew(node) < -1) {
            if (skew(node.right) > 0) { node.right = rotateRight(node.right); }
            return rotateLeft(node);
        }
        return node;
    }

    private static Node rotateLeft(Node node) {
        Node top = node.right; node.right = top.left; top.left = node;
        update(node); update(top); return top;
    }
    private static Node rotateRight(Node node) {
        Node top = node.left; node.left = top.right; top.right = node;
        update(node); update(top); return top;
    }

    @Override boolean hasValidStructure() {
        return super.hasValidStructure() && checkedHeight(root) != Integer.MIN_VALUE;
    }

    private static int checkedHeight(Node node) {
        if (node == null) { return -1; }
        int left = checkedHeight(node.left); int right = checkedHeight(node.right);
        if (left == Integer.MIN_VALUE || right == Integer.MIN_VALUE || Math.abs(left - right) > 1
                || node.height != 1 + Math.max(left, right)) { return Integer.MIN_VALUE; }
        return node.height;
    }
}
