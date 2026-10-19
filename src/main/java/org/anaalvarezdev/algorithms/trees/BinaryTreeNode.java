package org.anaalvarezdev.algorithms.trees;

/**
 * Immutable standalone binary-tree node; null children denote empty subtrees.
 * Tree algorithms require a proper tree (no child identity shared by two paths).
 * That shape precondition is not validated by each traversal. Container nodes
 * are separate and private; this type does not expose a search tree's storage.
 * Identity equality is intentional; no recursive equals/hashCode/toString.
 */
public final class BinaryTreeNode {
    private final int value;
    private final BinaryTreeNode left;
    private final BinaryTreeNode right;
    public BinaryTreeNode(int value) { this(value, null, null); }
    public BinaryTreeNode(int value, BinaryTreeNode left, BinaryTreeNode right) {
        this.value = value; this.left = left; this.right = right;
    }
    public int value() { return value; }
    public BinaryTreeNode left() { return left; }
    public BinaryTreeNode right() { return right; }
}
