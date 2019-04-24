package com.example.myapp4rao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.database.Cursor;


import android.widget.RelativeLayout;

public class SalesReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_report);

        Cursor pizzaCursor = MainActivity.sqLiteDBHelper.readPizza();
        String reports = "";

        if (pizzaCursor != null)
        {
            pizzaCursor.moveToFirst();

        }
        // iterate
        while (!pizzaCursor.isAfterLast())
        {
            TextView reportsText = findViewById(R.id.rowOne);
            reports = reports + "  |  " + pizzaCursor.getString(0);
            reports = reports + "  |  " + pizzaCursor.getString(1);
            reports = reports + "  |  " + pizzaCursor.getString(2);
            reports = reports + "  |  " + pizzaCursor.getString(3);
            reportsText.setText(reports);
            pizzaCursor.moveToNext();
        }


        final Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NavigationPage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
