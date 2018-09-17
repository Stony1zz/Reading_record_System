package com.lee.model;

public class Book {
    private String Bno;
    private String Bname;
    private String Bpage;
    private String Btype;

    public void setBno(String bno) {
        Bno = bno;
    }

    public void setBtype(String btype) {
        Btype = btype;
    }

    public void setBpage(String bpage) {
        Bpage = bpage;
    }

    public void setBname(String bname) {
        Bname = bname;
    }

    public String getBno() {
        return Bno;
    }

    public String getBtype() {
        return Btype;
    }

    public String getBpage() {
        return Bpage;
    }

    public String getBname() {
        return Bname;
    }
}
