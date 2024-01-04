package com.crochet.spring.jpa.demo.payload.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductRequest {
    @SerializedName("category_id")
    private UUID categoryId;
    private String name;
    private double price;
    private String description;
    private int height;
    private int width;
    private int length;
    private int weight;
}
