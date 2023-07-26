package com.sorting.demo.utility;

import com.sorting.demo.data.ListArray;
import org.springframework.stereotype.Component;

/**
 * Class used for merge sorting data in the ListArray
 */
@Component
public class MergeSorter {

    /**
     * Instantiates the class using an empty constructor
     */
    public MergeSorter() {}

    /**
     * Entry into the merge sort algorithm.
     * The left and right endpoints are set and
     * called in the method mergeSortHelper
     *
     * @param data includes array to be sorted
     */
    public void mergeSort(ListArray data){

        data.setSortType(1);

        int left = 0;
        int right = data.size() - 1;

        mergeSortHelper(data, left, right);
    }

    private void mergeSortHelper(ListArray storage, int left, int right)
    {

        if (left < right) {
            // Find mid
            int mid = left + (right - left) / 2;
 
            // Sort each half
            mergeSortHelper(storage, left, mid);
            mergeSortHelper(storage, mid + 1, right);
 
            // Merge halves after sorting
            merge(storage, left, mid, right);
        }
    }
    
    private void merge(ListArray storage, int left, int mid, int right)
    {
        // Get sizes
        int num1 = mid - left + 1;
        int num2 = right - mid;

        int[] leftArray = new int[num1];
        int[] rightArray = new int[num2];
 
        // Data copied to arrays
        for (int i = 0; i < num1; ++i)
            leftArray[i] = storage.get(left + i);
        for (int j = 0; j < num2; ++j)
            rightArray[j] = storage.get(mid + 1 + j);
 
        // Merge the Arrays
 
        int index = 0, jdex = 0;

        int k = left;
        while (index < num1 && jdex < num2) {
            if (leftArray[index] <= rightArray[jdex]) {
                storage.set(k, leftArray[index]);
                index++;
            }
            else {
                storage.set(k, rightArray[jdex]);
                jdex++;
            }
            k++;
        }
 
        // Remaining elements of leftArray copied
        while (index < num1) {
            storage.set(k, leftArray[index]);
            index++;
            k++;
        }
 
        // Remaining elements of rightArray copied
        while (jdex < num2) {
           storage.set(k, rightArray[jdex]);
            jdex++;
            k++;
        }
    }
}

