package com.codereview.counter.service;

import com.codereview.counter.dao.BitSetIPStorage;
import com.codereview.counter.dao.IPStorage;
import com.codereview.counter.dao.NioFileStorage;
import com.codereview.counter.exception.FileException;
import com.codereview.counter.parser.IPParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IPCounterServiceTest {

    private final IPStorage ipStorage = BitSetIPStorage.getInstance();

    @AfterEach
    public void clearBitSetStorage() {
        ipStorage.clear();
    }

    @Test
    @DisplayName("Проверка получения IP адресов из файла")
    public void getUniqueIPCountTest() {
        CounterService ipCounterService = new IPCounterService(
                new NioFileStorage("src\\test\\resources\\files\\10_ip_addresses"),
                BitSetIPStorage.getInstance(),
                new IPParser()
        );
        long uniqueIPCount = ipCounterService.getUniqueIPCount();
        Assertions.assertEquals(10, uniqueIPCount);
    }

    @Test
    @DisplayName("Проверка чтения IP адресов из пустого файла")
    public void getUniqueIPCountIfEmptyFileTest() {
        CounterService ipCounterService = new IPCounterService(
                new NioFileStorage("src\\test\\resources\\files\\empty_ip_address"),
                BitSetIPStorage.getInstance(),
                new IPParser()
        );
        long uniqueIPCount = ipCounterService.getUniqueIPCount();
        Assertions.assertEquals(0, uniqueIPCount);
    }

    @Test
    @DisplayName("Проверка проброса исключения из-за неверно переданного пути")
    public void getUniqueIPCountWrongPathToFileTest() {
        String wrongFilePath = "src\\test\\wrong_path";
        CounterService ipCounterService = new IPCounterService(
                new NioFileStorage(wrongFilePath),
                BitSetIPStorage.getInstance(),
                new IPParser()
        );
        Assertions.assertThrows(FileException.class,
                ipCounterService::getUniqueIPCount,
                "Fails to read a file:" + wrongFilePath);
    }
}
