package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Counts the frequency of words.
 */
public class WordCounter {
    private Map<String, Integer> wordMap;

    public WordCounter() {
        wordMap = new HashMap<>();
    }


    public void countWordFrequency(String[] fileNames) {
        Map<String, Integer> tempMap = new HashMap<>();
        List<MapBuilder> threadlist = new ArrayList<>();
        List<Map<String, Integer>> wordCounts = new ArrayList<>();

        for (int i = 0; i < fileNames.length; i++) {
            MapBuilder mb = new MapBuilder(fileNames[i]);
            threadlist.add(mb);
            mb.start();
        }

        for (MapBuilder mb : threadlist) {
            try {
                mb.join();
                wordCounts.add(mb.getWordMap());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        for (Map<String, Integer> wcount : wordCounts) {
            for (Map.Entry<String, Integer> entry : wcount.entrySet()) {
                tempMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }

        for (Map.Entry<String, Integer> e : tempMap.entrySet()) {
            if (e.getValue() > 1) {
                wordMap.put(e.getKey(), e.getValue());
            }
        }

    }

    public Map<String, Integer> getWordMap() {
        return wordMap;
    }
}
