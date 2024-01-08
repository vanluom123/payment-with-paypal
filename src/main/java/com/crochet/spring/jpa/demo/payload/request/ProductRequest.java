package com.crochet.spring.jpa.demo.payload.request;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProductRequest {
    @SerializedName("product_id")
    private UUID productId;
    @SerializedName("category_id")
    private UUID categoryId;
    @SerializedName("shop_id")
    private UUID shopId;
    private String name;
    private double price;
    private String description;
    private int height;
    private int width;
    private int length;
    private int weight;
}
