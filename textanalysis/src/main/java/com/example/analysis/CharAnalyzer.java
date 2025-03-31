package com.example.analysis;

import java.util.HashMap;
import java.util.Map;

public class CharAnalyzer implements Analyzer {

    @Override
    public Map<String, Integer> analyze(String data) {
        Map<String, Integer> map = new HashMap<>();

        for (char c : data.toCharArray()) {
            String tmp = String.valueOf(Character.toLowerCase(c));
            int i = 0;
            if(map.get(tmp) != null) {
                i = map.get(tmp);
            }
            map.put(tmp, i + 1);
        }

        String[] remove = {" ", ".", ","};
        for (String c : remove) {
            if(map.containsKey(c)) {
                map.remove(c);
            }
        }

        return map;
    }
    
}
