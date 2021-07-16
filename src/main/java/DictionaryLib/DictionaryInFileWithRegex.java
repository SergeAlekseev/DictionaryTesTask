package DictionaryLib;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DictionaryInFileWithRegex extends DictionaryInFile {

    static final String REGEX = "@regex";
    private String regex;
    private Pattern pattern;

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
        if (result) {
            result = getAndRemoveRegex();
            pattern = Pattern.compile(regex);
        }
        return result;
    }

    private boolean getAndRemoveRegex() {

        regex = dict.getProperty(REGEX);
        if (regex != null) {

            dict.remove(REGEX);
            return true;
        } else
            return false;
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
        if (isTrueWord(word))
            return super.add(word, translate);
        else
            return false;
    }

    public boolean isTrueWord(String word) {
        Matcher matcher = pattern.matcher(word);
        return matcher.find();
    }

    public String getRegex() {
        return regex;
    }
}
