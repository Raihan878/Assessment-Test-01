package com.example.firstapplication.ui.FirstSceen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapplication.R;
import com.example.firstapplication.ui.util.SqliteHelper;
import com.example.firstapplication.ui.util.data.Product;

import java.util.ArrayList;
import java.util.List;

public class FirstSceenActivity extends AppCompatActivity implements View.OnClickListener, Product_Adapter.MyAdapterItemClickListener {

    private RecyclerView recyclerView;
    private Button addBtn;
    private EditText name;
    private EditText catagory;
    private EditText description;
    private EditText price;
    private final String TAG = "firstactivity";
    List<Product> products;
    private SqliteHelper dbHelper;
    private int toUpdate=0;
    private Product_Adapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_sceen);
        name = findViewById(R.id.nameET);
        catagory = findViewById(R.id.catagoryET);
        description = findViewById(R.id.descriptionET);
        price = findViewById(R.id.priceET);
        recyclerView = findViewById(R.id.recyclerView);
        addBtn = findViewById(R.id.addBtn);

        products = new ArrayList<>();

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Home");

        productAdapter = new Product_Adapter(FirstSceenActivity.this, products);
        recyclerView.setAdapter(productAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(FirstSceenActivity.this));

        addBtn.setOnClickListener(this);
        productAdapter.setListener(this);

        dbHelper=new SqliteHelper(this);
        showList();

    }

    @Override
    public void onClick(View view) {

        String var = name.getText().toString().trim();
        String ctagory = catagory.getText().toString().trim();
        String des = description.getText().toString().trim();
        String pri = price.getText().toString().trim();

        if(toUpdate==0)
        {
            dbHelper.save(var,ctagory,des,Integer.parseInt(pri));
        }
        else {

            dbHelper.update(new Product(toUpdate,var,ctagory,des,Integer.parseInt(pri)));
            toUpdate=0;

        }
        showList();
        clearAll();
        Toast.makeText(FirstSceenActivity.this,"Data Saved!",Toast.LENGTH_SHORT).show();

    }
    public void showList(){
        products.clear();
        products.addAll(dbHelper.getAllProducts());
        productAdapter.notifyDataSetChanged();
    }
    public void clearAll()
    {
        name.setText("");
        catagory.setText("");
        description.setText("");
        price.setText("");
        toUpdate=0;
    }

    @Override
    public void onItemClick(Product item, int position) {
        name.setText(item.getName());
        catagory.setText(""+item.getCatagory());
        description.setText(""+item.getDescription());
        price.setText(""+item.getPrice());
        toUpdate=item.getId();
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemLongClick(Product item, int position) {
        dbHelper.delete(item.getId());
        showList();
    }
}
