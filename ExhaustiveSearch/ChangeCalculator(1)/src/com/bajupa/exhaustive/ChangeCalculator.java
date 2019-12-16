package com.bajupa.exhaustive;

import java.util.*;

/**
 * Copyright 2016 by Bajupa.com
 * Created by peter on 21/03/16.
 */
public class ChangeCalculator {

    private HashSet<Integer> availableCoins;
    private List<Integer> callsWithParameters;

    private ChangeCalculator(List<Integer> availableCoins) {
        this.availableCoins = new HashSet<>(availableCoins);
        callsWithParameters = new ArrayList<>();
    }

    public static ChangeCalculator havingTheFollowingCoins(Integer... availableCoins) {
        return new ChangeCalculator(Arrays.asList(availableCoins));
    }

    public List<Integer> getChangeList(int amountToChange) {
        callsWithParameters.add(amountToChange);
        if (availableCoins.contains(amountToChange))
            return Collections.singletonList(amountToChange);

        List<Integer> minimalSetOfCoins = Collections.nCopies(amountToChange, 1);

        for (int currentCoin : availableCoins) {
            if (amountToChange - currentCoin > 0) {
                List<Integer> candidate = new ArrayList<>(getChangeList(amountToChange - currentCoin));
                candidate.add(currentCoin);
                if (candidate.size() < minimalSetOfCoins.size()) {
                    minimalSetOfCoins = candidate;
                }
            }
        }
        return minimalSetOfCoins;


    }

    public int getNumberOfCalls() {
        return callsWithParameters.size();
    }

    public List<Integer> getCallList() {
        return callsWithParameters;
    }
}
