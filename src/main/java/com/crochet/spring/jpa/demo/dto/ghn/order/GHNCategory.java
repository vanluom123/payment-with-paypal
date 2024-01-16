package com.crochet.spring.jpa.demo.dto.ghn.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GHNCategory {
    @SerializedName("level1")
    @Expose
    public String level1;
}
