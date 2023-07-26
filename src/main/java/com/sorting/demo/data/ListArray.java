package com.sorting.demo.data;

import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * The ListArray class includes methods expanding the capabilities
 * of the standard array. This class is able to store the sorting method
 * within this class.
 */
@Component
public class ListArray {

    // Array of values sorted or unsorted
    private int[] dataList;

    // Number designation for type of sorting used:
    // 0 - None, 1 - Merge, 2 - Bubble, 3 - Quick
    private int sortType;

    /**
     * Empty constructor for instantiating the ListArray class
     */
    public ListArray() {
    }

    /**
     * Instantiates the class and copies pre-created data into the internal array
     *
     * @param data the pre-created data to be copied into the internal array
     */
    public ListArray(int[] data) {

        this.dataList = data;
    }

    /**
     * Helper method for copying data into internal array
     *
     * @param listArray data to be copied into internal array
     */
    public ListArray(ListArray listArray){
        copy(listArray);
    }

    /**
     * Replaces existing element with another value
     *
     * @param number value to be inserted
     * @param index  location of value that will be changed
     */
    public void add(int number, int index){
        this.dataList[index] = number;
    }

    /**
     * Get the value at a specific element
     *
     * @param element location of value to be retrieved
     * @return the value from element location
     */
    public int get(int element) {
        return dataList[element];
    }

    /**
     * Gets the internal array within this class
     *
     * @return the array being returned
     */
    public int[] getArr(){
        return dataList;
    }

    /**
     * Gets type of sorting used on the internal array
     *
     * @return the sorting type
     */
    public int getSortType() {return sortType; }

    /**
     * Set an index location to a specific value.
     *
     * @param index  location in the data array
     * @param number value to set in the data array
     */
    public void set(int index, int number) {
        this.dataList[index] = number;
    }

    /**
     * The algorithm used to sort is set to a pre-assigned number
     *
     * @param sortType the number representation of the sorting used
     */
    public void setSortType(int sortType) { this.sortType = sortType; }

    /**
     * Returns the size of the internal array
     *
     * @return the size of the array is returned
     */
    public int size(){
        return dataList.length;
    }

    private void copy(ListArray arr) {
        dataList = arr.getArr();
    }

    @Override
    public String toString() {
        return "ListArray{" +
                "myData=" + Arrays.toString(dataList) +
                ", sortType=" + sortType +
                '}';
    }

    /**
     * Print the contents of the internal array
     */
    public void printAtATime(){
        for (int myDatum : dataList) {
            System.out.println(myDatum);
        }
    }
}
