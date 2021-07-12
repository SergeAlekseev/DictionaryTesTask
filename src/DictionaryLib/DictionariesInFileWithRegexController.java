package DictionaryLib;

import java.io.IOException;

public class DictionariesInFileWithRegexController extends DictionariesInFileController {

    public static final String EMPTY_REGEX = "^";

    @Override
    public DictionaryInFileWithRegex create(String name) throws IOException {
        return new DictionaryInFileWithRegex(name, EMPTY_REGEX);

    }

    public DictionaryInFileWithRegex create(String name, String regex) throws IOException {
        if (!hasDictionary(name)) {
            DictionaryInFileWithRegex dictionary = new DictionaryInFileWithRegex(name, regex);
            dictionary.save();
            return dictionary;
        } else
            return null;
    }

    @Override
    public DictionaryInFileWithRegex getDictionary(String name) throws IOException {
        DictionaryInFileWithRegex dictionary = new DictionaryInFileWithRegex(name);
        dictionary.load();
        return dictionary;
    }
}
