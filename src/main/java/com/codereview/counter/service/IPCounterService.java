package com.codereview.counter.service;

import com.codereview.counter.dao.FileStorage;
import com.codereview.counter.dao.IPStorage;
import com.codereview.counter.parser.IPParser;

public class IPCounterService implements CounterService {

    private final FileStorage fileStorage;
    private final IPStorage ipStorage;
    private final IPParser ipParser;

    public IPCounterService(FileStorage fileStorage, IPStorage ipStorage, IPParser ipParser) {
        this.fileStorage = fileStorage;
        this.ipStorage = ipStorage;
        this.ipParser = ipParser;
    }

    @Override
    public long getUniqueIPCount() {
        fileStorage.getLines()
                .forEach(line -> {
                    int ipHash = ipParser.parseToInt(line);
                    ipStorage.addIPIfNotExists(ipHash);
                });
        return ipStorage.getIPCount();
    }
}
