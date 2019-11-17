package radinyazilim.com.mhfz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ControlModel {
    @SerializedName("No")
    @Expose
    public Integer no;
    @SerializedName("Image")
    @Expose
    public byte[] image;
    @SerializedName("Description")
    @Expose
    public String description;
    public ControlModel(byte[] image,String description) {
        //this.expertId=expertId;
        this.image = image;
        this.image = image;
    }
}
