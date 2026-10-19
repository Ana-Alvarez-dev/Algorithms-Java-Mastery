package org.anaalvarezdev.algorithms.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class SmallTrees {
    private record Shape(Shape left, Shape right) { }
    private SmallTrees() { }
    static List<BinaryTreeNode> all() {
        List<List<Shape>> shapes = new ArrayList<>(); shapes.add(Collections.singletonList(null));
        for (int size = 1; size <= 7; size++) {
            List<Shape> current = new ArrayList<>();
            for (int leftSize = 0; leftSize < size; leftSize++) {
                for (Shape left : shapes.get(leftSize)) for (Shape right : shapes.get(size - 1 - leftSize)) {
                    current.add(new Shape(left,right));
                }
            }
            shapes.add(current);
        }
        List<BinaryTreeNode> result = new ArrayList<>();
        for (List<Shape> group : shapes) for (Shape shape : group) { result.add(materialise(shape,new int[1])); }
        return result;
    }
    private static BinaryTreeNode materialise(Shape shape, int[] next) {
        if (shape == null) { return null; }
        BinaryTreeNode left = materialise(shape.left(),next); int value = next[0]++;
        return new BinaryTreeNode(value,left,materialise(shape.right(),next));
    }
    static BinaryTreeNode example() {
        return new BinaryTreeNode(4,new BinaryTreeNode(2,new BinaryTreeNode(1),new BinaryTreeNode(3)),
                new BinaryTreeNode(6,null,new BinaryTreeNode(7)));
    }
}
