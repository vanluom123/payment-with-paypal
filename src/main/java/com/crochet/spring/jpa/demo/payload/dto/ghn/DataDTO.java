package com.crochet.spring.jpa.demo.payload.dto.ghn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DataDTO {
    @SerializedName("last_offset")
    @Expose
    public long lastOffset;
    @SerializedName("shops")
    @Expose
    @Valid
    public List<ShopDTO> shops;

}
