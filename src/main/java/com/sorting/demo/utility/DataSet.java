package com.sorting.demo.utility;

import org.springframework.stereotype.Component;

/**
 * The type Data set.
 */
@Component
public class DataSet {

    private int dataSize;
    private int loops;

    /**
     * Instantiates the class using an empty constructor
     */
    public DataSet() {
    }

    /**
     * Gets the size of the array
     *
     * @return the size of the array
     */
    public int getDataSize() {
        return dataSize;
    }

    /**
     * Sets the size of the array
     *
     * @param dataSize value to set the array size to
     */
    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    /**
     * Gets the number of times the program loops through the sorting algorithms
     *
     * @return the value that represents the number of sorting loops
     */
    public int getLoops() {
        return loops;
    }

    /**
     * Gets the number of times the program loops through the sorting algorithms
     *
     * @param loops the value that represents the number of sorting loops
     */
    public void setLoops(int loops) {
        this.loops = loops;
    }

    /**
     * Prints the number of loops and data size used for the sorting algorithms
     *
     */
    @Override
    public String toString() {
        return "DataSet{" +
                "dataSize=" + dataSize +
                ", loops=" + loops +
                '}';
    }
}