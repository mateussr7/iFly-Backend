package com.ifly.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OrigDestRota {
    private String aeroportoOrig;
    private String aeroportoDest;


    public OrigDestRota(String aeroportoOrig, String aeroportoDest) {
        this.aeroportoOrig = aeroportoOrig;
        this.aeroportoDest = aeroportoDest;
    }
}
