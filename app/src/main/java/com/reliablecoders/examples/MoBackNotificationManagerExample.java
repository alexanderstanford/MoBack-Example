package com.reliablecoders.examples;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.moback.android.BasicCompletionBlock;
import com.moback.android.MoBack;
import com.moback.android.MoBackStatus;
import com.moback.android.NotificationService;


public class MoBackNotificationManagerExample extends Activity{

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

        //TODO IMPORTANT: YOU MUST SET UP YOUR ANDROID MANIFEST WITH THE PROPER SERVICE AND PERMISSIONS IN ORDER FOR NOTIFICATIONS TO WORK, VIEW THE MANIFEST TO SEE.

    }

    /**
     * Example of enabling Push Notifications. The result of the setting notifications will be returned in the BasicCompletionBlock callback.
     */
    public void enablePushNotifications() {
        /**
         * NotificationService deals with everything regarding Push Notifications. This particular method is used to enable or disable Push Notifications on a
         * particular device. True to enable, False to disable.
         */
        NotificationService.setNotificationsEnabled(true, new BasicCompletionBlock() {
            @Override
            public void callback(MoBackStatus moBackStatus) {
                if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "Successfully Enable/Disabled Push Notifications");
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
    }

    /**
     * Example of setting the Push Notification Callback that is run when a Push Notification is received and subsequently clicked. The result of setting the
     * callback will be returned in the BasicCompletionBlock callback.
     */
    public void setCallback() {
        /**
         * NotificationService deals with everything regarding Push Notifications. This particular method is used to set the Callback who's code is run when
         * a Push Notification is received and subsequently clicked. Create your own class with code you wish to run and use its name as the first parameter
         * required by setNotificationCallback. Refer to MoBackNotificationCallbackExample to see how the code should be formed. Using setNotificationCallback
         * automatically enables notifications to be received by the device. The result of setting the Callback will be returned in the BasicCompletionBlock.
         */
        NotificationService.setNotificationCallback(MoBackNotificationCallbackExample.class, new BasicCompletionBlock() {
            @Override
            public void callback(MoBackStatus moBackStatus) {
                if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "Successfully Set Push Notification Callback");
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
    }

    /**
     * Example of sending a Push Notification to your own devices. The result of the sent Push Notification will be returned in the BasicCompletionBlock callback.
     */
    public void sendPush() {
        /**
         * NotificationService deals with everything regarding Push Notifications. This particular method sends out a Push Notification to all enabled devices that
         * your user has signed in to.
         */
        NotificationService.sendPushAlert(" Your Push Notification Message Here ", new BasicCompletionBlock() {
            @Override
            public void callback(MoBackStatus moBackStatus) {
                if (moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "Successfully Sent Push Notification");
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
    }

    /**
     * Example of miscellaneous NotificationService methods.
     */
    public void miscellaneous() {
        /**
         * NotificationService deals with everything regarding Push Notifications. This particular method returns a boolean value with the status of whether Push
         * Notifications are currently enabled on the device.
         */
        NotificationService.isNotificationEnabled();

        /**
         * NotificationService deals with everything regarding Push Notifications. This particular method sets the vibrate pattern the device will exhibit when
         * a Push Notification is received.
         */
        long[] exampleVibratePattern = {100, 100, 100, 100};
        NotificationService.setNotificationVibration(exampleVibratePattern);

        /**
         * NotificationService deals with everything regarding Push Notifications. This particular method sets the image resource that will be displayed in the
         * Notification Bar when a Push Notification is received.
         */
        NotificationService.setNotificationIcon(R.drawable.ic_launcher);
    }

}
