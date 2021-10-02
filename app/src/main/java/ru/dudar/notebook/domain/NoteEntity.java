package ru.dudar.notebook.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteEntity implements Serializable {
    private int id;
    private String title;
    private String detail;
    private String data;

    public NoteEntity(String title, String detail) {
        //this.id = id;
        this.title = title;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setData(String data) {
        this.data = data;
    }


    public String getData() {
        return data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }



}
