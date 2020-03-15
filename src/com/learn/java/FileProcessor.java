package com.learn.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

interface BufferReaderProcessor {
    String process(BufferedReader br) throws IOException;
}

public class FileProcessor {

    public static void main(String[] args) throws IOException {
        String oneLine = processFile((BufferedReader br) -> br.readLine());
        System.out.println(oneLine);
        String twoLine = processFile((BufferedReader br) -> br.readLine() + "\n" + br.readLine());
        System.out.println(twoLine);
    }

    public static String processFile(BufferReaderProcessor processor) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            return processor.process(reader);
        }
    }

}
