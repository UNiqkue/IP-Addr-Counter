package com.codereview.counter;

import com.codereview.counter.dao.BitSetIPStorage;
import com.codereview.counter.dao.NioFileStorage;
import com.codereview.counter.exception.FileException;
import com.codereview.counter.service.CounterService;
import com.codereview.counter.service.IPCounterService;
import com.codereview.counter.parser.IPParser;

import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Objects;

public class Application {

    public static void main(String[] args) {
        String filePath = getFilePath(args);
        CounterService ipCounterService = new IPCounterService(
                new NioFileStorage(filePath),
                BitSetIPStorage.getInstance(),
                new IPParser()
        );
        System.out.println("Started " + OffsetDateTime.now());
        Instant start = Instant.now();
        System.out.println("Unique IP count: " + ipCounterService.getUniqueIPCount());
        Instant end = Instant.now();
        System.out.println("Finished " + OffsetDateTime.now());
        System.out.println("Millis: " + Duration.between(start, end).toMillis());
    }

    private static String getFilePath(String[] args) {
        String filePath;
        if ("-file".equals(args[0]) && Objects.nonNull(args[1])) {
            filePath = args[1];
        } else
            throw new FileException("The path to the file was not passed. Write, for example, like this: java -jar target/ip-addr-counter-1.0.0.jar -file ip_addresses");
        return filePath;
    }
}