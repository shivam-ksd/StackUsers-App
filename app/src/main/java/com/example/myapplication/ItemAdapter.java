package com.example.myapplication;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ItemAdapter extends PagedListAdapter<Item, ItemAdapter.Itemviewholder> {
    private Context mCtx;

    protected ItemAdapter(Context mCtx) {

        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }


    @NonNull
    @Override
    public Itemviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_item, parent, false);
        return new Itemviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Itemviewholder holder, int position) {
        final Item item = getItem(position);
        if (item != null) {
            Glide.with(mCtx)
                    .load(item.profile_image)
                    .into(holder.imageView);
            holder.textViewName.setText(item.display_name);
            holder.textView5.setText(String.valueOf(item.accept_rate));
            holder.textView9.setText(String.valueOf(item.badge_counts.gold));
            holder.textView10.setText(String.valueOf(item.badge_counts.silver));
            holder.textView11.setText(String.valueOf(item.badge_counts.bronze));
            holder.Enter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mCtx, activity2.class);
                    i.putExtra("a", item);
                    mCtx.startActivity(i);
                }
            });

        } else {
            Toast.makeText(mCtx, "Item is not given or null", Toast.LENGTH_LONG).show();
        }
    }

    private static DiffUtil.ItemCallback<Item> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Item>() {
                @Override
                public boolean areItemsTheSame(Item oldItem, Item newItem) {
                    return oldItem.user_id == newItem.user_id;
                }

                @Override
                public boolean areContentsTheSame(Item oldItem, Item newItem) {
                    return oldItem.equals(newItem);
                }
            };

    class Itemviewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName;
        TextView textView5;
        TextView textView9;
        TextView textView10;
        TextView textView11;
        Button Enter;


        public Itemviewholder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textView5 = itemView.findViewById(R.id.textView5);
            textView9 = itemView.findViewById(R.id.textView9);
            textView10 = itemView.findViewById(R.id.textView10);
            textView11 = itemView.findViewById(R.id.textView11);
            Enter = itemView.findViewById(R.id.button);


        }

    }
}
