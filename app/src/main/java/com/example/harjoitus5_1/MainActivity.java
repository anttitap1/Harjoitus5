package com.example.harjoitus5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Counter counterOnCreate;
    private Counter counterOnStart;
    private Counter counterOnResume;

    public static final String PREFERENCES_NAME = "HarjoitusPreferences";
    public static final String CREATIONS_KEY = "CreationsKey";
    public static final String VISIBLES_KEY = "VisiblesKey";
    public static final String FOREGROUNDS_KEY = "ForegroundsKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefGet = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        String savedCreations = prefGet.getString(CREATIONS_KEY, "0");
        String savedVisibles = prefGet.getString(VISIBLES_KEY, "0");
        String savedForegrounds = prefGet.getString(FOREGROUNDS_KEY, "0");

        counterOnCreate = new Counter(Integer.parseInt(savedCreations), 0, 1000);
        counterOnStart = new Counter(Integer.parseInt(savedVisibles), 0, 1000);
        counterOnResume = new Counter(Integer.parseInt(savedForegrounds), 0, 1000);

        Log.d("Harjoitus5-1", "OnCreate");
        counterOnCreate.increment();

        updateUI();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("Harjoitus5-1", "OnStart");
        counterOnStart.increment();

        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("Harjoitus5-1", "OnResume");
        counterOnResume.increment();

        updateUI();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("Harjoitus5-1", "OnPause");

        SharedPreferences prefPut = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putString(CREATIONS_KEY, Integer.toString(counterOnCreate.getCountValue()));
        prefEditor.putString(VISIBLES_KEY, Integer.toString(counterOnStart.getCountValue()));
        prefEditor.putString(FOREGROUNDS_KEY, Integer.toString(counterOnResume.getCountValue()));
        prefEditor.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("Harjoitus5-1", "OnStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("Harjoitus5-1", "OnRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("Harjoitus5-1", "OnDestroy");
    }

    public void onClickReset(View view) {
        counterOnCreate.reset();
        counterOnResume.reset();
        counterOnStart.reset();

        updateUI();
    }

    private void updateUI() {
        TextView textViewCreations = findViewById(R.id.textViewCreations);
        TextView textViewVisibles = findViewById(R.id.textViewVisibles);
        TextView textViewForegrounds = findViewById(R.id.textViewForegrounds);

        textViewCreations.setText(Integer.toString(counterOnCreate.getCountValue()));
        textViewVisibles.setText(Integer.toString(counterOnStart.getCountValue()));
        textViewForegrounds.setText(Integer.toString(counterOnResume.getCountValue()));
    }
}
