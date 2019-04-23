package com.example.myapp4rao;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Toast;

public class OrderViewer extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_viewer);



        final Button btnConfirmOrder = (Button) findViewById(R.id.confirmOrderButton);
        btnConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(OrderViewer.this);
                final AlertDialog alert = mBuilder.create();
                final View mView = getLayoutInflater().inflate(R.layout.dialog_confirm, null);
                final EditText mCustomerName = (EditText) mView.findViewById(R.id.etCustomerName);
                final EditText mCustomerAddress = (EditText) mView.findViewById(R.id.etCustomerAddress);
                final EditText mCustomerCardNum = (EditText) mView.findViewById(R.id.etCustomerCardNum);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                Button mConfirm = (Button) mView.findViewById(R.id.btnConfirmOrder);
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

