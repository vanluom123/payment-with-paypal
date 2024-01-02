package com.crochet.spring.jpa.demo.payload.dto.ghn.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class GHNCategory {
    @SerializedName("level1")
    @Expose
    public String level1;
}
