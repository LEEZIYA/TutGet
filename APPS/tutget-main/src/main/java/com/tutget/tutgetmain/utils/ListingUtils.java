package com.tutget.tutgetmain.utils;

public class ListingUtils {

    private double GSTRate = 0.08;

    public double calGST(int hourlyRate){
        if(hourlyRate <= 0){
           throw new ArithmeticException();
        } else {
            return GSTRate * hourlyRate;
        }

    }

}
