package com.bajupa.exhaustive;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Copyright 2016 by Bajupa.com
 * Created by peter on 21/03/16.
 */
public class ChangeCalculatorTest {

    @Test
    public void testChangeMatchesExactlyOneCoin() {
        ChangeCalculator cc = ChangeCalculator.havingTheFollowingCoins(1, 2, 10, 21, 25);
        assertEquals(Collections.singletonList(10), cc.getChangeList(10));
    }

    @Test
    public void testnCopy() {
        ChangeCalculator cc = ChangeCalculator.havingTheFollowingCoins(1, 10, 21, 25);
        assertEquals(Arrays.asList(1, 1, 1), cc.getChangeList(3));
    }

    @Test
    public void testConstruction() {
        ChangeCalculator cc = ChangeCalculator.havingTheFollowingCoins(1, 5, 10, 21, 25);
        assertEquals(Arrays.asList(25, 1), cc.getChangeList(26));
    }
}