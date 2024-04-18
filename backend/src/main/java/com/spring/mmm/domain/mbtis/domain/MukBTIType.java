package com.spring.mmm.domain.mbtis.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MukBTIType {
    EI("EI"), NS("NS"), TF("TF"), JP("JP"), MINT("Mint"), PINE("Pine"), DIE("Die");


    private final String temp;

    public String getTemp(){
        return temp;
    }
}
