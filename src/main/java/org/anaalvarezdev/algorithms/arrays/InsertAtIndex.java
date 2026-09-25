package org.anaalvarezdev.algorithms.arrays;
/**
 * Problem:
 * Given an integer array with available capacity, its current
 * logical size, an insertion index, and a value, insert the value
 * at the specified logical position.
 *
 * Input:
 * - An integer array with available capacity.
 * - The current logical size.
 * - A valid insertion index.
 * - The value to insert.
 *
 * Output:
 * - The new logical size after insertion.
 * - The input array is modified in place.
 *
 * Precondition:
 * - array != null
 * - 0 <= size < array.length
 * - 0 <= index <= size
 *
 * Postcondition:
 * - array[index] == value
 * - Elements originally in positions [index, size - 1]
 *   are shifted one position to the right.
 * - Elements before index remain unchanged.
 * - The new logical size is size + 1.
 *
 * Algorithmic Strategy:
 * Right shifting followed by indexed insertion.
 *
 * Time Complexity:
 * - End insertion: Θ(1)
 * - Middle insertion: Θ(n)
 * - Beginning insertion: Θ(n)
 * - Worst case: Θ(n)
 *
 * Space Complexity:
 * - Θ(1)
 *
 * Benchmarking:
 * - Required.
 * - Benchmarking is used to compare insertion at the beginning,
 *   middle, and end of the logical array.
 */
public class InsertAtIndex {
    private InsertAtIndex() {
    }

    /**
     * Inserts a value at the specified logical index.
     *
     * @param array the array containing available capacity
     * @param size the current logical number of elements
     * @param index the logical index where the value will be inserted
     * @param value the value to insert
     * @return the new logical size
     */
    public static int insert(
            int[] array,
            int size,
            int index,
            int value
    ) {

        if (array == null) {
            throw new NullPointerException("Array must not be null");
        }

        if (size < 0 || size >= array.length) {
            throw new IllegalArgumentException(
                    "Logical size must be between 0 and array.length - 1"
            );
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Insertion index must be between 0 and logical size"
            );
        }

        for (int current = size; current > index; current--) {
            array[current] = array[current - 1];
        }

        array[index] = value;

        return size + 1;
    }
}
