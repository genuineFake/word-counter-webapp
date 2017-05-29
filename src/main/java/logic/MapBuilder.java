package logic;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Goes through string of words and puts them in the HashMap.
 */
public class MapBuilder extends Thread {
    private String fileString;
    private Map<String, Integer> wordMap;


    public MapBuilder(String fileString) {
        this.fileString = fileString;
        wordMap = new HashMap<>();
    }

    @Override
    public void run() {
        buildWordMap(fileString);
    }

    private void buildWordMap(String fileString) {

        Pattern pattern = Pattern.compile("\\W+");
        String[] words = pattern.split(fileString.toLowerCase());
        for (String word : words) {
            if (word == null || word.trim().equals("")) {
                continue;
            }
            if (wordMap.containsKey(word)) {
                wordMap.put(word, (wordMap.get(word) + 1));
            } else {
                wordMap.put(word, 1);
            }
        }

    }

    public Map<String, Integer> getWordMap() {
        return wordMap;
    }

    public String getFileString() {
        return fileString;
    }

    public void setFileString(String fileString) {
        this.fileString = fileString;
    }

}
