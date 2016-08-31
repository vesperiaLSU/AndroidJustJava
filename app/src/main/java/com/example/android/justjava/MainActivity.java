package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

//This app displays an order form to order coffee
public class MainActivity extends AppCompatActivity {

    private int teamA = 0;
    private int teamB = 0;
    TextView teamAScore;
    TextView teamBScore;

    private void score(String teamName, int score) {
        if (Objects.equals(teamName, "A")) {
            teamA += score;
            teamAScore.setText(String.valueOf(teamA));
        } else if (Objects.equals(teamName, "B")) {
            teamB += score;
            teamBScore.setText(String.valueOf(teamB));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamAScore = (TextView) findViewById(R.id.team_a_score);
        teamBScore = (TextView) findViewById(R.id.team_b_score);
        teamAScore.setText(String.valueOf(teamA));
        teamBScore.setText(String.valueOf(teamB));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    public void score3Point(View view) {
        score(view.getTag().toString(), 3);
    }

    public void score2Point(View view) {
        score(view.getTag().toString(), 2);
    }

    public void scoreFreeThrow(View view) {
        score(view.getTag().toString(), 1);
    }

    public void reset(View view) {
        teamA = 0;
        teamB = 0;
        teamAScore.setText(String.valueOf(teamA));
        teamBScore.setText(String.valueOf(teamB));
    }

    public void emailResult(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Today's Game Score");
        intent.putExtra(Intent.EXTRA_TEXT, String.format(Locale.getDefault(), "The score of today's game is:\nTeam A: %d\nTeam B: %d", teamA, teamB));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
