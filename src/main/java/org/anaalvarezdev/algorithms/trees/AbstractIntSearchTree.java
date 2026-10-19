package org.anaalvarezdev.algorithms.trees;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.OptionalInt;

/** Shared iterative queries; private-to-package representation is never exposed. */
abstract class AbstractIntSearchTree implements OrderedIntSet {
    protected static final class Node {
        int value;
        Node left;
        Node right;
        int height;
        Node(int value) { this.value = value; }
    }
    protected Node root;
    protected int size;

    @Override public int size() { return size; }
    @Override public void clear() { root = null; size = 0; }

    @Override public boolean contains(int value) {
        Node node = root;
        while (node != null) {
            if (value == node.value) { return true; }
            node = value < node.value ? node.left : node.right;
        }
        return false;
    }

    @Override public OptionalInt first() {
        Node node = root;
        if (node == null) { return OptionalInt.empty(); }
        while (node.left != null) { node = node.left; }
        return OptionalInt.of(node.value);
    }

    @Override public OptionalInt last() {
        Node node = root;
        if (node == null) { return OptionalInt.empty(); }
        while (node.right != null) { node = node.right; }
        return OptionalInt.of(node.value);
    }

    @Override public OptionalInt floor(int value) {
        Node node = root;
        OptionalInt candidate = OptionalInt.empty();
        while (node != null) {
            if (value == node.value) { return OptionalInt.of(value); }
            if (value < node.value) { node = node.left;
            } else { candidate = OptionalInt.of(node.value); node = node.right; }
        }
        return candidate;
    }

    @Override public OptionalInt ceiling(int value) {
        Node node = root;
        OptionalInt candidate = OptionalInt.empty();
        while (node != null) {
            if (value == node.value) { return OptionalInt.of(value); }
            if (value > node.value) { node = node.right;
            } else { candidate = OptionalInt.of(node.value); node = node.left; }
        }
        return candidate;
    }

    @Override public int height() {
        if (root == null) { return -1; }
        ArrayDeque<Node> queue = new ArrayDeque<>(); queue.add(root);
        int height = -1;
        while (!queue.isEmpty()) {
            int width = queue.size(); height++;
            for (int index = 0; index < width; index++) {
                Node node = queue.remove();
                if (node.left != null) { queue.add(node.left); }
                if (node.right != null) { queue.add(node.right); }
            }
        }
        return height;
    }

    @Override public int[] toArray() {
        int[] result = new int[size];
        int index = 0;
        ArrayDeque<Node> stack = new ArrayDeque<>(); Node node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) { stack.push(node); node = node.left; }
            node = stack.pop(); result[index++] = node.value; node = node.right;
        }
        return result;
    }

    boolean hasValidStructure() {
        record Bound(Node node, long low, long high) { }
        var seen = Collections.newSetFromMap(new IdentityHashMap<Node, Boolean>());
        ArrayDeque<Bound> pending = new ArrayDeque<>();
        if (root != null) { pending.push(new Bound(root, Long.MIN_VALUE, Long.MAX_VALUE)); }
        while (!pending.isEmpty()) {
            Bound bound = pending.pop(); Node node = bound.node();
            if (!seen.add(node) || node.value <= bound.low() || node.value >= bound.high()) { return false; }
            if (node.left != null) { pending.push(new Bound(node.left, bound.low(), node.value)); }
            if (node.right != null) { pending.push(new Bound(node.right, node.value, bound.high())); }
        }
        return size >= 0 && seen.size() == size;
    }
}
