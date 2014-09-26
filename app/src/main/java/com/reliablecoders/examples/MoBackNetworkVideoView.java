package com.reliablecoders.examples;

import android.app.Activity;
import android.os.Bundle;

import com.moback.android.MoBack;
import com.moback.android.MoBackFile;
import com.moback.android.MoBackVideoView;


public class MoBackNetworkVideoView extends Activity {

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
     * Example of creating and using a MoBackImageView.
     */
    public void moBackVideoView() {
        /**
         * MoBackVideoView is a progressively downloaded VideoView. This allows you to load and watch videos on that
         * streaming would normally not allow.
         */

        /**
         * Give our layout a reference so that we can manipulate it.
         */
        MoBackVideoView moBackVideoView = (MoBackVideoView)findViewById(R.id.moBackVideoView);

        /**
         * Set the URL of the video you wish to view.
         */
        moBackVideoView.setVideoUrl(" Your Video URL Here ");

        /**
         * Alternatively you can give it a MoBackFile to load the video from.
         */
        MoBackFile moBackFile = new MoBackFile(" URL Of File ", " Name Of File ", 2);
        moBackVideoView.setVideoUrl(moBackFile);

        /**
         * Playing your video. The normal start method of VideoView has been overridden to allow extra functionality to
         * MoBackVideoView
         */
        moBackVideoView.start();

        /**
         * Other than these specially created methods, MoBackVideoView has all the methods of a normal VideoView and can be
         * manipulated a such.
         */
    }
}
