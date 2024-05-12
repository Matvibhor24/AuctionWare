package com.example.auctionware;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BiddingItemAdapter extends RecyclerView.Adapter<BiddingItemAdapter.ViewHolder> {
    private List<Item> items;
    private Context context;

    public BiddingItemAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bidding_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.textViewName.setText(item.getName());
        holder.textViewDescription.setText(item.getDescription());
        holder.textViewCurrBid.setText("Current Bid: ₹" + String.valueOf(item.getCurrBid()));
        holder.textViewPrevBid.setText("Previous Bid: ₹" + String.valueOf(item.getPrevBid()));

        // Set click listener for item card
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to item details page
                Intent intent = new Intent(context, ItemDetails.class);
                intent.putExtra("item_name", item.getName());
                intent.putExtra("item_desc", item.getDescription());
                intent.putExtra("item_curr_bid", String.valueOf(item.getCurrBid()));
                intent.putExtra("item_prev_bid", String.valueOf(item.getPrevBid()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDescription;
        TextView textViewCurrBid;
        TextView textViewPrevBid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewCurrBid = itemView.findViewById(R.id.textViewCurrBid);
            textViewPrevBid = itemView.findViewById(R.id.textViewPrevBid);
        }
    }
}