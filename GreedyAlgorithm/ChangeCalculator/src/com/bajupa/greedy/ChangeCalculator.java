package com.bajupa.greedy;

import java.util.*;

/**
 * Copyright 2016 by Bajupa.com
 * Created by peter on 21/03/16.
 */
public class ChangeCalculator {
    private List<Integer> availableCoins;

    public static ChangeCalculator HavingTheFollowingCoins(Integer... availableCoins) {
        return new ChangeCalculator(Arrays.asList(availableCoins));
    }

    private ChangeCalculator(List<Integer> listOfPossibleCoins) {
        this.availableCoins = listOfPossibleCoins;
        this.availableCoins.sort((a, b)-> b - a);
    }

    public List<Integer> getChangeList(int amountOfChange) {
        List<Integer> returnedCoins = new ArrayList<>();
        Iterator<Integer> i = availableCoins.iterator();

        int currentCoin = i.next();
        while (i.hasNext() && currentCoin > amountOfChange)
            currentCoin = i.next();
        while (amountOfChange > 0) {
            returnedCoins.add(currentCoin);
            amountOfChange -= currentCoin;
        }
        return returnedCoins;
    }

}
