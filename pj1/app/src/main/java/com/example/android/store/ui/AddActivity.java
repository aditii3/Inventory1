package com.example.android.store.ui;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.android.store.R;
import com.example.android.store.contract.DBContract;
import com.example.android.store.helper.DBHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.LENGTH_SHORT;

public class AddActivity extends AppCompatActivity {
    @BindView(R.id.et_name)
    EditText name;
    @BindView(R.id.et_price)
    EditText price;
    @BindView(R.id.et_quantity)
    EditText quantity;
    @BindView(R.id.et_type)
    EditText type;
    @BindView(R.id.et_supplier_name)
    EditText supplierName;
    @BindView(R.id.et_supplier_phone)
    EditText supplierPhone;
    @BindView(R.id.add_product_btn)
    Button addProduct;
    @BindView(R.id.scroll)
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToDB();
            }
        });

    }

    private void addToDB() {
        if (checkError()) {
            DBHelper helper = new DBHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(DBContract.DBEntry.COLUMN_PRODUCT_NAME, name.getText().toString().trim());
            cv.put(DBContract.DBEntry.COLUMN_PRODUCT_TYPE, type.getText().toString().trim());
            cv.put(DBContract.DBEntry.COLUMN_PRODUCT_PRICE, Integer.parseInt(price.getText().toString().trim()));
            cv.put(DBContract.DBEntry.COLUMN_PRODUCT_QUANTITY, Integer.parseInt(quantity.getText().toString().trim()));
            cv.put(DBContract.DBEntry.COLUMN_PRODUCT_SUPPLIER_NAME, supplierName.getText().toString().trim());
            cv.put(DBContract.DBEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NO, Integer.parseInt(supplierPhone.getText().toString().trim()));

            long rowId = db.insert(DBContract.DBEntry.TABLE_NAME, null, cv);
            if (rowId == -1) {
                Toast.makeText(this, getString(R.string.failure), LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, getString(R.string.success), LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private boolean checkError() {
        if (TextUtils.isEmpty(name.getText())) {
            name.setError(getString(R.string.req));
            return false;
        } else if (TextUtils.isEmpty(price.getText())) {
            price.setError(getString(R.string.req));
            return false;
        } else if (TextUtils.isEmpty(quantity.getText())) {
            quantity.setError(getString(R.string.req));
            return false;
        } else if (TextUtils.isEmpty(type.getText())) {
            type.setError(getString(R.string.req));
            return false;
        } else if (TextUtils.isEmpty(supplierName.getText())) {
            supplierName.setError(getString(R.string.req));
            return false;
        } else if (TextUtils.isEmpty(supplierPhone.getText())) {
            supplierName.setError(getString(R.string.req));
            return false;
        }
        return true;

    }
}
