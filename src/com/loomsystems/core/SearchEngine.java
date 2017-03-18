package com.loomsystems.core;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {

    private static final int WORD_START_NUMBER = 2;
    private static final int WORD_CHANGED = 2;
    private static final String WORD_SEPARATOR = " ";
    private List<String> inputList = new ArrayList<>();
    private List<String> resList = new ArrayList<>();
    private Dictionary dictionary = new Dictionary();

    public SearchEngine(List<String> inputList) {
        this.inputList = inputList;
    }

    /**
     * Search engine:
     * 1. Split sentence on words and calculating the amount of the sentence.
     * 2. Calculating the difference in the sums of sentences and filtering sentences.
     */
    public ArrayList<String> startSearch() {
        //Split sentence on words and calculating the amount of the sentence.
        List<Integer> codes = new ArrayList<>();
        for (String line : inputList) {
            String[] words = line.split(WORD_SEPARATOR);
            int sumOr = 0;
            for (int i = WORD_START_NUMBER; i < words.length; i++) {
                sumOr |= dictionary.getCode(words[i]);
            }
            codes.add(sumOr);
        }

        //Calculating the difference in the sums of sentences and filtering sentences.
        for (int i = 0; i < codes.size(); i++) {
            for (int j = i + 1; j < codes.size(); j++) {
                int xor = codes.get(i) ^ codes.get(j);
                if (bitCounter(xor) == WORD_CHANGED) {
                    resList.add(inputList.get(i));
                    resList.add(inputList.get(j));
                    resList.add("The changing word was: " + getChangWords(xor));
                    resList.add(" ");
                }
            }
        }
        return (ArrayList<String>) resList;
    }

    /**
     * Counting bits in a number.
     *
     * @param n - the search number.
     * @return counted bits.
     */
    private int bitCounter(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n = n >>> 1;
        }
        return count;
    }

    /**
     * Searching for words that have been changed:
     * 1. Calculation of word codes.
     * 2. Search for words in the dictionary.
     *
     * @param x - the number contains the search words.
     * @return - string with words found.
     */
    private String getChangWords(int x) {
        //Calculation of word codes.
        int a = x & -x;
        int b = x - a;
        //Search for words in the dictionary and return.
        return dictionary.getWord(a) + ", " + dictionary.getWord(b);
    }

}
