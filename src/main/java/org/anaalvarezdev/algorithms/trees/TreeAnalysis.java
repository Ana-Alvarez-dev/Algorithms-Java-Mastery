package org.anaalvarezdev.algorithms.trees;

import java.util.ArrayDeque;

/** Bottom-up metrics for a proper tree; explicit O(h+1) frames avoid call-stack overflow. */
public final class TreeAnalysis {
    /** Height/diameter count edges. Empty summary is (0,-1,0,true). */
    public record Summary(int size, int height, int diameter, boolean balanced) { }
    private static final Summary EMPTY = new Summary(0, -1, 0, true);
    private static final class Frame {
        final BinaryTreeNode node;
        int phase;
        Summary left = EMPTY;
        Summary right = EMPTY;
        Frame(BinaryTreeNode node) { this.node = node; }
    }
    private TreeAnalysis() { }
    /** Theta(n) time; diameter is the longest path between any two nodes. */
    public static Summary analyse(BinaryTreeNode root) {
        if (root == null) { return EMPTY; }
        ArrayDeque<Frame> stack = new ArrayDeque<>(); stack.push(new Frame(root));
        while (!stack.isEmpty()) {
            Frame frame = stack.peek();
            if (frame.phase == 0) {
                frame.phase = 1;
                if (frame.node.left() != null) { stack.push(new Frame(frame.node.left())); }
                continue;
            }
            if (frame.phase == 1) {
                frame.phase = 2;
                if (frame.node.right() != null) { stack.push(new Frame(frame.node.right())); }
                continue;
            }
            Summary left = frame.left; Summary right = frame.right;
            Summary result = new Summary(1 + left.size() + right.size(),
                    1 + Math.max(left.height(), right.height()),
                    Math.max(left.height() + right.height() + 2, Math.max(left.diameter(), right.diameter())),
                    left.balanced() && right.balanced() && Math.abs(left.height() - right.height()) <= 1);
            stack.pop();
            if (stack.isEmpty()) { return result; }
            Frame parent = stack.peek();
            if (parent.phase == 1) { parent.left = result; } else { parent.right = result; }
        }
        throw new AssertionError("nonempty traversal must return its summary");
    }
}
