package com.reliablecoders.examples;

import android.util.Log;

import com.moback.android.NotificationService;


/**
 * This is our Callback class that we create to handle what happens when a notification is clicked in the Notification Bar.
 * We extend NotificationService.
 */
public class MoBackNotificationCallbackExample extends NotificationService {

    /**
     * When the you click on the Notification in the Notification bar, this constructor method is called. From here we can
     * execute any code we like.
     */
    public MoBackNotificationCallbackExample() {
        Log.d(getPackageName(), "Received Push Callback");
    }
}
