package com.codereview.counter.dao;

import com.codereview.counter.exception.FileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class NioFileStorage implements FileStorage {

    private final String filePath;

    public NioFileStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Stream<String> getLines() {
        try {
            return Files.lines(Paths.get(filePath));
        } catch (IOException e) {
            throw new FileException("Fails to read a file: " + e.getMessage());
        }
    }

}
