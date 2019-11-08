
package radinyazilim.com.mhfz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExpertModel {

    @SerializedName("expert_Id")
    @Expose
    public Integer expertId;
    @SerializedName("name_Surname")
    @Expose
    public String nameSurname;
    @SerializedName("deviceToken")
    @Expose
    public String deviceToken;
    public ExpertModel(String nameSurname,String deviceToken){
        //this.expertId=expertId;
        this.nameSurname=nameSurname;
        this.deviceToken =deviceToken;
    }

}