package com.crochet.spring.jpa.demo.payload.dto.ghtk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GHTKOrder {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("pick_name")
    @Expose
    public String pickName;
    @SerializedName("pick_address")
    @Expose
    public String pickAddress;
    @SerializedName("pick_province")
    @Expose
    public String pickProvince;
    @SerializedName("pick_district")
    @Expose
    public String pickDistrict;
    @SerializedName("pick_ward")
    @Expose
    public String pickWard;
    @SerializedName("pick_tel")
    @Expose
    public String pickTel;
    @SerializedName("tel")
    @Expose
    public String tel;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("province")
    @Expose
    public String province;
    @SerializedName("district")
    @Expose
    public String district;
    @SerializedName("ward")
    @Expose
    public String ward;
    @SerializedName("hamlet")
    @Expose
    public String hamlet;
    @SerializedName("is_freeship")
    @Expose
    public String isFreeship;
    @SerializedName("pick_date")
    @Expose
    public String pickDate;
    @SerializedName("pick_money")
    @Expose
    public long pickMoney;
    @SerializedName("note")
    @Expose
    public String note;
    @SerializedName("value")
    @Expose
    public long value;
    @SerializedName("transport")
    @Expose
    public String transport;
    @SerializedName("pick_option")
    @Expose
    public String pickOption;
    @SerializedName("deliver_option")
    @Expose
    public String deliverOption;
    @SerializedName("pick_session")
    @Expose
    public long pickSession;
    @SerializedName("tags")
    @Expose
    @Valid
    public List<Long> tags;

}
