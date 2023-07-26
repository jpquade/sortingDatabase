package com.sorting.demo.utility;

import com.sorting.demo.data.ListArray;
import com.sorting.demo.entity.SortEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CompletableFuture;


/**
 * The Utility class gives access to the methods randomize and
 * sorting.
 */
@Component
public class Utility {
    private final Random rand = new Random();
    private final MergeSorter mergeSorter;
    private final BubbleSorter bubbleSorter;
    private final QuickSorter quickSorter;
    private final HeapSorter heapSorter;
    /**
     * Logs thread number and amount of time sorting took to complete.
     */
    Logger logger = LoggerFactory.getLogger(Utility.class);


    /**
     * Instantiates a new Utility object auto wiring all the sorting classes
     *
     * @param mergeSorter  object containing the merge sort algorithm
     * @param bubbleSorter object containing the bubble sort algorithm
     * @param quickSorter  object containing the quick sort algorithm
     * @param heapSorter   object containing the heap sort algorithm
     */
    @Autowired
    public Utility(MergeSorter mergeSorter, BubbleSorter bubbleSorter, QuickSorter quickSorter, HeapSorter heapSorter) {

        this.mergeSorter = mergeSorter;
        this.bubbleSorter = bubbleSorter;
        this.quickSorter = quickSorter;
        this.heapSorter = heapSorter;
    }

    /**
     * Randomize creates randomized data for the array called by the method.
     *
     * @param listArray contains the array which randomized data will be inserted into
     * @return the ListArray object with its built-in array containing randomized data
     */
    public ListArray randomize(ListArray listArray){

        for(int i = 0; i < listArray.size(); i++){
            listArray.set(i, rand.nextInt(1000000000));
        }
        return listArray;
    }

    /**
     * The Sorting method calls a ListArray object containing unsorted data. The other method
     * calls are for setting the algorithm used to sort and also the group or batch of sorting that
     * is going to occur in a single loop
     *
     * @param listArray contains the array of data that will be sorted
     * @param type      name of the type of sorting algorithm to be used
     * @param group     the number associated with which batch this sorting will take place
     * @return the sorted entity populated with the sorting type used, sorting time, and group number
     */
    @Async
    public CompletableFuture<SortEntity> sorting(ListArray listArray, String type, int group){

        // Capture starting time of sorting
        long start = System.nanoTime();

        // Sort the data
        switch(type){
            case "Merge" -> mergeSorter.mergeSort(listArray);
            case "Bubble" -> bubbleSorter.bubbleSort(listArray);
            case "Quick" -> quickSorter.quickSort(listArray);
            case "Heap" -> heapSorter.heapSort(listArray);
        }

        // Capture sorting time
        long end = System.nanoTime() - start;

        // After sorting set the sorting type
        String sortType;

        // Add name of sorting to database entry
        switch (listArray.getSortType()) {
            case 1 -> sortType = ("Merge");
            case 2 -> sortType = ("Bubble");
            case 3 -> sortType = ("Quick");
            case 4 -> sortType = ("Heap");
            default -> sortType = ("UnSorted");
        }

        // Log thread info
        logger.info("Saving sorting info: Sorting time - " + end + "ns : " + Thread.currentThread().getName());

        // Return data to database
        return CompletableFuture.completedFuture(new SortEntity(sortType, end, group));
    }
}