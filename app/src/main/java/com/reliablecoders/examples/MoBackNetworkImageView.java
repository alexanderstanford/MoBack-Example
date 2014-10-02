package com.reliablecoders.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.moback.android.MoBack;
import com.moback.android.MoBackImageView;


public class MoBackNetworkImageView extends Activity {

    /**
     * Keeping a reference to your App Keys.
     */
    private static final String APP_KEY = " Your App Key Here ";
    private static final String DEV_KEY = " Your Dev Key Here ";
    private static final String PROD_KEY = " Your Prod Key Here ";

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
     * Example of creating and using a MoBackImageView.
     */
    public void moBackImageView() {
        /**
         * MoBackImageView is a Network Image View that you give a URL to and it loads the image from the URL.
         * To create a MoBackImageView layout, simply make an XML layout with the tag 'com.moback.android.MoBackImageView'
         */

        /**
         * Give our layout a reference so that we can manipulate it.
         */
        MoBackImageView moBackImageView = (MoBackImageView)findViewById(R.id.moBackImageView);

        /**
         * Set the URL of the image we wish to load into our view.
         */
        moBackImageView.setImageUrl(" URL Of Image ");

        /**
         * Set a default image that will show until our image is loaded.
         */
        moBackImageView.setDefaultImageResId(R.drawable.ic_launcher);

        /**
         * Set an image that will show if an error occurs while trying to load the image.
         */
        moBackImageView.setErrorImageResId(R.drawable.ic_launcher);

        /**
         * Other than these specially created methods, MoBackImageView has all the methods of a normal ImageView and can be
         * manipulated a such.
         */
    }
}
