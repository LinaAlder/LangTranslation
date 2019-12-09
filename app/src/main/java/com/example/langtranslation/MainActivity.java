package com.example.langtranslation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner sp;
    private Languages languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] currentLangs;
        ArrayAdapter arrayAdapter;
        YandexRequest yandexRequest;
        // связываем элементы интерфейса с разметкой


        yandexRequest = new YandexRequest();
        yandexRequest.start();
        try {
            yandexRequest.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.languages = yandexRequest.languages;

        this.sp = findViewById(R.id.spinnerFrom);
        currentLangs = this.languages.getLangFrom();
        // указываем контекст, разметку и источник данных
        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, currentLangs);

        // назначаем обработчиком экземпляр активности, т.к. в нём реализован
        // интерфейс AdapterView.OnItemSelectedListener
        // можно реализовать интерфейс в виде отдельного или анонимного класса
        sp.setOnItemSelectedListener(this);

        sp.setAdapter(arrayAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // для выполнения задания удобно реализовать функцию getLang
        TextView textView;
        String langFrom;
        String[] langsTo;
        ArrayAdapter arrayAdapter;

        textView = (TextView) view;
        langFrom = textView.getText().toString();
        langsTo = this.languages.getLang(langFrom);
        this.sp = findViewById(R.id.spinnerTo);

        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, langsTo);
        sp.setAdapter(arrayAdapter);

        Toast.makeText(this, "Selected " + langsTo, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}