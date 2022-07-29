package com.example.yoyoiq;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.yoyoiq.Adapter.PageAdapter;
import com.example.yoyoiq.WalletPackage.AddCash;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ContestActivity extends AppCompatActivity {
    TextView walletTV, backPress, matchATv, matchBTv;
    String matchA = "", matchB = "", match_id, logo_url_a, logo_url_b, date_start, date_end;
    LinearLayout createTeamLayout;
    ViewPager viewPager;
    TabLayout tabLayout;
    TabItem tabItem1, tabItem2, tabItem3;
    PageAdapter pageAdapter;
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

        matchA = getIntent().getStringExtra("shortNameA");
        matchB = getIntent().getStringExtra("shortNameB");
        match_id = getIntent().getStringExtra("match_id");
        logo_url_a = getIntent().getStringExtra("logo_url_a");
        logo_url_b = getIntent().getStringExtra("logo_url_b");
        date_start = getIntent().getStringExtra("date_start");
        date_end = getIntent().getStringExtra("date_end");
        matchATv.setText(matchA);
        matchBTv.setText(matchB);

        tabLayout = findViewById(R.id.tabLayout);
        tabItem1 = findViewById(R.id.contests);
        tabItem2 = findViewById(R.id.myContests);
        tabItem3 = findViewById(R.id.myTeams);
        viewPager = findViewById(R.id.viewPager);
        matchList = findViewById(R.id.matchList);
        createTeam = findViewById(R.id.createTeam);


        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), match_id, matchA, matchB, logo_url_a, logo_url_b, date_start, date_end);
        viewPager.setAdapter(pageAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2) {
                    pageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        walletTV.setOnClickListener(view -> {
            Intent intent = new Intent(ContestActivity.this, AddCash.class);
            startActivity(intent);
        });

        backPress.setOnClickListener(view -> onBackPressed());

        createTeam.setOnClickListener(view -> {
            Intent intent = new Intent(this, CreateTeamActivity.class);
            intent.putExtra("match_id", match_id);
            intent.putExtra("matchA", matchA);
            intent.putExtra("matchB", matchB);
            intent.putExtra("logo_url_a", logo_url_a);
            intent.putExtra("logo_url_b", logo_url_b);
            intent.putExtra("date_start", date_start);
            intent.putExtra("date_end", date_end);
            startActivity(intent);

        });
    }
}