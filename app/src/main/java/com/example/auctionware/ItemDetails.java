package com.example.auctionware;

// ItemDetailsActivity.java
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ItemDetails extends AppCompatActivity {

    private ImageView backbtn;
    private TextView heading,name,desc,currBidVal,minBidVal,bidderId, highestMsg;
    private EditText am;
    Button bidbtn;
    private ImageView img;

    String itemName, itemDescription, currentBid, minBid, imageUrl, prevBidder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        heading = findViewById(R.id.heading);
        name = findViewById(R.id.name);
        desc = findViewById(R.id.desc);
        bidderId = findViewById(R.id.bidder);
        currBidVal = findViewById(R.id.currBidVal);
        minBidVal = findViewById(R.id.minBidVal);
        am = findViewById(R.id.enteram);
        bidbtn = findViewById(R.id.bidbtn);
        img = findViewById(R.id.img);
        highestMsg = findViewById(R.id.highMsg);

        backbtn = findViewById(R.id.back);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String itemId = getIntent().getStringExtra("item_id");
        String bidderIdCheck = "Previous Bidder: [" + FirebaseAuth.getInstance().getCurrentUser().getEmail() + "]";


        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("Items").child(itemId);
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Retrieve the item details
                Item item = dataSnapshot.getValue(Item.class);
                if (item != null) {
                    itemName = item.getName();
                    itemDescription = item.getDescription();
                    currentBid = String.valueOf(item.getCurrBid());
                    minBid = String.valueOf(item.getMinBid());
                    imageUrl = item.getImageUrl();
                    prevBidder = item.getPrevBidder();

                    heading.setText(itemName);
                    name.setText(itemName);
                    desc.setText(itemDescription);
                    currBidVal.setText("₹"+String.valueOf(currentBid));
                    minBidVal.setText("₹"+String.valueOf(minBid));
                    if (prevBidder != null && !prevBidder.isEmpty()) {
                        bidderId.setText("Previous Bidder: " + prevBidder);
                    } else {
                        bidderId.setVisibility(View.GONE);
                    }

                    Picasso.get().load(imageUrl).into(img);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Log.e("ItemDetailsActivity", "Failed to read item details.", databaseError.toException());
            }
        });

        bidbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String enteredText = am.getText().toString();
                Log.d("BidderCheckId", bidderIdCheck);
                Log.d("BidderCheckId", prevBidder);

                if (!enteredText.isEmpty()) {
                    double enteredValue = Double.parseDouble(enteredText);
                    double currentBidVal = Double.parseDouble(currentBid);

                    if (enteredValue <= currentBidVal) {
                        am.setError("Enter Higher Bid");
                    }
                    else if(prevBidder.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){

                        highestMsg.setVisibility(View.VISIBLE);
                        highestMsg.setText("You are already the highest Bidder !");
                    }else {
                        currentBid = String.valueOf(enteredValue);
                        prevBidder = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                        currBidVal.setText("₹" + currentBid);
                        bidderId.setVisibility(View.VISIBLE);
                        bidderId.setText("Previous Bidder: [" + prevBidder+"]");

                        DatabaseReference itemRef = FirebaseDatabase.getInstance().getReference().child("Items").child(itemId);
                        itemRef.child("currBid").setValue(enteredValue);
                        itemRef.child("prevBidder").setValue(prevBidder);


                    }
                } else {
                    am.setError("Enter Bidding Amount");
                }

            }
        });

    }

}
