package com.sorting.demo.utility;

import com.sorting.demo.data.ListArray;
import org.springframework.stereotype.Component;

/**
 * Class used for bubble sorting data in the ListArray
 */
@Component
public class BubbleSorter {

    /**
     * Instantiates the class using an empty constructor
     */
    public BubbleSorter() {}

    /**
     * Algorithm for sorting the array with-in ListArray
     * using Bubble Sort
     *
     * @param data contains the array to be bubble sorted
     */
    public void bubbleSort(ListArray data){

        // Set the sorting type
        data.setSortType(2);

        int index;
        int j;
        int temp;
        int size = data.size();
        boolean swapped;

        for (index = 0; index < size - 1; index++) {
            swapped = false;
            for (j = 0; j < size - index - 1; j++) {
                if (data.get(j) > data.get(j + 1)){

                    // Swap data(j) and  data(j+1)
                    temp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, temp);
                    swapped = true;
                }
            }

            // Break if swapping didn't occur
            if (!swapped)
                break;
        }
    }

}
