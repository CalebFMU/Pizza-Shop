package com.example.myapp4rao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.database.Cursor;


public class OrderPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        final Button logoutButton = findViewById(R.id.buttonLogOut);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        final Button smallButton = findViewById(R.id.buttonSmall);
        smallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView orderViewer = findViewById(R.id.orderViewerSize);
                orderViewer.setText("Small");
                TextView price = findViewById(R.id.orderPagePrice);

                price.setText("6.00");

                orderViewer.setVisibility(View.VISIBLE);
            }
        });

        final Button medButton = findViewById(R.id.buttonMed);
        medButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView orderViewer = findViewById(R.id.orderViewerSize);
                TextView price = findViewById(R.id.orderPagePrice);

                price.setText("10.00");

                orderViewer.setText("Medium");
                orderViewer.setVisibility(View.VISIBLE);
            }
        });

        final Button largeButton = findViewById(R.id.buttonLarge);
        largeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView orderViewer = findViewById(R.id.orderViewerSize);
                TextView price = findViewById(R.id.orderPagePrice);

                price.setText("12.00");

                orderViewer.setText("Large");
                orderViewer.setVisibility(View.VISIBLE);
            }
        });

        final Button cheeseButton = findViewById(R.id.buttonCheese);
        cheeseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView orderViewer = findViewById(R.id.orderViewerPizzaType);
                orderViewer.setText("Cheese");
                orderViewer.setVisibility(View.VISIBLE);
            }
        });

        final Button pepButton = findViewById(R.id.buttonPep);
        pepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView orderViewer = findViewById(R.id.orderViewerPizzaType);
                orderViewer.setText("Pepperoni");
                orderViewer.setVisibility(View.VISIBLE);
            }
        });

        final Button supButton = findViewById(R.id.buttonSup);
        supButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView orderViewer = findViewById(R.id.orderViewerPizzaType);

                orderViewer.setText("Supreme");
                orderViewer.setVisibility(View.VISIBLE);
            }
        });



  final Button placeOrderButton = findViewById(R.id.buttonPlaceOrder);

  placeOrderButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v)
      {

          int dbSize = 0;
          String orderNum = "";

          Cursor pizzaCursor = MainActivity.sqLiteDBHelper.readPizza();

          dbSize = pizzaCursor.getCount();
          orderNum = "00" + dbSize;

          startActivity(new Intent(OrderPage.this,OrderViewer.class));

          TextView pizzatype = findViewById(R.id.orderViewerPizzaType);
          String type = pizzatype.getText().toString();
          Log.i("TAG", "type: " + type);

          TextView pizzasize = findViewById(R.id.orderViewerSize);
          String size = pizzasize.getText().toString();
          Log.i("TAG", "size: " + size);

          TextView pizzaprice = findViewById(R.id.orderPagePrice);
          String price = pizzaprice.getText().toString();
          Log.i("TAG", "price: " + price);

          MainActivity.sqLiteDBHelper.insertPizza(orderNum,type,size,price);


      }
  });

            }

    }

