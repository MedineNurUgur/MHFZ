package radinyazilim.com.mhfz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import radinyazilim.com.mhfz.Activities.LoginActivity;

public class LoginModel {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("surname")
    @Expose
    public String surname;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("companyId")
    @Expose
    public Integer companyId;
    @SerializedName("address")
    @Expose
    public String address;


}