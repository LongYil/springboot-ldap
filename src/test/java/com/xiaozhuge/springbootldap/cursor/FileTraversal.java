package com.xiaozhuge.springbootldap.cursor;

import java.io.File;

public class FileTraversal {
    public static void main(String[] args) {
        String path = "/Users/liyinlong/jiaofu";
        traverseDirectory(new File(path));
    }

    public static void traverseDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        traverseDirectory(file);
                    } else {
                        System.out.println(file.getName());
                    }
                }
            }
        }
    }
}