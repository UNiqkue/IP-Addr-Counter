package com.codereview.counter.dao;

import java.util.BitSet;

public class BitSetIPStorage implements IPStorage {
    private final BitSet negativeNumbers;
    private final BitSet positiveNumbers;

    public BitSetIPStorage(BitSet negativeNumbers, BitSet positiveNumbers) {
        this.negativeNumbers = negativeNumbers;
        this.positiveNumbers = positiveNumbers;
    }

    @Override
    public void addIPIfNotExists(int ipHash) {
        if (ipHash >= 0) {
            if (!positiveNumbers.get(ipHash)) {
                positiveNumbers.set(ipHash);
            }
        } else {
            if (!negativeNumbers.get(-ipHash)) {
                negativeNumbers.set(-ipHash);
            }
        }

    }

    @Override
    public long getIPCount() {
        return negativeNumbers.cardinality() + positiveNumbers.cardinality();
    }
}
