package com.sorting.demo.runner;

import com.sorting.demo.data.ListArray;
import com.sorting.demo.service.SortService;
import com.sorting.demo.utility.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;


/**
 * Temporary Class that is currently disabled. It was used for instantly adding database entries when
 * the program is started.
 */
//@Component  // Disables AppRunner
public class AppRunner implements CommandLineRunner {

    @Autowired
    private SortService sortService;
    /**
     * Auto wires utility to use its randomize data method
     */
    @Autowired
    public Utility utility;

    /**
     * Data structure used to store the data for sorting
     */
    public ListArray[] list;

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    @Override
    public void run(String... args) throws Exception {
        int dataSize = 9999;
        int loops = 30;

        list = new ListArray[4];

        // Each loop is a group of sorting algorithms
        // Every time a loop occurs, each batch of sorting
        // is given a group number
        for(int i = 0; i < loops; i++){

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

            // Run threaded sorting algorithms
            CompletableFuture<Void> temp1 = sortService.sorting(list[0], "Bubble", i);
            CompletableFuture<Void> temp2 = sortService.sorting(list[1], "Merge", i);
            CompletableFuture<Void> temp3 = sortService.sorting(list[2], "Quick", i);
            CompletableFuture<Void> temp4 = sortService.sorting(list[3], "Heap", i);

            // Join all threads and await completion
            CompletableFuture.allOf(temp1, temp2, temp3, temp4);

            // Output total sorting time to logger
            logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        }
    }
}
