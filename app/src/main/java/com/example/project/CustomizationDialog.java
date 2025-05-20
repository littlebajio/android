package com.example.project;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.ArrayAdapter;

public class CustomizationDialog {
    public interface OnApply{ void run(); }

    public static void show(Context c, OnApply cb){
        String[] names={"Фиолетовый","Голубой","Зеленый","Розовый"};
        int cur = AppPreferences.accIdx(c);
        ArrayAdapter<String> ad = new ArrayAdapter<>(c,
                android.R.layout.simple_list_item_single_choice, names);

        new AlertDialog.Builder(c)
                .setTitle("Дополнение")
                .setSingleChoiceItems(ad,cur,null)
                .setPositiveButton("OK",(d,w)->{
                    int idx=((AlertDialog)d).getListView()
                            .getCheckedItemPosition();
                    AppPreferences.setAccIdx(c,idx);
                    cb.run();
                })
                .setNegativeButton("Отмена",null)
                .show();
    }
}
