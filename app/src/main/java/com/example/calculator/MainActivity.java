package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private StringBuilder s = new StringBuilder("0");
    private boolean flag = false; // nhiều dấu phép tính
    private boolean flagNumber = true; // không được 2 số 0 liên tiếp
    private boolean flagResult = true; // xóa kết quá cũ

    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_multiply;
    private Button btn_divide;
    private Button btn_subtract;
    private Button btn_add;
    private Button btn_dot;
    private Button btn_CE;
    private Button btn_C;
    private Button btn_BS;
    private Button btn_result;

    private TextView text_ressult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_0 =  (Button) findViewById(R.id.btn_0);
        btn_1 =  (Button) findViewById(R.id.btn_1);
        btn_2 =  (Button) findViewById(R.id.btn_2);
        btn_3 =  (Button) findViewById(R.id.btn_3);
        btn_4 =  (Button) findViewById(R.id.btn_4);
        btn_5 =  (Button) findViewById(R.id.btn_5);
        btn_6 =  (Button) findViewById(R.id.btn_6);
        btn_7 =  (Button) findViewById(R.id.btn_7);
        btn_8 =  (Button) findViewById(R.id.btn_8);
        btn_9 =  (Button) findViewById(R.id.btn_9);
        btn_multiply =  (Button) findViewById(R.id.btn_multiply);
        btn_divide =  (Button) findViewById(R.id.btn_divide);
        btn_subtract =  (Button) findViewById(R.id.btn_subtract);
        btn_add =  (Button) findViewById(R.id.btn_add);
        btn_dot =  (Button) findViewById(R.id.btn_dot);
        btn_CE =  (Button) findViewById(R.id.btn_CE);
        btn_C =  (Button) findViewById(R.id.btn_C);
        btn_BS =  (Button) findViewById(R.id.btn_BS);
        btn_result = (Button) findViewById(R.id.btn_result);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_subtract.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_dot.setOnClickListener(this);;
        btn_CE.setOnClickListener(this);
        btn_C.setOnClickListener(this);
        btn_BS.setOnClickListener(this);
        btn_result.setOnClickListener(this);

        text_ressult = (TextView) findViewById(R.id.text_result);
    }

    @Override
    public void onClick(View v) {
        if(flagResult){
            s = new StringBuilder("0");
            flag = false;
            flagNumber = true;
            text_ressult.setText(s.toString());
            flagResult = false;
        }
        int t = v.getId();
        Button btn;
        switch (t) {
            case R.id.btn_CE:
                int i = s.length() - 1;
                for(; i >= 0; i--){
                    if(!checkNumber(s.charAt(i))) break;
                }
                if(i < 0){
                    s = new StringBuilder("0");
                    flagNumber = true;
                    flag = false;
                }
                else {
                    s = new StringBuilder(s.substring(0, i + 1));
                    flag = true;
                }
                text_ressult.setText(s.toString());
                break;


            case R.id.btn_result:
                Double res = caculator(s.toString());
                if(res % 1 == 0){
                    text_ressult.setText("" + Math.round(res));
                }else{
                    text_ressult.setText(res + "");
                }
                flagResult = true;
                break;

            case R.id.btn_C:
                s = new StringBuilder("0");
                flag = false;
                flagNumber = true;
                text_ressult.setText(s.toString());
                break;

            case R.id.btn_BS:
                s = new StringBuilder(s.substring(0, s.length() - 1));
                if(s.length() == 0) s.append("0");
                else{
                    char c = s.charAt(s.length() - 1);
                    // warning
                    flag = !checkNumber(c);
                }
                text_ressult.setText(s.toString());
                break;

            case R.id.btn_change:
                break;

            case R.id.btn_multiply:
            case R.id.btn_divide:
            case R.id.btn_add:
            case R.id.btn_subtract:
                if(flag) s = new StringBuilder(s.substring(0, s.length() - 1));
                flag = true;
                btn = (Button) findViewById(t);
                s.append(btn.getText());
                text_ressult.setText(s.toString());
                flagNumber = false;
                break;

            default:
                btn = (Button) findViewById(t);
                String str = btn.getText().toString();
                if(flagNumber) {
                    if (!str.equals("0")) {
                        if(!str.equals(".")) s = new StringBuilder(s.substring(0, s.length() - 1));
                        s.append(str);
                        flagNumber = false;
                    }
                }else{
                    if(flag && str.equals("0")) flagNumber = true;
                    s.append(str);
                }
                text_ressult.setText(s.toString());
                flag = false;
        }
    }

    private boolean checkNumber(char c){
        return (c >= '0' && c <= '9') || c == '.';
    }

    private int getPriority(String op)
    {
        if (op.equals("x") || op.equals("/"))
            return 2;
        if (op.equals("+") || op.equals("-"))
            return 1;
        return 0;
    }

    private double cal(String s, Double before, Double after){
        if(s.equals("+")) return before + after;
        else if(s.equals("-")) return before - after;
        else if(s.equals("x")) return before * after;
        else if(s.equals("/")) return before/after;
        else return 0d;
    }

    private double caculator(String s) {
        s = s.replace(" ", "");
        s = s.trim();
        List<String> list = new ArrayList<>();
        Stack<String> st = new Stack<>();
        String str = "0";
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(checkNumber(c)) str+=c;
            else {
                list.add(str);
                str = "0";
                if(st.empty()) st.push(c + "");
                else {
                    if(getPriority(st.peek()) >= getPriority(c + "")) {
                        list.add(st.pop());
                        st.push(c + "");
                    }else st.push("" + c);
                }
            }
        }
        list.add(str);
        while(!st.empty()) {
            list.add(st.pop());
        }
        Stack<Double> stack = new Stack<>();
        Double before = 0d;
        Double after = 0d;
        for(String tmp : list) {
            if(tmp.charAt(0) == '0') {
                stack.push(Double.parseDouble(tmp));
            }else {
                after = stack.pop();
                before = stack.pop();
                stack.push(cal(tmp, before, after));
            }
        }
        return stack.pop();
    }
}