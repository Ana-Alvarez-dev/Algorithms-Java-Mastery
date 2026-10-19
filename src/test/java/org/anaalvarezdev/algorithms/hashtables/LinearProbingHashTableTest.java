package org.anaalvarezdev.algorithms.hashtables;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class LinearProbingHashTableTest extends HashTableContractTest {
    @Override protected <K,V> HashTable<K,V> create(int capacity) { return new LinearProbingHashTable<>(capacity); }
    @Override protected boolean valid(HashTable<?,?> table) { return ((LinearProbingHashTable<?,?>)table).hasValidStructure(); }

    @Test void shouldPreserveWrappedProbePathsAcrossDeletion() {
        LinearProbingHashTable<Key,Integer> table=new LinearProbingHashTable<>(8);
        for(int key=0;key<4;key++) { table.put(new Key(key,6),key); }
        assertThat(table.remove(new Key(1,6))).isEqualTo(1);
        assertThat(table.remove(new Key(0,6))).isZero();
        assertThat(table.get(new Key(2,6))).isEqualTo(2); assertThat(table.get(new Key(3,6))).isEqualTo(3);
        assertThat(table.get(new Key(99,6))).isNull(); assertThat(table.hasValidStructure()).isTrue();
    }

    @Test void shouldUpdateBeyondTombstoneRatherThanInsertDuplicateKey() {
        LinearProbingHashTable<Key,Integer> table=new LinearProbingHashTable<>(8);
        for(int key=0;key<3;key++) { table.put(new Key(key,0),key); }
        table.remove(new Key(1,0));
        assertThat(table.put(new Key(2,0),20)).isEqualTo(2);
        assertThat(table.size()).isEqualTo(2); assertThat(table.get(new Key(2,0))).isEqualTo(20);
        assertThat(table.deletedSlots()).isEqualTo(1); assertThat(table.hasValidStructure()).isTrue();
    }

    @Test void shouldReuseDeletedSlotsAndClearReferences() {
        LinearProbingHashTable<Key,Integer> table=new LinearProbingHashTable<>(16);
        for(int key=0;key<6;key++) { table.put(new Key(key,0),key); }
        table.remove(new Key(1,0));table.remove(new Key(2,0));
        table.put(new Key(10,0),10);table.put(new Key(11,0),11);
        assertThat(table.deletedSlots()).isZero(); assertThat(table.size()).isEqualTo(6);
        assertThat(table.hasValidStructure()).isTrue();
    }

    @Test void shouldCompactAtUsedThresholdWithoutGrowingLivePopulation() {
        LinearProbingHashTable<Integer,Integer> table=new LinearProbingHashTable<>(16);
        for(int key=0;key<8;key++) { table.put(key,key); }
        for(int key=0;key<4;key++) { table.remove(key);table.put(key+8,key+8); }
        assertThat(table.deletedSlots()).isEqualTo(4);
        table.remove(4);table.put(12,12);
        assertThat(table.capacity()).isEqualTo(16); assertThat(table.deletedSlots()).isZero();
        assertThat(table.size()).isEqualTo(8);
        for(int key=5;key<=12;key++) { assertThat(table.get(key)).isEqualTo(key); }
        assertThat(table.hasValidStructure()).isTrue();
    }

    @Test void shouldGrowAtHalfLiveLoadButNotForReplacement() {
        LinearProbingHashTable<Integer,Integer> table=new LinearProbingHashTable<>(4);
        table.put(1,1);table.put(2,2);table.put(1,7);
        assertThat(table.capacity()).isEqualTo(4);table.put(3,3);
        assertThat(table.capacity()).isEqualTo(8);assertThat(table.get(1)).isEqualTo(7);
        assertThat(table.hasValidStructure()).isTrue();
    }

    @Test void shouldHandleEmptyTableWithMarkersAndClearThemOnExplicitClear() {
        LinearProbingHashTable<Integer,Integer> table=new LinearProbingHashTable<>(8);
        for(int key=0;key<4;key++) { table.put(key,key); }
        for(int key=0;key<4;key++) { table.remove(key); }
        assertThat(table.deletedSlots()).isEqualTo(4); assertThat(table.get(0)).isNull();
        assertThat(table.remove(100)).isNull(); assertThat(table.hasValidStructure()).isTrue();
        table.clear();assertThat(table.deletedSlots()).isZero();assertThat(table.capacity()).isEqualTo(8);
        assertThat(table.hasValidStructure()).isTrue();
    }
}
