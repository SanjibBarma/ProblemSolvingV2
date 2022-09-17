package ltd.test.problemsolving.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Option implements Serializable {
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("referTo")
    @Expose
    private String referTo;

    public Option(String set, int i) {
    }



    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getReferTo() {
        return referTo;
    }

    public void setReferTo(String referTo) {
        this.referTo = referTo;
    }

    @Override
    public String toString() {
        return  value;
    }
}
