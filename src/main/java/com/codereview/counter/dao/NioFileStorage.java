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
//    private final BufferedReader reader;
//
//    public FileStorage(BufferedReader reader) {
//        this.reader = reader;
//    }

//    public String readLineIn() {
//        String line = null;
//        try {
//            line = reader.readLine();
//        } catch (IOException e) {
////
//            e.printStackTrace();
//        }
//        return line;
//    }

    @Override
    public Stream<String> getLines() {
        try {
            return Files.lines(Paths.get(filePath));
        } catch (IOException e) {
            throw new FileException("Ошибка чтения файла: " + e.getMessage());
        }
    }

}
