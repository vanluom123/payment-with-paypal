package com.crochet.spring.jpa.demo.payload.dto.ghn.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class GHNItem {
    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("code")
    @Expose
    public String code;

    @SerializedName("quantity")
    @Expose
    public long quantity;

    @SerializedName("price")
    @Expose
    public long price;

    @SerializedName("length")
    @Expose
    public long length = 12;

    @SerializedName("weight")
    @Expose
    public long weight = 1200;

    @SerializedName("width")
    @Expose
    public long width;

    @SerializedName("height")
    @Expose
    public long height;

    @SerializedName("category")
    @Expose
    public GHNCategory category;
}
