package com.lee.model;

public class Read {
    private String Uno;
    private String Uname;
    private String Bno;
    private String Bname;
    private float Rpage;
    private float Rpecent;
    private float Rpiont;

    public String getBno() {
        return Bno;
    }

    public void setRpiont(float rpiont) {
        Rpiont = rpiont;
    }

    public float getRpiont() {
        return Rpiont;
    }

    public String getUno() {
        return Uno;
    }

    public float getRpage() {
        return Rpage;
    }

    public float getRpecent() {
        return Rpecent;
    }

    public String getBname() {
        return Bname;
    }

    public String getUname() {
        return Uname;
    }

    public void setBname(String bname) {
        Bname = bname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public void setRpage(float rpage) {
        Rpage = rpage;
    }

    public void setBno(String bno) {
        Bno = bno;
    }

    public void setUno(String uno) {
        Uno = uno;
    }

    public void setRpecent(float rpecent) {
        Rpecent = rpecent;
    }
}
