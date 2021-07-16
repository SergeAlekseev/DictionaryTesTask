package DictionaryFileView;

import DictionaryLib.DictionaryInFileWithRegex;

import java.io.BufferedReader;
import java.io.IOException;

public class DictionaryInFileWithRegexView extends DictionaryInFileView {

    public DictionaryInFileWithRegexView(DictionaryInFileWithRegex dictionary, BufferedReader br) throws IOException, InterruptedException {
        super(dictionary,br);
    }

    @Override
    protected void add() throws IOException, InterruptedException {
        clearComandLine();

        String regex = ((DictionaryInFileWithRegex)dictionary).getRegex();

        System.out.println("Добавление слова ");
        System.out.format("Введите слово формата %s: \n", regex);
        String word = br.readLine();

        if (dictionary.search(word) == null)
            if (((DictionaryInFileWithRegex)dictionary).isTrueWord(word))
                addWithTranslate(word);
            else
                System.out.format("Слово не соответствует формату %s \n", regex);
        else
            System.out.println("Слово уже существует");
        Thread.sleep(1500);
    }
}
