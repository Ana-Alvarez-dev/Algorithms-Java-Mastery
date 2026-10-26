package org.anaalvarezdev.algorithms.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/** Assigns every interval to the minimum number of rooms; no interval is discarded. */
public final class IntervalPartitioning {
    private IntervalPartitioning() { }
    private record Entry(Interval interval, int index) { }
    private record Room(int finish, int id) { }

    /** Room ids are zero-based and positions refer to the original input order. */
    public record Assignment(List<Integer> roomByInputIndex, int roomCount) {
        public Assignment { roomByInputIndex = List.copyOf(roomByInputIndex); }
    }

    /**
     * Snapshots/validates input, orders by start/finish/input index, then reuses
     * the room finishing earliest (room id breaks ties). Half-open touching
     * intervals can share a room. Null list/items throw NullPointerException.
     * Input is preserved; empty input gives zero rooms and an empty assignment.
     * O(n log n) worst work and O(n) state including the returned assignment.
     */
    public static Assignment assign(List<Interval> intervals) {
        List<Interval> snapshot = List.copyOf(intervals);
        List<Entry> ordered = new ArrayList<>(snapshot.size());
        int index = 0;
        for (Interval interval : snapshot) { ordered.add(new Entry(interval, index++)); }
        ordered.sort(Comparator.comparingInt((Entry e) -> e.interval().start())
                .thenComparingInt(e -> e.interval().finish()).thenComparingInt(Entry::index));
        PriorityQueue<Room> rooms = new PriorityQueue<>(Comparator.comparingInt(Room::finish).thenComparingInt(Room::id));
        List<Integer> assignment = new ArrayList<>(Collections.nCopies(snapshot.size(), 0));
        int roomCount = 0;
        for (Entry entry : ordered) {
            Room available = rooms.peek();
            int id = available != null && available.finish() <= entry.interval().start()
                    ? rooms.remove().id() : roomCount++;
            assignment.set(entry.index(), id);
            rooms.add(new Room(entry.interval().finish(), id));
        }
        return new Assignment(assignment, roomCount);
    }
}
