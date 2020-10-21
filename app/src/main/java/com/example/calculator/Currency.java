package com.example.calculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Currency extends AppCompatActivity{
    List<String> list;
    List<Double> currency;
    Spinner spinner1;
    Spinner spinner2;
    EditText editText;
    TextView text_currency;

    int p1 = 0;
    int p2 = 15;
    Double amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency);

        list = new ArrayList<>();
        currency = new ArrayList<>();

        list.add("USD"); currency.add(23177.00);
        list.add("JPY"); currency.add(221.01);
        list.add("CNY"); currency.add(3482.75);
        list.add("EUR"); currency.add(27480.97);
        list.add("GBP"); currency.add(30248.00);
        list.add("CAD"); currency.add(17670.78);
        list.add("CHF"); currency.add(25643.95);
        list.add("AUD"); currency.add(16418.58);
        list.add("HKD"); currency.add(2990.54);
        list.add("SGD"); currency.add(17096.00);
        list.add("MYR"); currency.add(5590.21);
        list.add("IDR"); currency.add(1.5815);
        list.add("THB"); currency.add(741.43);
        list.add("PHP"); currency.add(477.3546);
        list.add("MMK"); currency.add(17.87);
        list.add("VND"); currency.add(1.00);

        spinner1 = (Spinner) findViewById(R.id.spinner_1);
        spinner2 = (Spinner) findViewById(R.id.spinner_2);

        editText =  (EditText) findViewById(R.id.editText_1);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s + "";
                if(str == ""){
                    amount = null;
                    text_currency.setText("");
                }
                else{
                    amount = Double.parseDouble(s + "");
                    text_currency.setText("" + convert(amount));
                }
            }
        });
        text_currency = (TextView) findViewById(R.id.text_currency);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                list);

        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                p1 = position;
                if(amount != null){
                    text_currency.setText("" + convert(amount));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                list);

        spinner2.setAdapter(adapter2);
        spinner2.setSelection(15);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                p2 = position;
                if(amount != null){
                    text_currency.setText("" + convert(amount));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private Double convert(Double am){
        return (Math.round(am*currency.get(p1)/currency.get(p2) * 1000))/1000d;
    }
}
