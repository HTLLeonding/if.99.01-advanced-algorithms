package com.bajupa.exhaustive;

import java.util.*;

/**
 * Copyright 2016 by Bajupa.com
 * Created by peter on 21/03/16.
 */
public class ChangeCalculator {

    HashSet<Integer> availableCoins;

    private ChangeCalculator(List<Integer> availableCoins) {
        this.availableCoins = new HashSet<>(availableCoins);
    }

    public static ChangeCalculator havingTheFollowingCoins(Integer... availableCoins) {
        return new ChangeCalculator(Arrays.asList(availableCoins));
    }

    public List<Integer> getChangeList(int amountToChange) {
        return updateChangeList(amountToChange);
    }

    public List<Integer> updateChangeList(int amountToChange) {
        if (availableCoins.contains(amountToChange))
            return Collections.singletonList(amountToChange);

        List<Integer> minimalSetOfCoins = Collections.nCopies(amountToChange, 1);

        for (int currentCoin : availableCoins) {
            if (amountToChange - currentCoin > 0) {
                List<Integer> candidate = new ArrayList<>(updateChangeList(amountToChange - currentCoin));
                candidate.add(currentCoin);
                if (candidate.size() < minimalSetOfCoins.size()) {
                    minimalSetOfCoins = candidate;
                }
            }
        }
        return minimalSetOfCoins;
    }
}
