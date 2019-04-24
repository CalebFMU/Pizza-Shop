package com.example.myapp4rao;

import android.database.Cursor;
import android.os.FileObserver;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class OrderViewer extends AppCompatActivity {
    private LinearLayout mLayout;
    private EditText mEditText;
    private Button mButton;
    String size = " ";
    String type = " ";
    String price = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_viewer);

        Cursor pizzaCursor = MainActivity.sqLiteDBHelper.readPizza("100");

        Log.i("TAG", "size: " + this.size);
        Log.i("TAG", "type: " + this.type);
        if (pizzaCursor != null)
        {
            pizzaCursor.moveToFirst();
        }

        // iterate
        while (!pizzaCursor.isAfterLast())
        {

            pizzaCursor.moveToNext();
        }

        TextView sizeText = findViewById(R.id.orderViewerSize2);
        sizeText.setText(this.size);
        final Button btnConfirmOrder = (Button) findViewById(R.id.confirmOrderButton);
        Button addItem = (Button) findViewById(R.id.addbutton);
        Button deleteItem = (Button) findViewById(R.id.addToOrder2);

        addItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Obtain new line to increment down

            }
        });
        deleteItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Delete previous line
            }
        });

        btnConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(OrderViewer.this);
                final View mView = getLayoutInflater().inflate(R.layout.dialog_confirm, null);
                final EditText mCustomerName = (EditText) mView.findViewById(R.id.etCustomerName);
                final EditText mCustomerAddress = (EditText) mView.findViewById(R.id.etCustomerAddress);
                final EditText mCustomerCardNum = (EditText) mView.findViewById(R.id.etCustomerCardNum);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                Button mConfirm = (Button) mView.findViewById(R.id.confirmOrderButton);
                Button mClose = (Button) mView.findViewById(R.id.btnCloseConfirmOrder);


                mConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mCustomerName.getText().toString().isEmpty() && !mCustomerAddress.getText().toString().isEmpty() && !mCustomerCardNum.getText().toString().isEmpty()) {
                            Toast.makeText(OrderViewer.this,
                                    "Order Placement Successful", Toast.LENGTH_SHORT).show();

                                    dialog.dismiss();


                        } else {
                            Toast.makeText(OrderViewer.this,
                                    getString(R.string.Failed_confirm_msg), Toast.LENGTH_SHORT).show();
                        }


                    }
                });

                mClose.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        dialog.dismiss();
                    }
                });



            }
        });

    }

}

