package com.example.langtranslation;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static android.content.ContentValues.TAG;


public class YandexRequest extends Thread{
    Languages languages;
    final static String API_KEY = "trnsl.1.1.20191007T114747Z.42023c5406dedaa4.c283e670320c21ad9e2362d95cb3f804bde399b8";

    @Override
    public void run() {
        String url_supported_lang;
        String dataLang;
        URL url;
        OutputStream out = null;
        HttpURLConnection urlConnection;
        Scanner in;
        Gson gson;

        url = null;
        urlConnection = null;
        in = null;
        url_supported_lang = "https://translate.yandex.net/api/v1.5/tr.json/getLangs";
        dataLang = "ui=en&key=" + API_KEY;


        try {
            url = new URL(url_supported_lang);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        urlConnection.setDoOutput(true); // setting POST method


        try {
            out = urlConnection.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out.write(dataLang.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            in = new Scanner(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (in.hasNext()) {
            gson = new Gson();
            languages = gson.fromJson(in.nextLine(), Languages.class);
            languages.createArrayLanguages();
        }
    }
}

