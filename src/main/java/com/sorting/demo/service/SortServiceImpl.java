package com.sorting.demo.service;


import com.sorting.demo.dao.SortRepository;
import com.sorting.demo.data.ListArray;
import com.sorting.demo.entity.SortEntity;
import com.sorting.demo.utility.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * The SortServiceImpl implements all associated interface abstract
 * methods allowing for data creation, access, updating and deletion.
 */
@Service
public class SortServiceImpl implements SortService {

    private SortRepository sortRepository;
    private MergeSorter mergeSorter;
    private BubbleSorter bubbleSorter;
    private QuickSorter quickSorter;
    private HeapSorter heapSorter;
    private Utility utility;

    /**
     * Logs events that occurs with each thread
     */
    Logger logger = LoggerFactory.getLogger(SortServiceImpl.class);
    /**
     * Arrays of data used for sorting
     */
    public ListArray[] list;

    /**
     * Instantiates all required utility and data classes.
     *
     * @param sortRepository the sort repository
     * @param mergeSorter    the merge sorter
     * @param bubbleSorter   the bubble sorter
     * @param quickSorter    the quick sorter
     * @param heapSorter     the heap sorter
     * @param utility        the utility
     * @param list           the list
     */
    @Autowired
    public SortServiceImpl(SortRepository sortRepository, MergeSorter mergeSorter,
                           BubbleSorter bubbleSorter, QuickSorter quickSorter,
                           HeapSorter heapSorter, Utility utility, List[] list) {
        this.sortRepository = sortRepository;
        this.mergeSorter = mergeSorter;
        this.bubbleSorter = bubbleSorter;
        this.quickSorter = quickSorter;
        this.heapSorter = heapSorter;
        this.utility = utility;
        this.list = new ListArray[4];
    }

    /**
     * Instantiates an empty constructor
     */
    public SortServiceImpl() {}

    @Override
    public List<SortEntity> findAll() {
        return sortRepository.findAll();
    }

    @Override
    public Optional<SortEntity> findId(int id){ return sortRepository.findById(id);}

    @Override
    public void deleteById(int id) { sortRepository.deleteById(id);}

    @Override
    public void save(SortEntity theSort) {
        sortRepository.save(theSort);
    }

    @Async
    public CompletableFuture<Void> sorting(ListArray listArray, String type, int group){

        // Capture starting time of sorting
        long start = System.nanoTime();

        // Case that chooses which type of sorting to use
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

        // Save data to database
        sortRepository.save(new SortEntity(sortType, end, group));

        return null;
    }

    @Async
    public void calculateSorting(int dataSize, int loops) throws ExecutionException, InterruptedException {

        // Loop number of times to complexity results
        for(int i = 0; i < loops; i++) {

            // Set data size for the original list
            list[0] = new ListArray(new int[dataSize]);

            // Add and randomize data
            utility.randomize(list[0]);

            // Copy data into listArrays
            for (int index = 1; index < 4; index++) {
                list[index] = new ListArray(list[0]);
            }

            // Capture start of sorting time
            long start = System.currentTimeMillis();

            // Run sorting algorithms
            CompletableFuture<SortEntity> cf1 = utility.sorting(list[0], "Bubble", i);
            CompletableFuture<SortEntity> cf2 = utility.sorting(list[1], "Merge", i);
            CompletableFuture<SortEntity> cf3 = utility.sorting(list[2], "Quick", i);
            CompletableFuture<SortEntity> cf4 = utility.sorting(list[3], "Heap", i);


            // Join all threads and await completion
            CompletableFuture.allOf(cf1, cf2, cf3, cf4);

            // Save entries to the repository
            sortRepository.save(new SortEntity(cf1.get().getSortName(), cf1.get().getTime(), cf1.get().getGroupNumber()));
            sortRepository.save(new SortEntity(cf2.get().getSortName(), cf2.get().getTime(), cf2.get().getGroupNumber()));
            sortRepository.save(new SortEntity(cf3.get().getSortName(), cf3.get().getTime(), cf3.get().getGroupNumber()));
            sortRepository.save(new SortEntity(cf4.get().getSortName(), cf4.get().getTime(), cf4.get().getGroupNumber()));

            // Output total sorting time to logger
            logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        }
    }
}