package com.loomsystems.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RDL on 18/03/2017.
 */
public class Dictionary {

    private Map<Integer, String> dicCodeWord = new HashMap<>();
    private Map<String, Integer> dicWordCode = new HashMap<>();

    public Integer getCode(String word) {
        Integer res = dicWordCode.get(word);
        if (res == null) {
            addToDic(word, res = 1 << dicWordCode.size());
        }
        return res;
    }

    private void addToDic(String word, Integer code) {
        dicCodeWord.put(code, word);
        dicWordCode.put(word, code);
    }

    public String getWord(Integer code) {
        return dicCodeWord.get(code);
    }
}
