package lambdasinaction.chap7;

import java.util.stream.Stream;

public class WordCountStream {

    public static int countWordsStream(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(),
                                                WordCounter::accumulate,
                                                WordCounter::combine);

        System.out.println(wordCounter);

        return wordCounter.getCounter();
    }
}