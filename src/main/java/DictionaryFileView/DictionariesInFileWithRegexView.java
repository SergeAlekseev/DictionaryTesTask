package DictionaryFileView;

import DictionaryLib.DictionariesInFileWithRegexController;
import DictionaryLib.DictionaryInFile;
import DictionaryLib.DictionaryInFileWithRegex;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.PatternSyntaxException;

public abstract class DictionariesInFileWithRegexView extends DictionariesInFileView {


    @Override
    protected void initDict(String dictionaryName) throws IOException, InterruptedException {
        DictionaryInFile dictionary;
        System.out.println("Введите регулярное выражение для слов словаря: ");
        String regex = br.readLine();
        try {
            dictionary = ((DictionariesInFileWithRegexController)dictionariesController).create(dictionaryName, regex);
            createEnd(dictionaryName, dictionary);
        } catch (PatternSyntaxException e) {
            System.out.println("Введен неверный формат регулярного выражения ");
        }
    }

    @Override
    protected void dictionaryMenu(DictionaryInFile dictionary) throws IOException, InterruptedException {
        getDictView(dictionary);
    }

    abstract DictionaryInFileWithRegexView getDictView(DictionaryInFile dictionary) throws IOException, InterruptedException;


}
