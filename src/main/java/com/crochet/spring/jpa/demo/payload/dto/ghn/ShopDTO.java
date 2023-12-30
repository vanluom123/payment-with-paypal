package com.crochet.spring.jpa.demo.payload.dto.ghn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShopDTO {
    @SerializedName("_id")
    @Expose
    public long id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("ward_code")
    @Expose
    public String wardCode;
    @SerializedName("district_id")
    @Expose
    public long districtId;
    @SerializedName("client_id")
    @Expose
    public long clientId;
    @SerializedName("bank_account_id")
    @Expose
    public long bankAccountId;
    @SerializedName("status")
    @Expose
    public long status;
    @SerializedName("location")
    @Expose
    @Valid
    public LocationDTO location;
    @SerializedName("version_no")
    @Expose
    public String versionNo;
    @SerializedName("is_created_chat_channel")
    @Expose
    public boolean isCreatedChatChannel;
    @SerializedName("updated_ip")
    @Expose
    public String updatedIp;
    @SerializedName("updated_employee")
    @Expose
    public long updatedEmployee;
    @SerializedName("updated_client")
    @Expose
    public long updatedClient;
    @SerializedName("updated_source")
    @Expose
    public String updatedSource;
    @SerializedName("updated_date")
    @Expose
    public String updatedDate;
    @SerializedName("created_ip")
    @Expose
    public String createdIp;
    @SerializedName("created_employee")
    @Expose
    public long createdEmployee;
    @SerializedName("created_client")
    @Expose
    public long createdClient;
    @SerializedName("created_source")
    @Expose
    public String createdSource;
    @SerializedName("created_date")
    @Expose
    public String createdDate;

}
