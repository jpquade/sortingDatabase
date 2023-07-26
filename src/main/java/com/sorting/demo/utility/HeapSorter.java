package com.sorting.demo.utility;

import com.sorting.demo.data.ListArray;
import org.springframework.stereotype.Component;

/**
 * Class used for heap sorting data in the ListArray
 */
@Component
public class HeapSorter {
    /**
     * Algorithm for sorting the array within ListArray
     * using Heap Sort
     *
     * @param data contains the array to be heap sorted
     */
    public void heapSort(ListArray data){

        // Set the sorting type
        data.setSortType(4);

        int size = data.size();

        // Rearrange array
        for (int i = size / 2 - 1; i >= 0; i--)
            heapify(data.getArr(), size, i);

        // Each element is extracted
        for (int i = size - 1; i >= 0; i--) {

            // Move current item to end
            int temp = data.get(0);
            data.set(0, data.get(i));
            data.set(i, temp);

            // Heapify heap
            heapify(data.getArr(), i, 0);
        }
    }

    /**
     * Heapify.
     *
     * @param arr array to be sorted through the heapify method
     * @param size   size of array
     * @param i    element where heapsort is currently at in the array
     */
// Heapify subtree rooted with node
    void heapify(int arr[], int size, int i){
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        // If root is smaller than left
        if (l < size && arr[l] > arr[largest])
            largest = l;

        // If right child is largest
        if (r < size && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Heapify tree
            heapify(arr, size, largest);
        }
    }
}
