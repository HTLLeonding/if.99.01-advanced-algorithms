package com.bajupa.greedy;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * Copyright 2016 by Bajupa.com
 * Created by peter on 21/03/16.
 */
public class ChangeCalculatorTest {
    private ChangeCalculator changeCalculator;

    @Before
    public void setUp() {
        changeCalculator = ChangeCalculator.HavingTheFollowingCoins(1, 2);
    }

    @Test
    public void testConstruction() {
        assertEquals(0, changeCalculator.getChangeList(0).size());
    }

    @Test
    public void testFirstFittingCoin() {
        assertEquals(Collections.singletonList(1), changeCalculator.getChangeList(1));
    }

    @Test
    public void testSecondFittingCoin() {
        assertEquals(Collections.singletonList(2), changeCalculator.getChangeList((2)));
    }

    @Test
    public void testTwoFittingCoins() {
        assertEquals(Arrays.asList(2, 2), changeCalculator.getChangeList(4));
    }

    @Test
    public void testTwoDifferentFittingCoins() {
        assertEquals(Arrays.asList(2, 1), changeCalculator.getChangeList(3));
    }
}