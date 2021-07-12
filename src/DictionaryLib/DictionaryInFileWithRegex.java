package DictionaryLib;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DictionaryInFileWithRegex extends DictionaryInFile {

    static final String REGEX = "@regex";
    String regex;
    Pattern pattern;

    DictionaryInFileWithRegex(String fileName, String regex) throws IOException {
        super(fileName);
        pattern = Pattern.compile(regex);
        this.regex = regex;
    }

    DictionaryInFileWithRegex(String fileName) throws IOException {
        super(fileName);
    }

    @Override
    public boolean load() {
        boolean result = super.load();
        getAndRemoveRegex(result);
        return result;
    }

    private void getAndRemoveRegex(boolean result) {
        if (result) {
            regex = dict.getProperty(REGEX);
            dict.remove(REGEX);
        }
    }

    @Override
    public boolean save() {
        dict.setProperty(REGEX, regex);
        boolean result = super.save();
        dict.remove(REGEX);
        return result;
    }

    @Override
    public boolean add(String word, String translate) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(word);
        if (matcher.find())
            return super.add(word, translate);
        else
            return false;
    }
}
