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
    private static final String customerDate = "Date";

    private static final String pizzaTable = "Pizza";
    private static final String orderNum = "Number";
    private static final String pizzaSize = "Pizza_Size";
    private static final String pizzaType = "Pizza_Type";
    private static final String pizzaPrice = "Pizza_Price";




    DatabaseHelper(Context c) {
        super(c, DB_Name, null, 1);
    }

    public void onCreate(SQLiteDatabase sqLiteDB) {
        sqLiteDB.execSQL("CREATE TABLE " + loginTable + " ("
                + loginUsername + " TEXT, "
                + loginPassword + " TEXT, "
                + loginAdmin + " TEXT);" );

        sqLiteDB.execSQL("CREATE TABLE " + customerTable + " ("
                + customerName + " TEXT, "
                + customerAddress + " TEXT, "
                + customerCreditNum + " TEXT, "
                + customerDate + "TEXT);" );

        //Pizza Tables
        sqLiteDB.execSQL("CREATE TABLE " + pizzaTable + " ("
                + orderNum  + " TEXT PRIMARY KEY,"
                + pizzaSize + " TEXT, "
                + pizzaType + " TEXT, "
                + pizzaPrice + " TEXT);" );
    }

    public void onUpgrade(SQLiteDatabase sqLiteDB, int i, int i1) {
        sqLiteDB.execSQL("DROP TABLE IF EXISTS " + loginTable);
        sqLiteDB.execSQL("DROP TABLE IF EXISTS " + customerTable);
        sqLiteDB.execSQL("DROP TABLE IF EXISTS " + pizzaTable);

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

    //Pizza Tables Methods
    //Insert
    public boolean insertPizza(String num, String size, String type, String price ){

        SQLiteDatabase db = this.getWritableDatabase();

        String table = "test";

        ContentValues contentValues = new ContentValues();

        contentValues.put(orderNum, num);
        contentValues.put(pizzaSize, size);
        contentValues.put(pizzaType, type);
        contentValues.put(pizzaPrice, price);

        long result = db.insert(pizzaTable, null, contentValues);
        return result != -1;


    }

    //read pizza data
    public Cursor readPizza(String order){

        String[] columns = {pizzaType, pizzaPrice, pizzaSize};

        String selection = orderNum +" =?";

        String[] selectionArgs = {order};

        Cursor pizzaCursor = this.getReadableDatabase().query(pizzaTable, columns,  selection,  selectionArgs, null, null, null);

        return pizzaCursor;

    }


}
