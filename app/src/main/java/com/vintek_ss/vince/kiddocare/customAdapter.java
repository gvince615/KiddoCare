package com.vintek_ss.vince.kiddocare;


public class customAdapter {
    private String ChildRowNum;
    private String ChildFNAME;
    private String ChildLNAME;


    public customAdapter(String ChildRowNum, String ChildFNAME, String ChildLNAME) {
        super();
        this.ChildRowNum = ChildRowNum;
        this.ChildFNAME = ChildFNAME;
        this.ChildLNAME = ChildLNAME;
    }

    public String getChildRowNum() {
        return ChildRowNum;
    }

    public String getChildFNAME() {
        return ChildFNAME;
    }

    public String getChildLNAME() {
        return ChildLNAME;
    }

}
