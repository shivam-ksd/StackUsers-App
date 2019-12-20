package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);
        Intent i=getIntent();
        Item it=i.getExtras().getParcelable("a");
        TextView Ter=findViewById(R.id.textView21);
        Ter.setText(it.display_name);
    }
}
