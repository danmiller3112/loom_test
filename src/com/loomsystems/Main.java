package com.loomsystems;

import com.loomsystems.core.IOFile;
import com.loomsystems.core.SearchEngine;

import java.util.ArrayList;

public class Main {

    private static final String INPUT_FILE = "src/input_file.txt";
    private static final String OUTPUT_FILE = "src/output_file.txt";

    public static void main(String[] args) {
        System.out.println("Start...");
        IOFile ioFile = new IOFile(INPUT_FILE, OUTPUT_FILE);
        SearchEngine searchEngine = new SearchEngine(ioFile.readFromFile());

        System.out.println("Searching...");
        ArrayList<String> resList = searchEngine.startSearch();

        System.out.println("Save to file...");
        ioFile.saveToFile(resList);

        System.out.println("The result is saved. Look to the file on path: " + OUTPUT_FILE);
    }
}
