package com.pullrueepe.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.pullrueepe.model.symbol.SymbolData;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String SYMBOL_TABLE = "currency_table";
    private static final String DB_NAME = "pullrueepe_db";

    // CURRENCY_TABLE
    String CREATE_SYMBOL_TABLE = "CREATE TABLE IF NOT EXISTS " + SYMBOL_TABLE + " (" +
            SymbolData.Column.SYMBOL_ID + " TEXT," + SymbolData.Column.SYMBOL_CODE + " TEXT," +
            SymbolData.Column.SYMBOL_COMPANY_NAME + " TEXT)";
    private Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SYMBOL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SYMBOL_TABLE);
    }

    public void insertSymbol(List<SymbolData> symbolData) {
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < symbolData.size(); i++) {
            SymbolData symbolData1 = symbolData.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put( SymbolData.Column.SYMBOL_ID, symbolData1.getId());
            contentValues.put(SymbolData.Column.SYMBOL_CODE, symbolData1.getSymbolcode());
            contentValues.put(SymbolData.Column.SYMBOL_COMPANY_NAME, symbolData1.getSymbolcompanyname());
            db.insert(SYMBOL_TABLE, null, contentValues);
        }
        db.close();
    }

    public double getSymbolCodeById(String symbolId) {
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + SYMBOL_TABLE + " WHERE " +
                SymbolData.Column.SYMBOL_ID + "='" + symbolId + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        double value = 1.0;
        if (cursor.moveToFirst()) {
            value = Double.parseDouble(cursor.getString(cursor.getColumnIndex(SymbolData.Column.SYMBOL_CODE)));
        }
        return value;
    }

    /**
     * @param companyName method to get currency symbol by currency code.
     *                     Will return default currency code.
     */
    public String getSymbolByCompanyName(String companyName) {
        try {
            SQLiteDatabase db = getReadableDatabase();
            String selectQuery = "SELECT " + SymbolData.Column.SYMBOL_COMPANY_NAME + " FROM " + SYMBOL_TABLE
                    + " WHERE " + SymbolData.Column.SYMBOL_COMPANY_NAME + "='" + companyName + "'";
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (db.isOpen() && cursor.moveToFirst()) {
                return cursor.getString(cursor.getColumnIndex(SymbolData.Column.SYMBOL_CODE));
            }
        } catch (Exception e) {
            return null;
            /*return CommonConstant.DEFAULT_CURRENCY_SYMBOL;*/
        }
        return null;
        /*return CommonConstant.DEFAULT_CURRENCY_SYMBOL;*/
    }

    /**
     * Clears table.
     **/
    public void clearTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(SYMBOL_TABLE, null, null);
        db.close();
    }

    /**
     * Returns list of currencies with Currency code & symbol.
     */
    public ArrayList<SymbolData> getSymbolList() {
        String selectQuery = "SELECT " + SymbolData.Column.SYMBOL_CODE + ","
                +  SymbolData.Column.SYMBOL_ID +"," +   SymbolData.Column.SYMBOL_COMPANY_NAME+" FROM " + SYMBOL_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        ArrayList<SymbolData> symbolDataArrayList = new ArrayList<>();
        for (int i = 0; i < cursor.getCount(); i++) {
            SymbolData symbolData = new SymbolData();
            symbolData.setId(cursor.getInt(cursor.getColumnIndex(SymbolData.Column.SYMBOL_ID)));
            symbolData.setSymbolcode(cursor.getString(cursor.getColumnIndex(SymbolData.Column.SYMBOL_CODE)));
            symbolDataArrayList.add(symbolData);
            cursor.moveToNext();
        }
        return symbolDataArrayList;
    }
}
