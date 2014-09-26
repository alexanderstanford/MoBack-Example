package com.reliablecoders.examples;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.moback.android.BasicCompletionBlock;
import com.moback.android.BooleanCompletionBlock;
import com.moback.android.MoBack;
import com.moback.android.MoBackStatus;
import com.moback.android.MoBackTable;
import com.moback.android.TableCompletionBlock;

import java.util.ArrayList;
import java.util.HashMap;


public class MoBackTableExample extends Activity {

    /**
     * Keeping a reference to your App Keys.
     */
    public static final String APP_KEY = " Your App Key Here ";
    public static final String DEV_KEY = " Your Dev Key Here ";
    public static final String PROD_KEY = " Your Prod Key Here ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_activity);

        /**
         * Set your App Keys before trying to do any kind of api call with MoBack.
         */
        MoBack.setApplicationKeys(this, APP_KEY, DEV_KEY);
    }

    /**
     * Example of creating Table on the MoBack database. The status of the creation request will be returned in the BasicCompletionBlock callback.
     */
    public void createTable() {
        /**
         * The columns of the table you wish to create are stored in a Hashmap as Key (name of the column)/ Value (type of data that the column can store) pairs.
         * The names of the columns must match with the names of your variables in your MoBackObjects that you will use to update the Table with new rows. The
         * values that the columns can store are part of the MoBackTable Constants. MoBackTable.BOOLEAN, MoBackTable.MOBACK_FILE, and MoBackTable.STRING are some
         * examples of constants to use.
         */
        HashMap<String, String> columnsHashmap = new HashMap<String, String>();
        columnsHashmap.put(" Name Of Your Column ", " Type Of Data Your Column Will Store ");
        columnsHashmap.put("exampleName", MoBackTable.STRING);
        /**
         * MoBackTable deals with nearly everything having to do with tables. This particular method is used for creating a new Table in the database.
         * The table will be created with the specified name and columns based on the provided Hashmap. The result of the creation request will be returned
         * in the BasicCompletionBlock callback.
         */
        MoBackTable.createTable(" Name of table you wish to create here ", columnsHashmap, new BasicCompletionBlock() {
            @Override
            public void callback(MoBackStatus moBackStatus) {
                if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "Successfully Created Table");
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
    }

    /**
     * Example of deleting a Table off of the MoBack database. The status of the deletion request will be returned in the BasicCompletionBlock callback.
     */
    public void deleteTable() {
        /**
         * MoBackTable deals with nearly everything having to do with tables. This particular method is used to delete a Table off of the database with the given name.
         */
        MoBackTable.deleteTable(" Name Of Table To Delete ", new BasicCompletionBlock() {
            @Override
            public void callback(MoBackStatus moBackStatus) {
                if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "Successfully Deleted Table");
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
    }

    /**
     * Example of getting a list of all existing Tables on the database. The status of the get request and list of Tables will be returned in the TableCompletionBlock callback.
     */
    public void getTableList() {
        /**
         * MoBackTable deals with nearly everything having to do with tables. This particular method is used to retrieve a list of all existing Tables in the database.
         */
        MoBackTable.tableList(new TableCompletionBlock() {
            @Override
            public void callback(ArrayList<String> tableNames, MoBackStatus moBackStatus) {
                /**
                 * TableCompletionBlock differs from the BasicCompletionBlock in that it returns an ArrayList of all the names of existing tables on the backend.
                 */
                if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    for (String element : tableNames) {
                        Log.d(getPackageName(), "Existing Table: " + element);
                    }
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
    }

    /**
     * Example of checking whether a Table exists on the MoBack database. The status of the checking request and confirmation of whether the specified Table exist will
     * be returned in the BooleanCompletionBlock.
     */
    public void tableExits() {
        /**
         * MoBackTable deals with nearly everything having to do with tables. This particular method is used to check whether or not a specified Table exists.
         */
        MoBackTable.tableExists(" Name Of Table To Check For ", new BooleanCompletionBlock() {
            @Override
            public void callback(boolean result, MoBackStatus moBackStatus) {
                /**
                 * BooleanCompletionBlock differs from the BasicCompletionBlock in that it returns a boolean confirming whether or not the specified Table exists.
                 */
                if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    if (result) {
                        Log.d(getPackageName(), "Table Exists");
                    } else {
                        Log.d(getPackageName(), "Table Does Not Exist");
                    }
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });

    }

    /**
     * Example of adding a column to an existing Table on the database. The status of the addition request will be returned in the BasicCompletionBlock.
     */
    public void addColumn() {
        /**
         * MoBackTable deals with nearly everything having to do with tables. This particular method is used for adding a new column to an existing Table.
         */
        MoBackTable.addColumn(" Name Of Table To Add Column To ", " Column Name To Add ", " Column Data Storage Type ", new BasicCompletionBlock() {
            @Override
            public void callback(MoBackStatus moBackStatus) {
                if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "Successfully Added Column To Table");
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
    }

    /**
     * Example of deleting a column from an existing Table on the database. The status of the delete request will be returned in the BasicCompletionBlock.
     */
    public void deleteColumn() {
        /**
         * MoBackTable deals with nearly everything having to do with tables. This particular method is used for deleting a column from an existing Table.
         */
        MoBackTable.deleteColumn(" Name Of Table To Delete Column From ", " Name Of The Column", new BasicCompletionBlock() {
            @Override
            public void callback(MoBackStatus moBackStatus) {
                if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "Successfully Deleted Column From Table");
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
    }

}
