package com.reliablecoders.examples;

import com.moback.android.MoBackFile;
import com.moback.android.MoBackObject;

/**
 * This is an example of a basic MoBack Table, it extends MoBackObject.
 */
public class MoBackObjectRowExampleTwo extends MoBackObject {

    /**
     * The names of columns of the tables on the database are directly linked to the names of your variables.
     */
    private String nameOfColumnOne;

    /**
     * You can have as many columns as your want for your objects.
     */
    private int nameOfColumnTwo;

    /**
     * MoBackFile is a specialized file meant for storing in tables/objects to keep a reference to files that have been uploaded to the server.
     * MoBackFiles are received in the callback after a successful upload or can be manually created.
     */
    private MoBackFile nameOfColumnThree;

    /**
     * Basic get and set functionality for storing information in your local MoBackObject before saving it to a MoBackTable.
     */
    public String getColumnOne() {
        return nameOfColumnOne;
    }
    public void setColumnOne(String nameOfColumnOne) {
        this.nameOfColumnOne = nameOfColumnOne;
    }
    public int getColumnTwo() {
        return nameOfColumnTwo;
    }
    public void setColumnTwo(int nameOfColumnTwo) {
        this.nameOfColumnTwo = nameOfColumnTwo;
    }
    public MoBackFile getColumnThree() {
        return nameOfColumnThree;
    }
    public void setColumnThree(MoBackFile nameOfColumnThree) {
        this.nameOfColumnThree = nameOfColumnThree;
    }

    /**
     * Basic constructor for your MoBackObject.
     */
    public MoBackObjectRowExampleTwo() {
    }
}