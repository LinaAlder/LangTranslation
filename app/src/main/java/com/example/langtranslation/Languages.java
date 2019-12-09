package com.example.langtranslation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Languages extends Thread {
    String[] dirs;
    ArrayList<String> langsFrom;
    ArrayList<String> langsTo;
    String[] direction;

    public void createArrayLanguages() {

        this.langsFrom = new ArrayList<>();
        this.langsTo = new ArrayList<>();

        for(String d : this.dirs) {
            this.direction = d.split("-");
            this.langsFrom.add(this.dirs[0]);
            this.langsTo.add(this.dirs[1]);
        }
    }

    public String[] getLang(String fromLang) {
        // возвращает список языков, на которые возможен перевод с fromLang
        HashSet<String> lang;

        lang = new HashSet<>();

        for(int i = 0; i < this.langsTo.size(); i++) {
            if(this.langsFrom.get(i).equals(fromLang))
                lang.add(this.langsTo.get(i));
        }

        return lang.toArray(new String[0]);
    }

    public String[] getLangFrom() {
        HashSet<String> lang;

        lang = new HashSet<>(this.langsFrom);

        return lang.toArray(new String[0]);
    }

    @Override
    public String toString() {
        StringBuffer sb;
        sb = new StringBuffer();
        for(String s : this.dirs) sb.append(s);
        return sb.toString();
    }

    boolean directionCorrect(String direction) {
        for(String s : this.dirs) {
            if (s.equals(direction)) return true;
        }
        return false;
    }
}
