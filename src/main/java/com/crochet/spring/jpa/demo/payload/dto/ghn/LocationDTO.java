package com.crochet.spring.jpa.demo.payload.dto.ghn;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationDTO {
    @SerializedName("lat")
    @Expose
    public double lat;
    @SerializedName("long")
    @Expose
    public double _long;
}