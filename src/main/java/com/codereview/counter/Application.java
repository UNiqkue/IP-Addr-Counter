package com.codereview.counter;

import com.codereview.counter.dao.BitSetIPStorage;
import com.codereview.counter.dao.NioFileStorage;
import com.codereview.counter.exception.FileException;
import com.codereview.counter.parser.IPParser;
import com.codereview.counter.service.CounterService;
import com.codereview.counter.service.IPCounterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Objects;

public class Application {

    private static final Logger log = LogManager.getLogger(Application.class);

    private final static String FILE_COMMAND = "-file";

    public static void main(String[] args) {
        String filePath = getFilePath(args);

        CounterService ipCounterService = new IPCounterService(
                new NioFileStorage(filePath),
                BitSetIPStorage.getInstance(),
                new IPParser()
        );

        log.info("Started at " + OffsetDateTime.now());
        Instant startTime = Instant.now();

        log.info("Unique IP count: " + ipCounterService.getUniqueIPCount());

        Instant endTime = Instant.now();
        log.info("Finished at " + OffsetDateTime.now());

        log.info("All duration time in Millis: " + Duration.between(startTime, endTime).toMillis());
    }

    private static String getFilePath(String[] args) {
        String filePath;
        if (FILE_COMMAND.equals(args[0]) && Objects.nonNull(args[1])) {
            filePath = args[1];
        } else {
            throw new FileException("The path to the file was not passed. Write, for example, like this: java -jar target/ip-addr-counter-1.0.0.jar -file ip_addresses");
        }
        return filePath;
    }
}
