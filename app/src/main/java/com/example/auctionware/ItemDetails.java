package com.example.auctionware;

// ItemDetailsActivity.java
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ItemDetails extends AppCompatActivity {

    private ImageView backbtn;
    private TextView heading,name,desc,currBidVal,minBidVal;
    private EditText am;
    Button bidbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        heading = findViewById(R.id.heading);
        name = findViewById(R.id.name);
        desc = findViewById(R.id.desc);
        currBidVal = findViewById(R.id.currBidVal);
        minBidVal = findViewById(R.id.minBidVal);
        am = findViewById(R.id.enteram);
        bidbtn = findViewById(R.id.bidbtn);

        backbtn = findViewById(R.id.back);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String itemName = getIntent().getStringExtra("item_name");
        String itemDescription = getIntent().getStringExtra("item_desc");
        String currentBid = getIntent().getStringExtra("item_curr_bid");
        String minBid = getIntent().getStringExtra("item_min_bid");
        heading.setText(itemName);
        name.setText(itemName);
        desc.setText(itemDescription);
        currBidVal.setText("₹"+String.valueOf(currentBid));
        minBidVal.setText("₹"+String.valueOf(minBid));

        bidbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String enteredText = am.getText().toString();
                if (!enteredText.isEmpty()) {
                    double enteredValue = Double.parseDouble(enteredText);
                    double currentBidVal = Double.parseDouble(currentBid);
                    // Now you can proceed with the comparison
                    if (enteredValue <= currentBidVal) {
                        // Perform actions for valid input
                        am.setError("Enter Higher Bid");
                    } else {
                        // Perform actions for invalid input
                    }
                } else {
                    // Handle case where EditText is empty
                    // For example, display a message to the user
                }

            }
        });

    }

}
