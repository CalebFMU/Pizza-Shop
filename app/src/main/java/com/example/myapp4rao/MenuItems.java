package com.example.myapp4rao;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MenuItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_viewer);

        /*****************
         *
         * Get to where Menu items is incremented in a for loop for every time user add more items
         * + and - buttons remove or add items
         * Get confirm button to prompt a total price and then exit program
         *
         *

    // Want to use alert prompt after Confirm button press to ask user if done (Submit or Cancel)
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);

        AlertDialog dialog = builder.create();
         */
    }
}
