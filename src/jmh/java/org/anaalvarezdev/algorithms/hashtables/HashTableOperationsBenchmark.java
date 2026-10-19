package org.anaalvarezdev.algorithms.hashtables;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How do chained/probed manual tables and JDK HashMap compare for successful
 * lookup, absent lookup and remove/restore with distinct versus identical hashes?
 *
 * Theoretical Expectation:
 * Constant expected operations need hashing assumptions; manual collision-heavy
 * lookup is linear. The JDK reference can use comparison order in collision bins
 * and is not a pure academic chain. No universal constant bound is assumed.
 *
 * Measured Work:
 * Hit and remove/restore cycle equal-but-distinct query keys; key selection is
 * timed. Miss uses a fixed absent key. Trial construction/boxing are excluded.
 * Each table starts with the same reserved power-of-two capacity >= 2n.
 * A pair restores original key/value references, count and mappings, though
 * chain/tree placement can change. Node reinsertion/allocation sites are timed,
 * subject to JIT optimisation. Growth and accumulated tombstone cleanup are not.
 * DISTINCT_HASH is an ideal collision-free home-position fixture at these sizes;
 * CONSTANT_HASH deliberately forces one hash class. Neither is a random workload.
 *
 * Result:
 * Pending an author-supplied measurement run. Smoke checks are not research data.
 *
 * Interpretation:
 * Pending. Retain actual runtime, source commit, settings, means and errors.
 * Compare matching size/hash mode; do not infer allocation, memory footprint,
 * cache causes, amortised proofs or isolated deletion cost from paired scores.
 * Protocol: docs/08-hash-tables/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations = 3, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Fork(2)
public class HashTableOperationsBenchmark {
    @Param({"100", "1000", "10000"})
    private int size;
    @Param({"DISTINCT_HASH", "CONSTANT_HASH"})
    private String hashMode;

    private SeparateChainingHashTable<Key,Integer> chaining;
    private LinearProbingHashTable<Key,Integer> probing;
    private Map<Key,Integer> javaMap;
    private Key[] storedKeys;
    private Key[] queries;
    private Key absent;
    private int cursor;

    @Setup(Level.Trial)
    public void setup() {
        int capacity = Hashing.capacity(size * 2);
        chaining = new SeparateChainingHashTable<>(capacity);
        probing = new LinearProbingHashTable<>(capacity);
        javaMap = new HashMap<>(capacity,0.75f);
        storedKeys = new Key[size]; queries = new Key[size];
        boolean constant = hashMode.equals("CONSTANT_HASH");
        for (int index=0;index<size;index++) {
            Key key = new Key(index,constant); storedKeys[index] = key;
            queries[index] = new Key(index,constant);
            Integer value = index;
            chaining.put(key,value); probing.put(key,value); javaMap.put(key,value);
        }
        absent = new Key(size,constant); cursor = 0;
    }

    private Key nextQuery() {
        Key key = queries[cursor];
        cursor = cursor == size - 1 ? 0 : cursor + 1;
        return key;
    }

    @Benchmark public Integer chainingHit() { return chaining.get(nextQuery()); }
    @Benchmark public Integer probingHit() { return probing.get(nextQuery()); }
    @Benchmark public Integer javaMapHit() { return javaMap.get(nextQuery()); }
    @Benchmark public Integer chainingMiss() { return chaining.get(absent); }
    @Benchmark public Integer probingMiss() { return probing.get(absent); }
    @Benchmark public Integer javaMapMiss() { return javaMap.get(absent); }

    @Benchmark public Integer chainingRemoveRestore() {
        Key key = nextQuery(); Integer value = chaining.remove(key);
        chaining.put(storedKeys[key.id()],value); return value;
    }
    @Benchmark public Integer probingRemoveRestore() {
        Key key = nextQuery(); Integer value = probing.remove(key);
        probing.put(storedKeys[key.id()],value); return value;
    }
    @Benchmark public Integer javaMapRemoveRestore() {
        Key key = nextQuery(); Integer value = javaMap.remove(key);
        javaMap.put(storedKeys[key.id()],value); return value;
    }

    /** Stable fixture key; comparison is consistent with equality, including mode. */
    public record Key(int id,boolean constantHash) implements Comparable<Key> {
        @Override public int hashCode() { return constantHash ? 1 : id; }
        @Override public int compareTo(Key other) {
            int order = Integer.compare(id,other.id);
            return order != 0 ? order : Boolean.compare(constantHash,other.constantHash);
        }
    }
}
