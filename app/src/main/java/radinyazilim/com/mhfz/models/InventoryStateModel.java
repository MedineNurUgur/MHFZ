package radinyazilim.com.mhfz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class InventoryStateModel {
    @SerializedName("Inventory_Id")
    @Expose
    public String inventoryStateId;
    @SerializedName("State")
    @Expose
    public Integer inventory_state;
    @SerializedName("Time")
    @Expose
    public Timestamp inventoryStateTime;
}
