package com.crochet.spring.jpa.demo.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class AccessTokenDTO {
    private String scope;

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("app_id")
    private String appId;

    @SerializedName("expires_in")
    private int expiresIn;

    private String nonce;
}
