package com.crochet.spring.jpa.demo.payload.dto.ghtk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GHTKProduct {
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("weight")
    @Expose
    public double weight;
    @SerializedName("quantity")
    @Expose
    public long quantity;
    @SerializedName("product_code")
    @Expose
    public long productCode;
}
