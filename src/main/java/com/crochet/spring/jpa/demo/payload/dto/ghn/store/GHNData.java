package com.crochet.spring.jpa.demo.payload.dto.ghn.store;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GHNData {
    @SerializedName("last_offset")
    @Expose
    public long lastOffset;
    @SerializedName("shops")
    @Expose
    @Valid
    public List<GHNShop> shops;
}
