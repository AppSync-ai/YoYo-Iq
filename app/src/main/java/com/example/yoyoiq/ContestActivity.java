package com.example.yoyoiq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.WalletPackage.AddCash;

public class ContestActivity extends AppCompatActivity {
    TextView walletTV, backPress, matchATv, matchBTv;
    String matchA = "", matchB = "";
    LinearLayout createTeamLayout;
    TextView matchList, createTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);
        walletTV = findViewById(R.id.walletTV);
        backPress = findViewById(R.id.backPress);
        matchATv = findViewById(R.id.matchATv);
        matchBTv = findViewById(R.id.matchBTv);
        createTeamLayout = findViewById(R.id.createTeamLayout);
        matchList = findViewById(R.id.matchList);
        createTeam = findViewById(R.id.createTeam);

        matchA = getIntent().getStringExtra("shortNameA");
        matchB = getIntent().getStringExtra("shortNameB");
        matchATv.setText(matchA);
        matchBTv.setText(matchB);

        walletTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContestActivity.this, AddCash.class);
                startActivity(intent);
            }
        });

        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        createTeam.setOnClickListener(view -> {
            Intent intent = new Intent(ContestActivity.this, CreateTeamActivity.class);
            startActivity(intent);

        });
    }
}