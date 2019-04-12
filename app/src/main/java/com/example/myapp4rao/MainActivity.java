package com.example.myapp4rao;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static DatabaseHelper sqLiteDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteDBHelper = new DatabaseHelper(this);
        sqLiteDBHelper.addAttributes("Caleb","Password","Y");
        sqLiteDBHelper.addAttributes("Harley", "Password","Y");
        sqLiteDBHelper.addAttributes("John","Doe","N");

        final Button buttonCancel = findViewById(R.id.loginButtonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Whatever code or methods you want to run
                final TextView usernameTextFill = findViewById(R.id.usernameTextFill);
                final TextView passwordTextFill = findViewById(R.id.passwordTextFill);
                usernameTextFill.setText("");
                passwordTextFill.setText("");
            }
        });
        final Button buttonOk = findViewById(R.id.loginButtonOk);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Whatever code or methods you want to run
                final TextView usernameTextFill = findViewById(R.id.usernameTextFill);
                final TextView passwordTextFill = findViewById(R.id.passwordTextFill);
                String username = usernameTextFill.getText().toString();
                String password = passwordTextFill.getText().toString();

                Cursor cursor = sqLiteDBHelper.getUsername(username, password);
                cursor.moveToFirst();
                int column = cursor.getColumnIndex(sqLiteDBHelper.getLoginAdmin());
                String adminStatus = cursor.getString(column);
                Intent intent;
                if( cursor.getCount() > 0) {
                    if( adminStatus.equalsIgnoreCase("Y")) {
                        intent = new Intent(v.getContext(), NavigationPage.class);
                    }
                    else {
                        intent = new Intent(v.getContext(), OrderPage.class);
                    }
                    startActivity(intent);
                    finish();
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setMessage("ERROR: Username or Password incorrect").setTitle("Error");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // User clicked OK button
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }
        });
    }
}
