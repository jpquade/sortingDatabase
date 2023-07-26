package com.sorting.demo.utility;

import com.sorting.demo.data.ListArray;
import org.springframework.stereotype.Component;

/**
 * Class used for quick sorting data in the ListArray
 */
@Component
public class QuickSorter {

    /**
     * Instantiates the class using an empty constructor
     */
    public QuickSorter() {
    }

    /**
     * Quick sort entry point. It calls a ListArray object containing
     * the array to be sorted.
     *
     * @param data contains the array to be sorted
     */
    public void quickSort(ListArray data) {
        quickSortHelper(data, 0, data.size() - 1);
    }

    private void quickSortHelper(ListArray data, int low, int high){

        if (low < high) {

            data.setSortType(3);

            // Find partition index
            int pi = partition(data, low, high);

            // Elements on both sides of partition sorted
            quickSortHelper(data, low, pi - 1);
            quickSortHelper(data, pi + 1, high);
        }
    }

    // Swap two elements
    private void swap(ListArray storage, int i, int j){
        int temp = storage.get(i);
        storage.set(i, storage.get(j));
        storage.set(j, temp);
    }

    // Sort around pivot
    private int partition(ListArray storage, int low, int high){

        // Get the pivot
        int pivot = storage.get(high);

        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // Compare current element
            if (storage.get(j) < pivot) {

                i++;
                swap(storage, i, j);
            }
        }
        swap(storage, i + 1, high);
        return (i + 1);
    }
}