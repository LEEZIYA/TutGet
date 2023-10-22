package com.tutget.tutgetmain.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListingUtilsTest {


    ListingUtils listingUtils;

    @BeforeEach
    void init(){
        listingUtils = new ListingUtils();
    }


    @Test
    @DisplayName("Count GST Test")
    void calGSTTest() {
        assertEquals(0.08, listingUtils.calGST(1), "The calculated GST is wrong");
    }


    @Test
    @DisplayName("Negative GST Test")
    void calGSTNegativeTest(){
        assertThrows(ArithmeticException.class, () -> listingUtils.calGST(-1), "Negative result should throw");
    }

    @Test
    @Disabled
    @DisplayName("TDD method. Should not run")
    void testDisabled(){
        fail("Test driven dev in progress");
    }


}