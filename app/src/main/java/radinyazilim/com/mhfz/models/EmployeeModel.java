package radinyazilim.com.mhfz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployeeModel {

    @SerializedName("employee_Id")
    @Expose
    public String employeeId;
    @SerializedName("name_Surname")
    @Expose
    public String nameSurname;
    @SerializedName("santiye_Id")
    @Expose
    public Integer santiyeId;
    @SerializedName("puan")
    @Expose
    public Integer puan;
    @SerializedName("takilmayan_Sure")
    @Expose
    public Float takilmayanSure;
    @SerializedName("alert")
    @Expose
    public Integer alert;
    public EmployeeModel(String employeeId,String nameSurname,int santiyeId,int puan,float takilmayanSure,int alert){
        this.employeeId = employeeId;
        this.nameSurname = nameSurname;
        this.santiyeId = santiyeId;
        this.puan = puan;
        this.takilmayanSure = takilmayanSure;
        this.alert = alert;
    }
}
