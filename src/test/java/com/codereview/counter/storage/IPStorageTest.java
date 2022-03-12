package com.codereview.counter.storage;

import com.codereview.counter.dao.BitSetIPStorage;
import com.codereview.counter.dao.IPStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IPStorageTest {
    private final IPStorage ipStorage = BitSetIPStorage.getInstance();

    @AfterEach
    public void clearBitSetStorage() {
        ipStorage.clear();
    }

    @Test
    @DisplayName("Проверка добавления в BitSet отрицательных и положительных значений с повтором")
    public void addIPIfNotExistsAndGetIPCountTest() {
        ipStorage.addIPIfNotExists(1665461519);
        ipStorage.addIPIfNotExists(-595934046);
        ipStorage.addIPIfNotExists(1665461519);
        ipStorage.addIPIfNotExists(-595934046);

        Assertions.assertEquals(2, ipStorage.getIPCount());
    }

    @Test
    public void addIPIfMaxValueTest() {
        ipStorage.addIPIfNotExists(Integer.MAX_VALUE);
        Assertions.assertEquals(1, ipStorage.getIPCount());
    }
}
