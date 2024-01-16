package com.crochet.spring.jpa.demo.dto.ghn.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GHNItem {
    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("code")
    @Expose
    public String code;

    @SerializedName("quantity")
    @Expose
    public int quantity;

    @SerializedName("price")
    @Expose
    public int price;

    @SerializedName("length")
    @Expose
    public int length;

    @SerializedName("width")
    @Expose
    public int width;

    @SerializedName("height")
    @Expose
    public int height;

    @SerializedName("weight")
    @Expose
    public int weight;

    @SerializedName("category")
    @Expose
    public GHNCategory category;
}
