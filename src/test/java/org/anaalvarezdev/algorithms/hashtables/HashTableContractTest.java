package org.anaalvarezdev.algorithms.hashtables;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.SplittableRandom;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

abstract class HashTableContractTest {
    protected abstract <K,V> HashTable<K,V> create(int capacity);
    protected abstract boolean valid(HashTable<?,?> table);

    @Test void shouldStartEmptyAndRepresentAbsenceUnambiguously() {
        HashTable<String,Integer> table = create(1);
        assertThat(table.get("a")).isNull(); assertThat(table.remove("a")).isNull();
        assertThat(table.containsKey("a")).isFalse(); assertState(table, Map.of());
    }

    @Test void shouldReturnPreviousValuesWithoutAddingDuplicateKeys() {
        HashTable<String,Integer> table = create(4);
        assertThat(table.put("a",1)).isNull(); assertThat(table.put("a",2)).isEqualTo(1);
        assertThat(table.put(new String("a"),3)).isEqualTo(2);
        assertThat(table.get("a")).isEqualTo(3); assertState(table,Map.of("a",3));
    }

    @Test void shouldRetainOriginalEqualKeyAndValueIdentity() {
        HashTable<String,StringBuilder> table = create(4);
        String key = new String("key"); StringBuilder first = new StringBuilder("one");
        StringBuilder second = new StringBuilder("two");
        table.put(key, first); assertThat(table.put(new String("key"), second)).isSameAs(first);
        Map<String,StringBuilder> copy = table.toMap();
        assertThat(copy.keySet().iterator().next()).isSameAs(key);
        assertThat(copy.get(key)).isSameAs(second);
        assertThat(table.remove(new String("key"))).isSameAs(second);
        assertThat(valid(table)).isTrue();
    }

    @Test void shouldRejectNullQueriesAndWritesWithoutMutation() {
        HashTable<String,Integer> table = create(4); table.put("a",1); table.put("b",2);
        int capacity = table.capacity();
        assertThatThrownBy(() -> table.put(null,3)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> table.put("c",null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> table.put("a",null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> table.get(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> table.remove(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> table.containsKey(null)).isInstanceOf(NullPointerException.class);
        assertThat(table.capacity()).isEqualTo(capacity); assertState(table,Map.of("a",1,"b",2));
    }

    @Test void shouldPermitRepeatedValuesForDifferentKeys() {
        HashTable<String,Integer> table = create(4);
        table.put("a",7); table.put("b",7);
        assertThat(table.remove("a")).isEqualTo(7);
        assertState(table,Map.of("b",7));
    }

    @Test void shouldRemoveEveryCollidingKeyAndPermitReinsertion() {
        HashTable<Key,Integer> table = create(4);
        for (int id=0;id<100;id++) { table.put(new Key(id,7),id); }
        for (int id=0;id<100;id+=2) { assertThat(table.remove(new Key(id,7))).isEqualTo(id); }
        for (int id=1;id<100;id+=2) { assertThat(table.get(new Key(id,7))).isEqualTo(id); }
        assertThat(valid(table)).isTrue();
        for (int id=1;id<100;id+=2) { assertThat(table.remove(new Key(id,7))).isEqualTo(id); }
        assertState(table,Map.of()); table.put(new Key(101,7),9);
        assertState(table,Map.of(new Key(101,7),9));
    }

    @Test void shouldPreserveAllReferencesThroughRepeatedGrowth() {
        HashTable<Integer,StringBuilder> table = create(1);
        Map<Integer,StringBuilder> expected = new HashMap<>();
        for (int key=0;key<1000;key++) {
            StringBuilder value = new StringBuilder(Integer.toString(key));
            expected.put(key,value); table.put(key,value);
        }
        assertThat(table.capacity()).isGreaterThan(4);
        for (int key=0;key<1000;key++) { assertThat(table.get(key)).isSameAs(expected.get(key)); }
        assertState(table,expected);
    }

    @Test void shouldExposeIndependentMutableShallowSnapshots() {
        HashTable<String,StringBuilder> table = create(4);
        StringBuilder value = new StringBuilder("a"); table.put("a",value);
        Map<String,StringBuilder> copy = table.toMap(); copy.put("b",new StringBuilder("b"));
        assertThat(table.containsKey("b")).isFalse(); assertThat(copy.get("a")).isSameAs(value);
        table.clear(); assertThat(copy).hasSize(2); copy.clear(); assertThat(table.isEmpty()).isTrue();
    }

    @Test void shouldClearRetainCapacityAndPermitReuse() {
        HashTable<Integer,Integer> table = create(1); table.clear();
        for (int key=0;key<50;key++) { table.put(key,key); }
        int capacity=table.capacity(); table.clear(); table.clear();
        assertThat(table.capacity()).isEqualTo(capacity); assertState(table,Map.of());
        table.put(8,9); assertState(table,Map.of(8,9));
    }

    @Test void shouldHandleNegativeHashesAndIntegerExtremes() {
        HashTable<Key,Integer> table=create(4); Map<Key,Integer> model=new HashMap<>();
        int id=0;
        for(int hash:new int[]{Integer.MIN_VALUE,-1,0,Integer.MAX_VALUE}) {
            Key key=new Key(id++,hash); table.put(key,hash); model.put(key,hash);
        }
        for(Key key:model.keySet()) { assertThat(table.get(new Key(key.id(),key.hash()))).isEqualTo(model.get(key)); }
        assertState(table,model);
    }

    @Test void shouldKeepInstancesIndependent() {
        HashTable<String,Integer> a=create(4),b=create(4); a.put("a",1); b.put("b",2); a.clear();
        assertState(a,Map.of()); assertState(b,Map.of("b",2));
    }

    @Test void shouldMatchHashMapThroughSeededCollisionAndSpreadSequences() {
        for(boolean collide:new boolean[]{false,true}) {
            HashTable<Key,Integer> table=create(1); Map<Key,Integer> model=new HashMap<>();
            SplittableRandom random=new SplittableRandom(20261019);
            for(int step=0;step<2500;step++) {
                int id=random.nextInt(64); Key key=new Key(id,collide?7:id);
                int operation=random.nextInt(20);
                if(operation<10) { int value=random.nextInt(); assertThat(table.put(key,value)).isEqualTo(model.put(key,value));
                } else if(operation<15) { assertThat(table.remove(key)).isEqualTo(model.remove(key));
                } else if(operation<18) { assertThat(table.get(key)).isEqualTo(model.get(key));
                } else if(operation==18) { assertThat(table.containsKey(key)).isEqualTo(model.containsKey(key));
                } else { table.clear(); model.clear(); }
                assertState(table,model);
            }
        }
    }

    private <K,V> void assertState(HashTable<K,V> table,Map<K,V> expected) {
        assertThat(valid(table)).isTrue(); assertThat(table.size()).isEqualTo(expected.size());
        assertThat(table.isEmpty()).isEqualTo(expected.isEmpty()); assertThat(table.toMap()).isEqualTo(expected);
    }

    protected record Key(int id,int hash) { @Override public int hashCode() { return hash; } }
}
