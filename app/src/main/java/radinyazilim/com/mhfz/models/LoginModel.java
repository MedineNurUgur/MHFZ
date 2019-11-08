package radinyazilim.com.mhfz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import radinyazilim.com.mhfz.Activities.LoginActivity;

public class LoginModel {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("password")
    @Expose
    public String password;


}