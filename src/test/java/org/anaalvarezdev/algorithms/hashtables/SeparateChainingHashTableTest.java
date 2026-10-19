package org.anaalvarezdev.algorithms.hashtables;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SeparateChainingHashTableTest extends HashTableContractTest {
    @Override protected <K,V> HashTable<K,V> create(int capacity) { return new SeparateChainingHashTable<>(capacity); }
    @Override protected boolean valid(HashTable<?,?> table) { return ((SeparateChainingHashTable<?,?>)table).hasValidStructure(); }

    @Test void shouldRemoveInteriorHeadTailAndSingletonOfOneChain() {
        SeparateChainingHashTable<Key,Integer> table=new SeparateChainingHashTable<>(16);
        for(int key=0;key<4;key++) { table.put(new Key(key,5),key); }
        for(int key:new int[]{2,3,0,1}) {
            assertThat(table.remove(new Key(key,5))).isEqualTo(key);
            assertThat(table.hasValidStructure()).isTrue(); assertThat(table.containsKey(new Key(key,5))).isFalse();
        }
        assertThat(table.isEmpty()).isTrue();
    }

    @Test void shouldGrowOnlyForNewMappingsBeyondThreeQuarterThreshold() {
        SeparateChainingHashTable<Integer,Integer> table=new SeparateChainingHashTable<>(4);
        table.put(1,1);table.put(2,2);table.put(3,3);
        table.put(1,9); assertThat(table.capacity()).isEqualTo(4);
        table.put(4,4); assertThat(table.capacity()).isEqualTo(8);
        assertThat(table.get(1)).isEqualTo(9); assertThat(table.hasValidStructure()).isTrue();
    }
}
