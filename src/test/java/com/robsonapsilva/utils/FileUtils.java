package com.robsonapsilva.utils;

import java.io.File;
import java.net.URL;

public class FileUtils {

    public static File getFile(String path) {
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource(path);
            return new File(url.getFile());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}