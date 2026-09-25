package org.anaalvarezdev.algorithms.arrays;
/**
 * Problem:
 * Given an integer array, its current logical size, and a valid
 * index, remove the element stored at that logical position.
 *
 * Input:
 * - An integer array.
 * - The current logical size.
 * - A valid deletion index.
 *
 * Output:
 * - The new logical size after deletion.
 * - The input array is modified in place.
 *
 * Precondition:
 * - array != null
 * - 0 < size <= array.length
 * - 0 <= index < size
 *
 * Postcondition:
 * - The element originally stored at index is removed
 *   from the logical sequence.
 * - Elements originally stored in positions [index + 1, size - 1]
 *   are shifted one position to the left.
 * - Elements before index remain unchanged.
 * - The new logical size is size - 1.
 * - The vacated position at size - 1 is reset to 0.
 *
 * Algorithmic Strategy:
 * Left shifting followed by logical-size reduction.
 *
 * Time Complexity:
 * - End deletion: Θ(1)
 * - Middle deletion: Θ(n)
 * - Beginning deletion: Θ(n)
 * - Worst case: Θ(n)
 *
 * Space Complexity:
 * - Θ(1)
 *
 * Benchmarking:
 * - Required.
 * - Benchmarking is used to compare deletion at the beginning,
 *   middle, and end of the logical array.
 */
public class DeleteAtIndex {
    private DeleteAtIndex() {
    }

    /**
     * Deletes the element at the specified logical index.
     *
     * @param array the integer array containing the elements
     * @param size the current logical number of elements
     * @param index the logical index to delete
     * @return the new logical size
     */
    public static int delete(
            int[] array,
            int size,
            int index
    ) {

        if (array == null) {
            throw new NullPointerException("Array must not be null");
        }

        if (size <= 0 || size > array.length) {
            throw new IllegalArgumentException(
                    "Logical size must be between 1 and array.length"
            );
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Deletion index must be between 0 and size - 1"
            );
        }

        for (int current = index; current < size - 1; current++) {
            array[current] = array[current + 1];
        }

        array[size - 1] = 0;

        return size - 1;
    }
}
