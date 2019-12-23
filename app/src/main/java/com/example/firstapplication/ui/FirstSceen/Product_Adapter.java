package com.example.firstapplication.ui.FirstSceen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapplication.R;
import com.example.firstapplication.ui.util.data.Product;

import java.util.List;

public class Product_Adapter extends RecyclerView.Adapter<Product_Adapter.ViewHolder> {

    private List<Product> datalist;
    private Context context;
    private MyAdapterItemClickListener listener;
    public Product_Adapter(Context context, List<Product> datalist) {
        this.datalist = datalist;
    }
    @NonNull
    @Override
    public Product_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.myproductlist, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Product_Adapter.ViewHolder holder, final int position) {

        holder.nametextView.setText(datalist.get(position).getName());
        holder.idTextView.setText(""+datalist.get(position).getId());
        holder.catagoryTextView.setText(""+datalist.get(position).getCatagory());
        holder.descriptionTextView.setText(""+datalist.get(position).getDescription());
        holder.priceTextView.setText(""+datalist.get(position).getPrice());
        holder.cardViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                {
                    listener.onItemClick(datalist.get(position),position);


                }
            }
        });
        holder.cardViewLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onItemLongClick(datalist.get(position),position);
                return false;
            }
        });
    }
    public void setListener(MyAdapterItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView nametextView;
        private CardView cardViewLayout;
        private TextView idTextView;
        private TextView catagoryTextView;
        private TextView descriptionTextView;
        private TextView priceTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nametextView = itemView.findViewById(R.id.nameTV);
            cardViewLayout = itemView.findViewById(R.id.cardView);
            idTextView=itemView.findViewById(R.id.idTV);
            catagoryTextView=itemView.findViewById(R.id.catogory_TV);
            descriptionTextView=itemView.findViewById(R.id.description_TV);
            priceTextView=itemView.findViewById(R.id.price_TV);

        }
    }
    public interface MyAdapterItemClickListener{
        public void onItemClick(Product item,int position);
        public void onItemLongClick(Product item,int position);
    }
}
