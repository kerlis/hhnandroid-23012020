package me.doapps.appdhn.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Leandro on 10/30/17.
 */

public class Token {

    @SerializedName("DEVICEID")
    private String DEVICEID;

    @SerializedName("DEVICETOKEN")
    private String DEVICETOKEN;

    @SerializedName("DEVICETYPE")
    private String DEVICETYPE;

    public Token(String DEVICEID, String DEVICETOKEN, String DEVICETYPE) {
        this.DEVICEID = DEVICEID;
        this.DEVICETOKEN = DEVICETOKEN;
        this.DEVICETYPE = DEVICETYPE;
    }

    public String getDEVICEID() {
        return DEVICEID;
    }

    public void setDEVICEID(String DEVICEID) {
        this.DEVICEID = DEVICEID;
    }

    public String getDEVICETOKEN() {
        return DEVICETOKEN;
    }

    public void setDEVICETOKEN(String DEVICETOKEN) {
        this.DEVICETOKEN = DEVICETOKEN;
    }

    public String getDEVICETYPE() {
        return DEVICETYPE;
    }

    public void setDEVICETYPE(String DEVICETYPE) {
        this.DEVICETYPE = DEVICETYPE;
    }
}