package com.example.auctionware;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

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
        holder.textViewMinBid.setText("Minimum Bid: ₹" + String.valueOf(item.getMinBid()));
//        holder.textViewPrevBidder.setText("Previous Bidder: " + item.getPrevBidder());

        Picasso.get().load(item.getImageUrl()).into(holder.item_img);

        // Set click listener for item card
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemDetails.class);
                intent.putExtra("item_id", item.getId());
//                intent.putExtra("item_name", item.getName());
//                intent.putExtra("item_desc", item.getDescription());
//                intent.putExtra("item_curr_bid", String.valueOf(item.getCurrBid()));
//                intent.putExtra("item_min_bid", String.valueOf(item.getMinBid()));
//                intent.putExtra("item_prev_bidder", item.getPrevBidder());
//                intent.putExtra("item_image", item.getImageUrl());
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
        TextView textViewMinBid;
        ImageView item_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewCurrBid = itemView.findViewById(R.id.textViewCurrBid);
            textViewMinBid = itemView.findViewById(R.id.textViewMinBid);
            item_img = itemView.findViewById(R.id.item_img);
        }
    }
}