package com.example.logs.forwarding.utils;

import java.nio.file.Files;

import org.springframework.core.io.ClassPathResource;

import lombok.SneakyThrows;

public final class Utils {

    private Utils() {

    }

    @SneakyThrows
    public static String classpathFileToString(String path) {
        return Files.readString(new ClassPathResource(path).getFile()
                .toPath());
    }
}
