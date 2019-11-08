package radinyazilim.com.mhfz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackModel {
    @SerializedName("no")
    @Expose
    public Integer no;
    @SerializedName("feedback")
    @Expose
    public String feedback;

    public FeedbackModel(String feedbackMesage) {
        this.feedback = feedbackMesage;
        //this.no=no;
    }

}
