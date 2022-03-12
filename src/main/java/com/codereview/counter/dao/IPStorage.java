package com.codereview.counter.dao;

public interface IPStorage {
    void addIPIfNotExists(int ipHash);

    long getIPCount();
}
