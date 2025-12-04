package com.boda.secondhandtransaction.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String name;
    private String description;
    private String price;
    private Integer sellerId;
    private int id;
}
