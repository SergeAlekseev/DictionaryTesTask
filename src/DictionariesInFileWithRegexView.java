import DictionaryLib.DictionariesInFileController;
import DictionaryLib.DictionariesInFileWithRegexController;
import DictionaryLib.DictionaryInFile;
import DictionaryLib.DictionaryInFileWithRegex;

import java.io.IOException;
import java.util.regex.PatternSyntaxException;

public class DictionariesInFileWithRegexView extends DictionariesInFileView {


    public DictionariesInFileWithRegexView() throws IOException, InterruptedException {
    }

    @Override
    protected void initController() {
        dictionariesController = new DictionariesInFileWithRegexController();
    }

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

    protected void dictionaryMenu(DictionaryInFile dictionary) throws IOException, InterruptedException {
        new DictionaryInFileWithRegexView((DictionaryInFileWithRegex)dictionary);
    }


}
