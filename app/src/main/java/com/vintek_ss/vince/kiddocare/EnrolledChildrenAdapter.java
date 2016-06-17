package com.vintek_ss.vince.kiddocare;

import android.graphics.Bitmap;

public class EnrolledChildrenAdapter {
    private String childRecord;
    private String ChildFNAME;
    private String ChildLNAME;
    private String childEdate;
    private String childActive;
    private Bitmap ChildPIC;
    private String childLoggedIn;

    public EnrolledChildrenAdapter(String childRecord, String ChildFNAME,
                                   String ChildLNAME, String childEdate, String childActive,
                                   Bitmap bmChildPIC, String childLoggedIn) {
        super();
        this.ChildFNAME = ChildFNAME;
        this.ChildLNAME = ChildLNAME;
        this.childRecord = childRecord;
        this.childEdate = childEdate;
        this.childActive = childActive;
        this.ChildPIC = bmChildPIC;
        this.childLoggedIn = childLoggedIn;
    }

    public String getChildFNAME() {
        return ChildFNAME;
    }


    public String getChildLNAME() {
        return ChildLNAME;
    }


    public Bitmap getChildPIC() {
        return ChildPIC;
    }

    public String getchildRecord() {
        return childRecord;
    }


    public String getchildEdate() {
        return childEdate;
    }


    public String getchildActive() {
        return childActive;
    }

    public String childLoggedIn() {
        return childLoggedIn;
    }

}
