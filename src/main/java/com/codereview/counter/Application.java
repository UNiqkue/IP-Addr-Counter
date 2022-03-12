package com.codereview.counter;

import com.codereview.counter.dao.NioFileStorage;
import com.codereview.counter.dao.BitSetIPStorage;
import com.codereview.counter.exception.FileException;
import com.codereview.counter.service.CounterService;
import com.codereview.counter.service.IPCounterService;
import com.codereview.counter.utils.IPParser;

import java.time.OffsetDateTime;
import java.util.BitSet;
import java.util.Objects;

public class Application {

    public static void main(String[] args) {
        String fileName = getFileName(args);
        CounterService ipService = new IPCounterService(
                new NioFileStorage(fileName),
                new BitSetIPStorage(new BitSet(Integer.MAX_VALUE), new BitSet(Integer.MAX_VALUE)),
                new IPParser()
        );
        System.out.println("Начало " + OffsetDateTime.now());
        System.out.println("Уникальное кол-во ip адресов: " + ipService.getUniqueIPCount());
        System.out.println("закончилось " + OffsetDateTime.now());
    }

    private static String getFileName(String[] args) {
        String fileName;
        if ("-file".equals(args[0]) && Objects.nonNull(args[1])) {
            fileName = args[1];
        } else
            throw new FileException("Путь к файлу не передан. Пропишите, например, так java -jar target/ip-addr-counter-1.0.0.jar -file ip_addresses");
        return fileName;
    }
}

