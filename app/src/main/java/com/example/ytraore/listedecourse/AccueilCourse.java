package com.example.ytraore.listedecourse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ytraore.listedecourse.R;

/**
 * Created by ytraore on 08/12/2014.
 */
public class AccueilCourse extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);

    }
 public void onButtonClick(View view){
        Intent i= new Intent(AccueilCourse.this, MainActivity.class);
        startActivity(i);

    }
}