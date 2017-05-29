package logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Writes words and their frequency to files.
 */
public class FrequencyWriter {
    private Map<String, Integer> allWordsMap;
    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;

    public FrequencyWriter(Map<String, Integer> allWordsMap) {
        this.allWordsMap = allWordsMap;
    }

    /**
     * Takes words and frequency count from the map and sorts them to StringBuilder objects.
     *
     * @return an array of stringbuilders
     */
    private StringBuilder[] sortWords() {
        StringBuilder stringBuilderAG = new StringBuilder();
        StringBuilder stringBuilderHN = new StringBuilder();
        StringBuilder stringBuilderOU = new StringBuilder();
        StringBuilder stringBuilderVZ = new StringBuilder();

        for (Map.Entry<String, Integer> e : allWordsMap.entrySet()) {
            if (e.getKey().matches("^[a-g].*$")) {
                stringBuilderAG.append(e.getKey() + "\t" + e.getValue() + "\n");
            }
            if (e.getKey().matches("^[h-n].*$")) {
                stringBuilderHN.append(e.getKey() + "\t" + e.getValue() + "\n");
            }
            if (e.getKey().matches("^[o-u].*$")) {
                stringBuilderOU.append(e.getKey() + "\t" + e.getValue() + "\n");
            }
            if (e.getKey().matches("^[v-z].*$")) {
                stringBuilderVZ.append(e.getKey() + "\t" + e.getValue() + "\n");
            }
        }
        StringBuilder[] stringBuilders = {stringBuilderAG, stringBuilderHN, stringBuilderOU, stringBuilderVZ};
        return stringBuilders;
    }

    public void writeWordsToFiles() {
        StringBuilder[] stringBuilders = sortWords();

        for (int i = 0; i < stringBuilders.length; i++) {
            writeToFiles(stringBuilders[i], i);
        }

    }

    /**
     * Writes strings to files.
     *
     * @param stringBuilder object to write to file
     * @param i             the value which indicates the file
     */
    private void writeToFiles(StringBuilder stringBuilder, int i) {
        String fileName = "";
        if (i == 0) {
            fileName = "A-G.txt";
        } else if (i == 1) {
            fileName = "H-N.txt";
        } else if (i == 2) {
            fileName = "O-U.txt";
        } else {
            fileName = "V-Z.txt";
        }


        try {
            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append(stringBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
                if (fileWriter != null)
                    fileWriter.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
