package com.vintek_ss.vince.kiddocare;


public class customAdapter {
    private String ChildFNAME;
    private String ChildLNAME;


    public customAdapter(String ChildFNAME, String ChildLNAME) {
        super();
        this.ChildFNAME = ChildFNAME;
        this.ChildLNAME = ChildLNAME;
    }

    public String getChildFNAME() {
        return ChildFNAME;
    }


    public String getChildLNAME() {
        return ChildLNAME;
    }

}
