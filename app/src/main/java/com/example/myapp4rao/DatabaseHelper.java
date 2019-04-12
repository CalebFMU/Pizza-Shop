package com.example.myapp4rao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_Name = "pizza.db";

    private static final String loginTable = "Login";
    private static final String loginUsername = "Username";
    private static final String loginPassword = "Password";
    private static final String loginAdmin = "Admin";

    private static final String customerTable = "Customer";
    private static final String customerName = "Name";
    private static final String customerAddress = "Address";
    private static final String customerCreditNum = "Credit_Card_Num";
    private static final String customerPhoneNum = "Phone_Num";



    DatabaseHelper(Context c) {
        super(c, DB_Name, null, 1);
    }

    public void onCreate(SQLiteDatabase sqLiteDB) {
        sqLiteDB.execSQL("CREATE TABLE " + loginTable + " ("
                + loginUsername + " TEXT, "
                + loginPassword + " TEXT, "
                + loginAdmin + " TEXT);" );
    }

    public void onUpgrade(SQLiteDatabase sqLiteDB, int i, int i1) {
        sqLiteDB.execSQL("DROP TABLE IF EXISTS " + loginTable);
        onCreate(sqLiteDB);
    }

    public Cursor getUsername(String username, String password) {
        String[] columns = {loginAdmin};
        String where = loginUsername + " = ? and " + loginPassword + " = ?";
        String[] args = new String[]{username, password};
        Cursor userName = this.getReadableDatabase().query(loginTable, columns, where, args, null,
                null, null);
        return userName;
    }

    public String getLoginAdmin() { return loginAdmin; }

    public void addAttributes(String addUsername, String addPassword, String addAdmin) {
        SQLiteDatabase sqLiteDB = this.getWritableDatabase();

        ContentValues c = new ContentValues();
        c.put(loginUsername, addUsername);
        c.put(loginPassword, addPassword);
        c.put(loginAdmin, addAdmin);
        sqLiteDB.insert(loginTable, null, c);
    }

}
