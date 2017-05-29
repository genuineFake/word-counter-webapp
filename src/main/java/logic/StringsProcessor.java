package logic;

import java.util.List;
import java.util.Map;

/**
 * Created by fnord on 17.5.28.
 */
public class StringsProcessor {


    public Map<String, Integer> process(List<String> uploadedStrings) {
        WordCounter wordCounter = new WordCounter();
        wordCounter.countWordFrequency(uploadedStrings.toArray(new String[uploadedStrings.size()]));
        return wordCounter.getWordMap();
    }


}
