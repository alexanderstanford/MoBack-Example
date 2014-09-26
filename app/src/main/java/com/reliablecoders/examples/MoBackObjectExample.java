package com.reliablecoders.examples;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.moback.android.BasicCompletionBlock;
import com.moback.android.FetchCountCompletionBlock;
import com.moback.android.FetchMultipleCompletionBlock;
import com.moback.android.FetchSingleCompletionBlock;
import com.moback.android.MoBack;
import com.moback.android.MoBackFile;
import com.moback.android.MoBackGeoPoint;
import com.moback.android.MoBackObject;
import com.moback.android.MoBackQuery;
import com.moback.android.MoBackStatus;


public class MoBackObjectExample extends Activity {

    /**
     * Keeping a reference to your App Keys.
     */
    public static final String APP_KEY = " Your App Key Here ";
    public static final String DEV_KEY = " Your Dev Key Here ";
    public static final String PROD_KEY = " Your Prod Key Here ";

    /**
     * These are for usage in upcoming methods for simplicity's sake. It is not required to declare and initialize them here.
     */
    String valueForColumnOne = "";
    int valueForColumnTwo = 0;
    MoBackFile valueForColumnThree = new MoBackFile(" URL ", " Name That Displays In Row ", 2);
    MoBackObjectRowExampleOne rowForTable = new MoBackObjectRowExampleOne(valueForColumnOne, valueForColumnTwo, valueForColumnThree);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_activity);

        /**
         * Set your App Keys before trying to do any kind of api call with MoBack.
         */
        MoBack.setApplicationKeys(this, APP_KEY, DEV_KEY);

        //TODO IMPORTANT: THE NAME OF THE JAVA CLASS FILE THAT YOU EXTEND WITH MoBackObject AND USE FOR YOUR ROWS IS THE NAME OF THE TABLE THAT THOSE ROWS GET CREATED/UPDATED/DELETED.
    }

    /**
     * Examples of how to set-up a MoBackObject.
     */
    public void settingUpMoBackObjects() {
        /**
         * MoBackObject deals with everything regarding the MoBackObjects that eventually get saved to MoBackTables as rows in the Tables. These can be
         * be stored locally on the device's SD card or on the MoBack backend's database.
         */

        /**
         * Here is one way to make a MoBackObject. We can predefine what values this particular row will have before saving it to a Table. Refer to MoBackObjectRowExampleOne
         * for this particular object.
         */
        int valueForColumnTwo = 1;
        MoBackFile valueForColumnThree = new MoBackFile(" URL ", " Name That Displays In Row ", 2);
        MoBackObjectRowExampleOne exampleObject1 = new MoBackObjectRowExampleOne("valueForColumnOne", valueForColumnTwo, valueForColumnThree);

        /**
         * Or you can simply declare the object and then add to it afterword. Refer to MoBackObjectRowExampleTwo for this particular object.
         */
        MoBackObjectRowExampleTwo exampleObject2 = new MoBackObjectRowExampleTwo();
        exampleObject2.setColumnOne("settingValueOfColumnOne");
        int settingValueOfColumnTwo = 5;
        exampleObject2.setColumnTwo(settingValueOfColumnTwo);
        MoBackFile settingValueOfColumnThree = new MoBackFile(" URL ", " Name That Displays In Row ", 2);
        exampleObject2.setColumnThree(settingValueOfColumnThree);
    }

    /**
     * Example of creating a new row to a Table using an extended MoBackObject. The result of the creation request will be returned in the BasicCompletionBlock callback.
     */
    public void addNewRow() {
        /**
         * MoBackObject deals with everything regarding the MoBackObjects that eventually get saved to MoBackTables as rows in the Tables. These can be
         * be stored locally on the device's SD card or on the MoBack backend's database. This particular method creates a new row on an existing table
         * in the backend's database.
         */
        rowForTable.create(new BasicCompletionBlock() {
            @Override
            public void callback(MoBackStatus moBackStatus) {
                if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "Successfully Added Row To Table");
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
    }

    /**
     * Example of updating an existing row with new information to a Table using an extended MoBackObject. The result of the update request will be returned
     * in the BasicCompletionBlock callback.
     */
    public void updateRow() {
        /**
         * MoBackObject.fetchX is a required step in order to update a row and will be explained in their own methods.
         */
        MoBackObject.fetchAll(MoBackObjectRowExampleOne.class, null, new FetchMultipleCompletionBlock() {
            @Override
            public void callback(MoBackObject[] moBackObjects, MoBackStatus moBackStatus) {
                /**
                 * MoBackObject deals with everything regarding the MoBackObjects that eventually get saved to MoBackTables as rows in the Tables. These can be
                 * be stored locally on the device's SD card or on the MoBack backend's database. This particular method updates an existing row with new information.
                 * All rows have their own unique ID that is automatically generated and maintained by the backend. In order to update a row, we must first get them with fetching.
                 */
                for (MoBackObject individualRows : moBackObjects) {
                    /** Here we are iterating through all the rows of the table and setting their values to our own values, then updating them. */
                    individualRows = rowForTable;
                    individualRows.update(new BasicCompletionBlock() {
                        @Override
                        public void callback(MoBackStatus moBackStatus) {
                            if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                                Log.d(getPackageName(), "Successfully Update Row On Table");
                            } else {
                                Log.d(getPackageName(), moBackStatus.toString());
                            }
                        }
                    });
                }
            }
        });
    }

    /**
     * Example of deleting an existing row with new information from a Table. The result of the delete will be returned in the BasicCompletionBlock callback.
     */
    public void deleteRow() {
        /**
         * MoBackObject.fetchX is a required step in order to delete a row and will be explained in their own methods.
         */
        MoBackObject.fetchAll(MoBackObjectRowExampleOne.class, null, new FetchMultipleCompletionBlock() {
            @Override
            public void callback(MoBackObject[] moBackObjects, MoBackStatus moBackStatus) {
                /**
                 * MoBackObject deals with everything regarding the MoBackObjects that eventually get saved to MoBackTables as rows in the Tables. These can be
                 * be stored locally on the device's SD card or on the MoBack backend's database. This particular method deletes an existing row. All rows have
                 * their own unique ID that is automatically generated and maintained by the backend. In order to delete a row, we must first get them with fetching.
                 */
                for (MoBackObject individualRows : moBackObjects) {
                    /** Here we are iterating through all the rows of the table and deleting them from the database. */
                    individualRows.delete(new BasicCompletionBlock() {
                        @Override
                        public void callback(MoBackStatus moBackStatus) {
                            if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                                Log.d(getPackageName(), "Successfully Delete Row From Table");
                            } else {
                                Log.d(getPackageName(), moBackStatus.toString());
                            }
                        }
                    });
                }
            }
        });
    }

    /**
     * Example of creating rows locally to phone storage instead of the backend. The result of the creation will be returned in the BasicCompletionBlock callback.
     */
    public void saveLocally() {
        /**
         * MoBackObject deals with everything regarding the MoBackObjects that eventually get saved to MoBackTables as rows in the Tables. These can be
         * be stored locally on the device's SD card or on the MoBack backend's database. This particular method creates and saves rows locally to the phone's
         * storage. All rows have their own unique ID that is automatically generated and maintained by the backend, but locally saved rows have a different kind
         * of ID and have certain restrictions.
         */
        rowForTable.saveLocal(new BasicCompletionBlock() {
            @Override
            public void callback(MoBackStatus moBackStatus) {
                if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "Successfully Saved Row Locally");
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
    }

    /**
     * Example of deleting rows locally from phone storage. The result of the deletion will be returned in the BasicCompletionBlock callback.
     */
    public void deleteRowsLocally() {
        /**
         * MoBackObject.fetchX is a required step in order to update a row and will be explained in their own methods.
         */
        MoBackObject.fetchAllFromLocal(MoBackObjectRowExampleOne.class, null, new FetchMultipleCompletionBlock() {
            @Override
            public void callback(MoBackObject[] moBackObjects, MoBackStatus moBackStatus) {
                /**
                 * MoBackObject deals with everything regarding the MoBackObjects that eventually get saved to MoBackTables as rows in the Tables. These can be
                 * be stored locally on the device's SD card or on the MoBack backend's database. This particular method deletes individual locally saved rows.
                 */
                for (MoBackObject individualRows : moBackObjects) {
                    /** Here we are iterating through all the rows that are saved locally and deleting them. */
                    individualRows.deleteLocal(new BasicCompletionBlock() {
                        @Override
                        public void callback(MoBackStatus moBackStatus) {
                            if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                                Log.d(getPackageName(), "Successfully Deleted Row From Local Storage");
                            } else {
                                Log.d(getPackageName(), moBackStatus.toString());
                            }
                        }
                    });
                    /** We can also delete locally saved rows by getting their ID and using that. */
                    MoBackObject.deleteLocal(MoBackObjectRowExampleOne.class, individualRows.getId(), new BasicCompletionBlock() {
                        @Override
                        public void callback(MoBackStatus moBackStatus) {
                            if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                                Log.d(getPackageName(), "Successfully Deleted Row From Local Storage");
                            } else {
                                Log.d(getPackageName(), moBackStatus.toString());
                            }
                        }
                    });
                }
            }
        });
    }

    /**
     * Example of saving rows to the database when there is an internet connection and saving them locally to be saved to the database when an internet connection is established.
     * The result of the save will be returned in the BasicCompletionBlock callback.
     */
    public void saveEventually() {
        /**
         * MoBackObject deals with everything regarding the MoBackObjects that eventually get saved to MoBackTables as rows in the Tables. These can be
         * be stored locally on the device's SD card or on the MoBack backend's database. This particular method is used to save objects to the database
         * when there is an internet connection or store them locally until there is an internet connection to save them to the database.
         */
        rowForTable.saveEventually(new BasicCompletionBlock() {
            @Override
            public void callback(MoBackStatus moBackStatus) {
                if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "Success");
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
    }

    /**
     * Example of checking how many rows are stored in a particular Table. The results of the check and number of rows will be returned in the FetchCountCompletionBlock callback.
     */
    public void numberOfRows() {
        /**
         * MoBackObject deals with everything regarding the MoBackObjects that eventually get saved to MoBackTables as rows in the Tables. These can be
         * be stored locally on the device's SD card or on the MoBack backend's database. This particular method is used to check how many rows are stored
         * in a particular Table on the database.
         */
        MoBackObject.fetchCount(MoBackObjectRowExampleOne.class, new FetchCountCompletionBlock() {
            @Override
            public void callback(int count, MoBackStatus moBackStatus) {
                /**
                 * TableCompletionBlock differs from the BasicCompletionBlock in that it returns an ArrayList of all the names of existing tables on the backend.
                 */
                if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "Success, the amount of rows in this Table is: " + String.valueOf(count));
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
    }

    /**
     * Example of fetching rows from the either the MoBack database or local storage. After fetching, they can be updated, deleted, or used however seen fit.
     * The results of fetching and the rows will be returned in the FetchMultipleCompletionBlock callback.
     */
    public void getSpecificRows() {
        /**
         * MoBackObject deals with everything regarding the MoBackObjects that eventually get saved to MoBackTables as rows in the Tables. These can be
         * be stored locally on the device's SD card or on the MoBack backend's database. This particular method fetches rows from the Table that is named the same
         * way as the extended MoBackObject class file. FetchAlls use Queries which based on what information is put it, will return only specific rows. Here we
         * use a null Query, which effectively Fetches every row. Queries will be explained in another method.
         */
        MoBackObject.fetchAll(MoBackObjectRowExampleOne.class, null, new FetchMultipleCompletionBlock() {
            @Override
            public void callback(MoBackObject[] moBackObjects, MoBackStatus moBackStatus) {
                /**
                 * FetchMultipleCompletionBlock differs from BasicCompletionBlock in that it returns a list of MoBackObject rows.
                 */
                for (MoBackObject element : moBackObjects) {
                    if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                        Log.d(getPackageName(), "Fetch Successful, here is the unique ID of this row: " + element.getId());
                    } else {
                        Log.d(getPackageName(), moBackStatus.toString());
                    }
                }
            }
        });
        MoBackObject.fetchAllFromLocal(MoBackObjectRowExampleOne.class, null, new FetchMultipleCompletionBlock() {
            @Override
            public void callback(MoBackObject[] moBackObjects, MoBackStatus moBackStatus) {
                /**
                 * FetchMultipleCompletionBlock differs from BasicCompletionBlock in that it returns a list of MoBackObject rows.
                 */
                for (MoBackObject element : moBackObjects) {
                    if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                        Log.d(getPackageName(), "Fetch Successful, here is the unique ID of this row: " + element.getId());
                    } else {
                        Log.d(getPackageName(), moBackStatus.toString());
                    }
                }
            }
        });
    }

    /**
     * Example of fetching individual row from either the MoBack database or local storage. After fetching, they can be updated, delete, or used however seen fit.
     * The results of fetching the the row will be returned in the FetchSingleCompletionBlock callback.
     */
    public void getIndividualRow() {
        /**
         * MoBackObject deals with everything regarding the MoBackObjects that eventually get saved to MoBackTables as rows in the Tables. These can be
         * be stored locally on the device's SD card or on the MoBack backend's database. This particular method fetches a row from the Table that is named the same
         * way as the extended MoBackObject class file. Individual fetches require the unique ID of the row you wish to fetch. These ID's can be retrieved when using
         * FetchAlls or when creating a row.
         */
        final String moBackObjectId = " You Get This When Using FetchAll Or After Creating A New Row ";

        MoBackObject.fetchObject(MoBackObjectRowExampleOne.class, moBackObjectId, new FetchSingleCompletionBlock() {
            @Override
            public void callback(MoBackObject moBackObject, MoBackStatus moBackStatus) {
                /**
                 * FetchSingleCompletionBlock differs from BasicCompletionBlock in that it returns a single moBackObject row.
                 */
                if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "Fetch Successful, here is the fetched row: " + moBackObject.toString());
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
        MoBackObject.fetchFromLocal(MoBackObjectRowExampleOne.class, moBackObjectId, new FetchSingleCompletionBlock() {
            @Override
            public void callback(MoBackObject moBackObject, MoBackStatus moBackStatus) {
                /**
                 * FetchSingleCompletionBlock differs from BasicCompletionBlock in that it returns a single moBackObject row.
                 */
                if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "Fetch Successful, here is the fetched row: " + moBackObject.toString());
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
    }

    /**
     * Examples of Queries and how to make them.
     */
    public void queries() {
        /**
         * Here are a few examples of how to makes Queries for use in Fetches. There are many kinds of Queries and ways to use them for getting particular rows.
         */
        MoBackQuery.all(); /** This is the same as passing null, it will return all row in the fetch response */
        MoBackQuery.whereValueForFieldIsEqualTo(" Name Of Column ", rowForTable); /** The 'Name Of Column' must match a column name in 'rowForTable'. When passing
                                                                                      this Query in a FetchAll, it will return all rows that have values equal to
                                                                                      the value that is present within 'rowForTable' that has the same name as 'Name Of Column'. */
        MoBackQuery.all().setLimit(50); /** This Query, when passed in to a FetchAll, will return the first 50 rows of a Table. */
    }

    /**
     * Example of a few miscellaneous methods.
     */
    public void miscellaneous() {
        /** Gets the unique ID of this row. */
        rowForTable.getId();

        /** Checks whether this row is from the backend database or local storage. True means it is from the backend and False means it is from local storage. */
        rowForTable.isFromCloud();

        /** Creating MoBackGeoPoints to store in tables */
        double locationX = 0.0;
        double locationY = 0.0;
        double geoLocation[] = new double[2];
        geoLocation[0] = locationX;
        geoLocation[1] = locationY;
        MoBackGeoPoint moBackGeoPoint = new MoBackGeoPoint(geoLocation);

        /**
        * MoBackFile files are the default filetype for the MoBack backend. Currently, file types can either be 1 or 2, wih 2 being standard.
        */
        MoBackFile moBackFile = new MoBackFile(" URL Of Your File ", " Name That Will Show In Any Rows ", 2);
    }

}
