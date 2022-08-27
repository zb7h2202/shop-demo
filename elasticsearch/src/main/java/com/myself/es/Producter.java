package com.myself.es;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Producter {

    private String productName;
    private double price;
    private int number;
    public String geIndexId() {
        int id = 1;
        id += id;
        String indesId = String.valueOf(id);
        return indesId;
    }




}
