package org.anaalvarezdev.algorithms.greedy;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Optimal binary prefix codes for positive int frequencies. Integer symbols
 * are opaque ids, not Unicode/text parsing. Codewords are deterministic by
 * (subtree weight, minimum symbol); first/second removals become left/right.
 * Empty input costs zero. A singleton uses code "0", costing its frequency;
 * this is the explicit nonempty-codeword convention, not a zero-bit code.
 */
public final class HuffmanCoding {
    private HuffmanCoding() { }
    private record Node(long weight, int minimumSymbol, Integer symbol, Node left, Node right) { }
    private record Frame(Node node, String prefix) { }

    /** Immutable sorted symbol/code map and exact weighted codeword bit length. */
    public record Codebook(Map<Integer,String> codes, BigInteger weightedBitLength) {
        public Codebook { codes = Collections.unmodifiableMap(new TreeMap<>(codes)); }
    }

    /**
     * Builds a codebook without mutating input. Null map/keys/values throw
     * NullPointerException; nonpositive frequencies throw IllegalArgumentException.
     * Tree traversal uses an explicit stack. With L total codeword characters,
     * tree/queue plus code materialisation use O(n+L) state, not merely O(n).
     * Queue operations use O(n log n) comparisons; materialisation adds O(L).
     * This is not a bitstream encoder/decoder or a full compression format.
     */
    public static Codebook build(Map<Integer,Integer> frequencies) {
        Objects.requireNonNull(frequencies, "frequencies must not be null");
        Map<Integer,Integer> snapshot = new TreeMap<>();
        frequencies.forEach((symbol,frequency) -> {
            Objects.requireNonNull(symbol, "symbol must not be null");
            Objects.requireNonNull(frequency, "frequency must not be null");
            requirePositive(frequency);
            snapshot.put(symbol, frequency);
        });
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingLong(Node::weight).thenComparingInt(Node::minimumSymbol));
        snapshot.forEach((symbol,frequency) -> queue.add(new Node(frequency, symbol, symbol, null, null)));
        if (queue.isEmpty()) { return new Codebook(Map.of(), BigInteger.ZERO); }
        while (queue.size() > 1) {
            Node left = queue.remove();
            Node right = queue.remove();
            queue.add(new Node(left.weight() + right.weight(), Math.min(left.minimumSymbol(), right.minimumSymbol()), null, left, right));
        }
        Map<Integer,String> codes = new TreeMap<>();
        ArrayDeque<Frame> stack = new ArrayDeque<>();
        stack.push(new Frame(queue.remove(), ""));
        BigInteger cost = BigInteger.ZERO;
        while (!stack.isEmpty()) {
            Frame frame = stack.pop();
            Node node = frame.node();
            if (node.symbol() != null) {
                String code = frame.prefix().isEmpty() ? "0" : frame.prefix();
                codes.put(node.symbol(), code);
                cost = cost.add(BigInteger.valueOf(node.weight()).multiply(BigInteger.valueOf(code.length())));
            } else {
                stack.push(new Frame(node.right(), frame.prefix() + "1"));
                stack.push(new Frame(node.left(), frame.prefix() + "0"));
            }
        }
        return new Codebook(codes, cost);
    }

    /** Exact optimum cost only, with repeated heap offers/removals; no codewords. */
    public static BigInteger costWithHeap(int[] frequencies) {
        validate(frequencies);
        if (frequencies.length < 2) { return baseCost(frequencies); }
        PriorityQueue<Long> queue = new PriorityQueue<>();
        for (int frequency : frequencies) { queue.add((long) frequency); }
        BigInteger cost = BigInteger.ZERO;
        while (queue.size() > 1) {
            long weight = queue.remove() + queue.remove();
            cost = cost.add(BigInteger.valueOf(weight));
            queue.add(weight);
        }
        return cost;
    }

    /**
     * Same exact cost via a sorted private leaf array and monotone internal queue.
     * Sorting is included, then there are n-1 constant-cursor merges. Input stays
     * unchanged; O(n) arrays, O(n log n) worst comparison preprocessing plus
     * linear merging and BigInteger accumulation. No codewords are produced.
     */
    public static BigInteger costWithTwoQueues(int[] frequencies) {
        validate(frequencies);
        if (frequencies.length < 2) { return baseCost(frequencies); }
        int[] leaves = frequencies.clone();
        Arrays.sort(leaves);
        long[] merged = new long[leaves.length - 1];
        int leaf = 0;
        int head = 0;
        int tail = 0;
        BigInteger cost = BigInteger.ZERO;
        for (int merge = 0; merge < leaves.length - 1; merge++) {
            long weight = 0;
            for (int take = 0; take < 2; take++) {
                if (leaf < leaves.length && (head == tail || leaves[leaf] <= merged[head])) {
                    weight += leaves[leaf++];
                } else {
                    weight += merged[head++];
                }
            }
            merged[tail++] = weight;
            cost = cost.add(BigInteger.valueOf(weight));
        }
        return cost;
    }

    private static BigInteger baseCost(int[] frequencies) {
        return frequencies.length == 0 ? BigInteger.ZERO : BigInteger.valueOf(frequencies[0]);
    }

    private static void validate(int[] frequencies) {
        Objects.requireNonNull(frequencies, "frequencies must not be null");
        for (int frequency : frequencies) { requirePositive(frequency); }
    }

    private static void requirePositive(int frequency) {
        if (frequency <= 0) { throw new IllegalArgumentException("frequencies must be positive"); }
    }
}
