package com.reliablecoders.examples;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.moback.android.DownloadCompletionBlock;
import com.moback.android.FetchStringCompletionBlock;
import com.moback.android.FileManager;
import com.moback.android.MoBack;
import com.moback.android.MoBackFile;
import com.moback.android.MoBackStatus;
import com.moback.android.Progress;
import com.moback.android.UploadCompletionBlock;

import java.io.File;


public class MoBackFileManagerExample extends Activity {

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
     * Example of downloading a file. Downloading uses two different callbacks. The Progress callback is used for returning how far along the download is.
     * The DownloadCompletionBlock callback returns the status of the download.
     */
    public void downloadFile() {
        /**
         * Here we make a Progress callback to know how far along the download is.
         */
        Progress moBackProgress = new Progress() {
            @Override
            public void callback(int progress) {
                Log.d(getPackageName(), "Downloaded: " + String.valueOf(progress) + "%");
            }
        };
        /**
         * FileManager deals with everything having to do with MoBackFiles. This particular method is used for downloading a file from either a website
         * or a moBackFile. You can a obtain the URL of a MoBackFile by using the .getUrl() method.
         */
        FileManager.downloadFile(" URL Of File Here ", " The Name The File Will Have After Downloading ", this, moBackProgress, new DownloadCompletionBlock() {
            @Override
            public void callback(MoBackStatus moBackStatus) {
                /**
                 * DownloadCompletionBlock differs from BasicCompletionBlock in that it is simply a different callback. This can be useful for if you use
                 * a single callback for all api calls.
                 */
                if(moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "Successfully Downloaded File");
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
    }

    /**
     * Example of uploading a file. Uploading a file uses two different callbacks. The Progress callback is used for returning how far along the upload is.
     * The UploadCompletionBlock callback returns a MoBackFile and the status of the upload.
     */
    public void uploadFile() {
        /**
         * Here we make a simple File.
         */
        File exampleFile = new File(" Location Of File On Device ", " Name Of File For When It's Uploaded ");
        /**
         * Here we make a Progress callback to know how far along the upload is.
         */
        Progress moBackProgress = new Progress() {
            @Override
            public void callback(int progress) {
                Log.d(getPackageName(), "Uploaded: " + String.valueOf(progress) + "%");
            }
        };
        /**
         * Here we create the UploadCompletionBlock that we will be using in out uploadFile method. The MoBackFile that is returned in the callback can be
         * stored in a MoBackObject as the type MoBackFile. These are subsequently stored on the MoBack backend as the type MoBackFile, using the constant
         * MoBackTable.MOBACK_FILE
         */
        UploadCompletionBlock moBackUpload = new UploadCompletionBlock() {
            @Override
            public void callback(MoBackFile moBackFile, MoBackStatus moBackStatus) {
                /**
                 * UploadCompletionBlock differs from BasicCompletionBlock in that it returns a MoBackFile.
                 */
                if(moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "Successfully Uploaded File: " + moBackFile.toString());
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        };
        /**
         * FileManager deals with everything having to do with MoBackFiles. This particular method is used for uploading a file to the MoBack database.
         * The callbacks moBackProgress and moBackUpload are defined above.
         */
        FileManager.uploadFile(exampleFile, moBackProgress, moBackUpload, " Description Of Uploaded File ");
    }

    /**
     * Example of getting appFileStorage. The FetchStringCompletionBlock callback returns a string containing information about how much storage is in use.
     */
    public void appStorageAmount() {
        /**
         * FileManager deals with everything having to do with MoBackFiles. This particular method is used for checking how much of the storage capacity
         * the files stored on the database are taking up.
         */
        FileManager.getAppFileStorage(new FetchStringCompletionBlock() {
            @Override
            public void callback(String message, MoBackStatus moBackStatus) {
                /**
                 * FetchStringCompletionBlock differs from BasicCompletionBlock in that it returns a string with the information about the storage.
                 */
                if(moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "You are currently using: " + message);
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
    }

}
