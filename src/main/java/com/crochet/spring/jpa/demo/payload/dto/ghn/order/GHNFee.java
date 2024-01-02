package com.crochet.spring.jpa.demo.payload.dto.ghn.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class GHNFee {
    @SerializedName("coupon")
    @Expose
    public long coupon;
    @SerializedName("insurance")
    @Expose
    public long insurance;
    @SerializedName("main_service")
    @Expose
    public long mainService;
    @SerializedName("r2s")
    @Expose
    public long r2s;
    @SerializedName("return")
    @Expose
    public long _return;
    @SerializedName("station_do")
    @Expose
    public long stationDo;
    @SerializedName("station_pu")
    @Expose
    public long stationPu;
}
