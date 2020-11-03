package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.adapter.GmailAdapter;
import com.example.calculator.model.GmailModel;
import com.example.calculator.util.ReadDataGmail;

import java.util.List;

public class GmailActivity extends AppCompatActivity {

    List<GmailModel> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gmail_layout);

        ReadDataGmail read = new ReadDataGmail();
        list = read.getData();

        GmailAdapter adapter = new GmailAdapter(list, this);
        ListView view = findViewById(R.id.list_view);
        view.setAdapter(adapter);
    }
}
