package com.kwiatkowski.WymianaWalut.exceptions.SideFund;

public class SideFundException extends RuntimeException{

    private SideFundError sideFundError;

    public SideFundException(SideFundError sideFundError){
        this.sideFundError = sideFundError;
    }

    public SideFundError getSideFundError() {
        return sideFundError;
    }
}
