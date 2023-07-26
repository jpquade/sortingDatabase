package com.sorting.demo.service;

import com.sorting.demo.data.ListArray;
import com.sorting.demo.entity.SortEntity;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * The interface Sort service.
 */
public interface SortService {

    /**
     * Gets all entries and returns a list
     *
     * @return the list of entries
     */
    public List<SortEntity> findAll();

    /**
     * Finds a database entry from an id
     *
     * @param id id to find in the database
     * @return the entry if found
     */
    public Optional<SortEntity> findId(int id);

    /**
     * Entry is deleted by id if found
     *
     * @param id id to find in the database
     */
    public void deleteById(int id);

    /**
     * Saves a new created entry from user
     *
     * @param theSort entity data to be saved to the database
     */
    public void save(SortEntity theSort);

    /**
     * Uses four threaded sorting algorithms to process data
     * This is used in the AppRunner Class
     *
     * @param listArray the data including array to be sorted
     * @param type      the type of sorting algorithm used
     * @param group     the number of the batch the set of algorithms were used in
     * @return a null value. The method uses CompletableFuture as a return acting like a void method
     * @throws CloneNotSupportedException the exception is thrown when CompletableFuture get returns an invalid
     * value
     */
    public CompletableFuture<Void> sorting(ListArray listArray, String type, int group) throws CloneNotSupportedException;

    /**
     * Uses four threaded sorting algorithms to process data
     * This service method is called by an HTTP POST
     *
     * @param dataSize value representing the number of elements in the array
     * @param loops    each loop represents a single run of all sorting algorithms as a group
     * @throws ExecutionException   exception when data fails to be added to the database
     * @throws InterruptedException interrupted exception happens when a failure to add database occurs
     */
    public void calculateSorting(int dataSize, int loops) throws ExecutionException, InterruptedException;
}
