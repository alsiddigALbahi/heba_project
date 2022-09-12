package com.example.lastauction;

public class ReadWriteUserDetails {
    public String datee,mobilee,genderr;

    ReadWriteUserDetails(){};

    public ReadWriteUserDetails( String textdate, String textGender, String textmobile) {
        this.datee = textdate;
        this.mobilee = textmobile;
        this.genderr = textGender;
    }
}
