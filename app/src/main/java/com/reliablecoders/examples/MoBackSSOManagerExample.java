package com.reliablecoders.examples;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.moback.android.BasicCompletionBlock;
import com.moback.android.MoBack;
import com.moback.android.MoBackStatus;
import com.moback.android.SSOManager;


public class MoBackSSOManagerExample extends Activity {

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
     * Example of logging in to MoBack. The status of the login will be returned in the BasicCompletionBlock callback.
     */
    public void logIn() {
        /**
         * SSOManager deals with everything having to do with a user. This particular method is used for logging in. After successfully logging in,
         * the SSOToken for the user will automatically be saved into User Preferences. SSOTokens are required for the majority of api calls.
         */
        SSOManager.logIn(" Username Here ", " Password Here ", new BasicCompletionBlock() {
            /**
             * This is MoBack's most simple callback, the BasicCompletionBlock. You can add your own code inside of the callback to execute after the api call is finished.
             */
            @Override
            public void callback(MoBackStatus moBackStatus) {
                if(moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "Successfully Logged In");
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
    }

    /**
     * Example of registering a user with MoBack. The status of the register will be returned in the BasicCompletionBlock callback.
     */
    public void signUp() {
        /**
         * SSOManager deals with everything having to do with a user. This particular method is used for registering a user. After successfully registering,
         * the new user will be automatically logged in and their SSOToken will automatically be saved into User Preferences. SSOTokens are required for the majority of api calls.
         */
        SSOManager.registerUser(" Username Here, but not required", " Email Address Here ", " Password Here ", new BasicCompletionBlock() {
            /**
             * Here inside the callback, we can check if the status of the registerUser attempt was successful.
             * You can also print out the full response of the api call from the status.
             */
            @Override
            public void callback(MoBackStatus moBackStatus) {
                if(moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "Successfully Registered and Logged In");
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
    }

    /**
     * Example of logging out a MoBack user.
     */
    public void logOut() {
        /**
         * SSOManager deals with everything having to do with a user. This particular method is used for logging out a user.
         */
        SSOManager.logoutUser();
    }

    /**
     * Example of resetting the password of a MoBack User.
     */
    public void resetPassword() {
        /**
         * This particular method resets the password of the user specified in the parameters. An email with a link to resetting the password will
         * be emailed to the email associated with the Username.
         */
        SSOManager.resetPassword(" Username of account to reset Password of ", new BasicCompletionBlock() {
            /**
             * Here inside the callback, we can check if the status of the resetPassword attempt was successful.
             * You can also print out the full response of the api call from the status.
             */
            @Override
            public void callback(MoBackStatus moBackStatus) {
                if(moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "Successfully Reset Password, An Email Will Arrive Shortly");
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });
    }

    /**
     * Example of checking various details of the user and/or changing them.
     */
    public void userDetails() {
        /**
         * Checks if there is a logged in user. Returns True if there is one logged in, returns False otherwise.
         */
        SSOManager.isUserLoggedIn();


        /**
         * Returns the Username of the currently logged in User as a String.
         */
        SSOManager.getUsername();


        /**
         * Checks to see if there is a User profile available. User profiles are stored on the database in a System table called user.
         * This checks if the user profile has been loaded locally from the user table.
         * Return True is the user profile is in Shared Preferences, returns False otherwise.
         */
        SSOManager.isProfileAvailable();


        /**
         * Returns the unique UserObjectId.
         */
        SSOManager.getUserObjectId();


        /**
         * Loads the user profile from the database into Shared Preferences. After finishing the api call, the callback returns the status of the load request.
         */
        SSOManager.loadUserProfile(new BasicCompletionBlock() {
            /**
             * Here inside the callback, we can check if the status of the loadUserProfile attempt was successful.
             * You can also print out the full response of the api call from the status.
             */
            @Override
            public void callback(MoBackStatus moBackStatus) {
                if(moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "User Profile successfully pulled from database");
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });


        /**
         * Updates the User Profile on the database with the locally stored User Profile. Any changes such as adding profile fields, will be saved to the database.
         */
        SSOManager.saveUserProfile(new BasicCompletionBlock() {
            /**
             * Here inside the callback, we can check if the status of the saveUserProfile attempt was successful.
             * You can also print out the full response of the api call from the status.
             */
            @Override
            public void callback(MoBackStatus moBackStatus) {
                if(moBackStatus.getCode() == MoBackStatus.STATUS_OKAY) {
                    Log.d(getPackageName(), "User Profile successfully updated");
                } else {
                    Log.d(getPackageName(), moBackStatus.toString());
                }
            }
        });


        /**
         * User Profile fields are a special column within the user system table. User Profile fields are Key/Value pairs of Strings. Setting a Key that doesn't currently exist
         * will create a new Key/Value pair. Profile Fields are useful for storing User Specific data.
         */
        SSOManager.setProfileField(" Name of either new or pre-existing Key ", " Value to be overwritten or created ");


        /**
         * User Profile fields are a special column within the user system table. User Profile fields are Key/Value pairs of Strings. getProfileField returns the Value mapped to
         * the specified Key.
         */
        SSOManager.getProfileField(" Key for value which you wish to get ");
    }

}
