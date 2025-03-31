package com.example.analysis;

import java.util.HashMap;
import java.util.Map;

public class WordAnalyzer implements Analyzer {

    @Override
    public Map<String, Integer> analyze(String data) {
        Map<String, Integer> map = new HashMap<>();

        for (String word : data.split("[\\s.,?!]")) {
            int i = 0;
            if(map.get(word) != null) {
                i = map.get(word);
            }
            map.put(word, i + 1);
        }

        if(map.containsKey("")) {
            map.remove("");
        }

        return map;
    }
    
}