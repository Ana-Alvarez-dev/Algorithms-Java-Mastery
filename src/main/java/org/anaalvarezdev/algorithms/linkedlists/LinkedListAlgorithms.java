package org.anaalvarezdev.algorithms.linkedlists;

import java.util.Comparator;
import java.util.Objects;

/**
 * Representative algorithms over explicit LinkedNode chains.
 *
 * <p>Null heads represent empty chains. Except for hasCycle, methods reject
 * cycles before modifying links. Sorted operations validate input order first;
 * comparators must be non-null and define a consistent order for all values.
 * Validation adds linear work and constant auxiliary state. Comparisons are
 * assumed constant-time in the stated bounds.
 *
 * <p>These are ownership-sensitive node algorithms, not operations on a
 * LinkedSequence container with cached size and endpoints. Mutating methods
 * return the head callers must retain; element nodes and stored values are reused.
 */
public final class LinkedListAlgorithms {

    private LinkedListAlgorithms() {
    }

    /**
     * Floyd detection compares node identity, not equal stored values. After k
     * steps slow has advanced k and fast 2k; in a cycle their relative offset
     * eventually becomes zero. Acyclic fast traversal reaches null instead.
     *
     * @param head the chain, possibly null or cyclic
     * @return whether a reachable cycle exists
     */
    public static boolean hasCycle(LinkedNode<?> head) {
        LinkedNode<?> slow = head;
        LinkedNode<?> fast = head;
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * The reversed prefix and untouched suffix partition the original chain.
     * Saving next before changing the link preserves access to the suffix.
     * Each step moves one node; exhaustion establishes the reversed chain.
     * Time Θ(n), auxiliary space Θ(1), including cycle validation.
     *
     * @param <E> the stored type
     * @param head the acyclic chain, possibly null
     * @return its new reversed head, or null
     * @throws IllegalArgumentException if the input contains a cycle
     */
    public static <E> LinkedNode<E> reverse(LinkedNode<E> head) {
        requireAcyclic(head);
        LinkedNode<E> previous = null;
        LinkedNode<E> current = head;
        while (current != null) {
            LinkedNode<E> next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }
        return previous;
    }

    /**
     * Returns the second middle for even lengths: position floor(n/2).
     * Slow advances once for every two fast steps. This method leaves links and
     * values unchanged. Time Θ(n), auxiliary space Θ(1), including validation.
     *
     * @param <E> the stored type
     * @param head the acyclic chain, possibly null
     * @return the middle node, or null for an empty chain
     * @throws IllegalArgumentException if the input contains a cycle
     */
    public static <E> LinkedNode<E> middle(LinkedNode<E> head) {
        requireAcyclic(head);
        LinkedNode<E> slow = head;
        LinkedNode<E> fast = head;
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    /**
     * Destructively merges disjoint sorted chains, reusing element nodes.
     * Equal heads select the left chain first, preserving within-chain order.
     * The constructed prefix is ordered and contains exactly the consumed
     * nodes. Each step consumes a head, so the loop terminates. A constant-size
     * temporary sentinel is not part of the returned element sequence.
     * Time Θ(n + m), auxiliary space Θ(1), including input validation.
     *
     * @param <E> the stored type
     * @param left first input, possibly null
     * @param right second input, possibly null
     * @param comparator ordering relation for all stored values
     * @return the merged head; original heads must no longer be treated as independent chains
     * @throws NullPointerException if comparator is null
     * @throws IllegalArgumentException for cycles, unsorted inputs, or shared nodes
     */
    public static <E> LinkedNode<E> mergeSorted(
            LinkedNode<E> left, LinkedNode<E> right, Comparator<? super E> comparator) {
        Objects.requireNonNull(comparator, "comparator must not be null");
        LinkedNode<E> leftTail = validateSorted(left, comparator);
        LinkedNode<E> rightTail = validateSorted(right, comparator);
        if (leftTail != null && leftTail == rightTail) {
            throw new IllegalArgumentException("input chains must not share nodes");
        }
        LinkedNode<E> sentinel = new LinkedNode<>(null);
        LinkedNode<E> tail = sentinel;
        while (left != null && right != null) {
            if (comparator.compare(left.getValue(), right.getValue()) <= 0) {
                tail.setNext(left);
                left = left.getNext();
            } else {
                tail.setNext(right);
                right = right.getNext();
            }
            tail = tail.getNext();
        }
        tail.setNext(left != null ? left : right);
        return sentinel.getNext();
    }

    /**
     * Removes adjacent comparator-equal nodes from a sorted chain, retaining
     * the first node of each equal run. Sortedness makes every equivalence run
     * contiguous. Each step either detaches one duplicate or advances current,
     * preserving the unique prefix and terminating after linear work.
     * Time Θ(n), auxiliary space Θ(1), including validation.
     *
     * @param <E> the stored type
     * @param head the sorted acyclic chain, possibly null
     * @param comparator ordering relation for all stored values
     * @return the original head with duplicates removed, or null
     * @throws NullPointerException if comparator is null
     * @throws IllegalArgumentException for cycles or unsorted input
     */
    public static <E> LinkedNode<E> removeDuplicatesSorted(
            LinkedNode<E> head, Comparator<? super E> comparator) {
        Objects.requireNonNull(comparator, "comparator must not be null");
        validateSorted(head, comparator);
        LinkedNode<E> current = head;
        while (current != null && current.getNext() != null) {
            LinkedNode<E> next = current.getNext();
            if (comparator.compare(current.getValue(), next.getValue()) == 0) {
                current.setNext(next.getNext());
                next.setNext(null);
            } else {
                current = next;
            }
        }
        return head;
    }

    private static void requireAcyclic(LinkedNode<?> head) {
        if (hasCycle(head)) {
            throw new IllegalArgumentException("input chain must be acyclic");
        }
    }

    private static <E> LinkedNode<E> validateSorted(
            LinkedNode<E> head, Comparator<? super E> comparator) {
        requireAcyclic(head);
        LinkedNode<E> current = head;
        if (current == null) {
            return null;
        }
        while (current.getNext() != null) {
            if (comparator.compare(current.getValue(), current.getNext().getValue()) > 0) {
                throw new IllegalArgumentException("input chain must be sorted");
            }
            current = current.getNext();
        }
        return current;
    }
}
