package com.example.firstapplication.ui.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.firstapplication.ui.util.data.Product;

import java.util.ArrayList;
import java.util.List;

public class SqliteHelper extends SQLiteOpenHelper {
    private final String TABLE1="tbl_product";
    private final String TABLE1_COL1="id";
    private final String TABLE1_COL2="name";
    private final String TABLE1_COL3="catagory";
    private final String TABLE1_COL4="description";
    private final String TABLE1_COL5="price";
    public SqliteHelper(@Nullable Context context) {
        super(context, "productdb", null, 1);
    }

    public void save(String name, String catagory, String description, int price){
        ContentValues values=new ContentValues();
        values.put(TABLE1_COL2,name);
        values.put(TABLE1_COL3,catagory);
        values.put(TABLE1_COL4,description);
        values.put(TABLE1_COL5,price);

        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(TABLE1,null,values);

    }

    public List<Product> getAllProducts()
    {
        List<Product> products=new ArrayList<>();
        String sql="SELECT * FROM "+TABLE1;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            while (cursor.moveToNext())
            {
                String id=cursor.getString(cursor.getColumnIndex(TABLE1_COL1));
                String name=cursor.getString(cursor.getColumnIndex(TABLE1_COL2));
                String catg=cursor.getString(cursor.getColumnIndex(TABLE1_COL3));
                String des=cursor.getString(cursor.getColumnIndex(TABLE1_COL4));
                String price=cursor.getString(cursor.getColumnIndex(TABLE1_COL5));

                Product product=new Product(Integer.parseInt(id),name,catg,des, Integer.parseInt(price));
                products.add(product);
            }
        }
        return products;
    }




    public void update(Product product){
        int id=product.getId();
        String name = product.getName();
        String catagory = product.getCatagory();
        String des = product.getDescription();
        int price = product.getPrice();

        ContentValues values=new ContentValues();
        values.put(TABLE1_COL2,""+name);
        values.put(TABLE1_COL3,""+catagory);
        values.put(TABLE1_COL4,""+des);
        values.put(TABLE1_COL5,""+price);

        SQLiteDatabase db=this.getWritableDatabase();
        db.update(TABLE1,values,""+TABLE1_COL1+" = ?",new String[]{""+id});

    }

    public void delete(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE1,""+TABLE1_COL1+" = ?",new String[]{""+id});
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1= "CREATE TABLE "+TABLE1+" ("+TABLE1_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TABLE1_COL2+" VARCHAR(50), "+TABLE1_COL3+" VARCHAR(50), "+TABLE1_COL4+" VARCHAR(500),"+TABLE1_COL5+" INTEGER)";
        db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropQuery="DROP TABLE IF EXISTS "+TABLE1;
        db.execSQL(dropQuery);
        onCreate(db);
    }
}