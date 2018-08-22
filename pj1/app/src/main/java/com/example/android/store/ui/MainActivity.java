package com.example.android.store.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.android.store.R;
import com.example.android.store.contract.DBContract.DBEntry;
import com.example.android.store.helper.DBHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private DBHelper helper;
    @BindView(R.id.textView)
    TextView text;
    @BindView(R.id.fab_add)
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        helper = new DBHelper(this);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        displayInfo();
    }

    private void displayInfo() {
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {
                DBEntry._ID, DBEntry.COLUMN_PRODUCT_NAME,
                DBEntry.COLUMN_PRODUCT_TYPE,
                DBEntry.COLUMN_PRODUCT_PRICE,
                DBEntry.COLUMN_PRODUCT_QUANTITY,
                DBEntry.COLUMN_PRODUCT_SUPPLIER_NAME,
                DBEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NO};
        Cursor c = db.query(DBEntry.TABLE_NAME, projection, null, null, null, null, null);
        try {
            text.setText(String.format(getString(R.string.currently), c.getCount()));
            int idColIndex = c.getColumnIndex(DBEntry._ID);
            int nameColIndex = c.getColumnIndex(DBEntry.COLUMN_PRODUCT_NAME);
            int typeColIndex = c.getColumnIndex(DBEntry.COLUMN_PRODUCT_TYPE);
            int priceColIndex = c.getColumnIndex(DBEntry.COLUMN_PRODUCT_PRICE);
            int quantityColIndex = c.getColumnIndex(DBEntry.COLUMN_PRODUCT_QUANTITY);
            int supplierNameColIndex = c.getColumnIndex(DBEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
            int supplierPhoneColIndex = c.getColumnIndex(DBEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NO);
            while (c.moveToNext()) {
                text.append("\n\nCURRENT ID" + c.getInt(idColIndex)
                        + "\nNAME " + c.getString(nameColIndex)
                        + "\nTYPE " + c.getString(typeColIndex)
                        + "\nPRICE " + c.getInt(priceColIndex)
                        + "\nQUANTITY " + c.getInt(quantityColIndex)
                        + "\nSUPPLIER NAME " + c.getString(supplierNameColIndex)
                        + "\nSUPPLIER PHONE " + c.getInt(supplierPhoneColIndex));
            }


        } finally {
            c.close();
        }

    }
}

