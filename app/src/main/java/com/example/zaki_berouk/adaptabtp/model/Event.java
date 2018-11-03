package com.example.zaki_berouk.adaptabtp.model;

public class Event {
    private String name;
    private String date;
    private String category;
    private String time;
    private String descr;

    public Event(String name, String date, String category, String time, String descr) {
        this.name = name;
        this.date = date;
        this.category = category;
        this.time = time;
        this.descr = descr;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescr() { return descr; }

    public void setDescr(String descr) { this.descr = descr; }
}
