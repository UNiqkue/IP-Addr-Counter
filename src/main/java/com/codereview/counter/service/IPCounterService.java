package com.codereview.counter.service;

import com.codereview.counter.dao.NioFileStorage;
import com.codereview.counter.utils.IPParser;
import com.codereview.counter.dao.BitSetIPStorage;

public class IPCounterService implements CounterService {

    private final NioFileStorage fileStorage;
    private final BitSetIPStorage ipStorage;
    private final IPParser ipParser;

    public IPCounterService(NioFileStorage fileStorage, BitSetIPStorage ipStorage, IPParser ipParser) {
        this.fileStorage = fileStorage;
        this.ipStorage = ipStorage;
        this.ipParser = ipParser;
    }

    @Override
    public long getUniqueIPCount() {
//        TreeSet inetAddrs = new TreeSet();
//        BitSet bitSet = new BitSet();
        fileStorage.getLines()
                .forEach(line -> {
                    int ipHash = ipParser.parseToInt(line);
                    ipStorage.addIPIfNotExists(ipHash);
                });
        return ipStorage.getIPCount();
    }
}
