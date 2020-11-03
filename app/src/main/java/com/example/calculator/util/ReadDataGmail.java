package com.example.calculator.util;

import com.example.calculator.model.GmailModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadDataGmail {
    public List<GmailModel> getData() {
        List<GmailModel> list = new ArrayList<>();
        BufferedReader reader = null;
        String file = "D:\\20201\\android\\data.csv";
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null){
                String a[] = line.split("@@");
                String username = a[0];
                String message = a[1];
                SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
                Date date = f.parse(a[2]);
                boolean favorite = a[3] == "1";
                GmailModel gm = new GmailModel(username, message, date, favorite);
            }
            return list;
        } catch (IOException | ParseException e) {
            return null;
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
