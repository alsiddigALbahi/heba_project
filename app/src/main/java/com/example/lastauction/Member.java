package com.example.lastauction;

public class Member {
    String title, image, des, pri;
    String orgpri, overauc, aucnum;

    public Member() {
    }

    public Member(String title, String image, String des, String pri, String orgpri, String overauc, String aucnum) {
        this.title = title;
        this.image = image;
        this.des = des;
        this.pri = pri;
        this.orgpri = orgpri;
        this.overauc = overauc;
        this.aucnum = aucnum;
    }

    public String getOrgpri() {
        return orgpri;
    }

    public void setOrgpri(String orgpri) {
        this.orgpri = orgpri;
    }

    public String getOverauc() {
        return overauc;
    }

    public void setOverauc(String overauc) {
        this.overauc = overauc;
    }

    public String getAucnum() {
        return aucnum;
    }

    public void setAucnum(String aucnum) {
        this.aucnum = aucnum;
    }

    public String getPri() {
        return pri;
    }

    public void setPri(String pri) {
        this.pri = pri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
