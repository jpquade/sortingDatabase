package com.sorting.demo.entity;

import jakarta.persistence.*;

/**
 * The Sort Entity is a class used to interact with the
 * database and insert data
 */
@Entity
@Table(name="sort")
public class SortEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="sort_name")
    private String sortName;

    @Column(name="time")
    private double time;

    @Column(name="group_number")
    private int groupNumber;

    /**
     * Empty constructor for instantiating the SortEntity class
     */
    public SortEntity() {
    }

    /**
     * Constructor instantiating the SortEntity Class with the name of the sorting used, the
     * amount of time it took to sort the array. GroupNumber is instantiated with the current
     * group this entity is batched with.
     *
     * @param sortName    name of sorting algorithm used
     * @param time        is the amount of time it took to sort data
     * @param groupNumber is the looping batch in which this sorting occurred
     */
    public SortEntity(String sortName, double time, int groupNumber) {
        this.sortName = sortName;
        this.time = time;
        this.groupNumber = groupNumber;
    }

    /**
     * Gets the entity id
     *
     * @return the entity id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the entity id
     *
     * @param id value which the current entity id will be set to
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the type of sorting used in this entity.
     *
     * @return the name of the type of sorting
     */
    public String getSortName() {
        return sortName;
    }

    /**
     * Sets the name of the type of sorting used in this entity.
     *
     * @param sortName name of sorting algorithm used for this entity
     */
    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    /**
     * Gets the amount of time it took to sort the built-in array of ListArray
     *
     * @return the sorting time
     */
    public double getTime() {
        return time;
    }

    /**
     * Sets the sorting time
     *
     * @param time value of sorting time to be set to
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     * Gets the batch number this entity was grouped with
     *
     * @return the current groupNumber associated with this entity
     */
    public int getGroupNumber() {
        return groupNumber;
    }

    /**
     * Sets the batch number this entity was grouped with
     *
     * @param groupNumber the value the current group number will be set to
     */
    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    /**
     * Prints the id, Sorting name, sorting time, and groupNumber associated
     * with this entity
     *
     */
    @Override
    public String toString() {
        return "Sort{" +
                "id=" + id +
                ", sortName='" + sortName + '\'' +
                ", time='" + time + '\'' +
                ", groupNumber='" + groupNumber + '\'' +
                '}';
    }
}
