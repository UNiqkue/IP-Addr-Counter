package com.codereview.counter.dao;

import java.util.stream.Stream;

public interface FileStorage {
    Stream<String> getLines();
}
