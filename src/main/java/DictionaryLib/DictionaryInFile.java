package DictionaryLib;

import java.io.IOException;
import java.util.Formatter;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;


public class DictionaryInFile implements DictionaryInFileInterface {

    public static final String EMPTY_STRING = "";
    public static final String FORMAT = "%s - %s\n";
    DictionaryFileSaveLoad dictionaryFileSaveLoad;
    Properties dict;
    final String fileName;


    DictionaryInFile(String fileName) throws IOException {
        this.fileName = fileName;
        dict = new Properties();
        initSaveLoad();

    }

    protected void initSaveLoad() {
        dictionaryFileSaveLoad = new DictionaryFileSaveLoad(dict);
    }

    @Override
    public boolean add(String word, String translate) {
        String loweWord = word.toLowerCase();
        Object isOk = dict.setProperty(loweWord, translate);
        return isOk == null;
    }

    @Override
    public boolean delete(String word) {
        String loweWord = word.toLowerCase();
        Object isOk = dict.remove(loweWord);
        return isOk == null;
    }

    @Override
    public String search(String word) {
        String loweWord = word.toLowerCase();
        return (String) dict.get(loweWord);
    }

    @Override
    public boolean save() {

        return dictionaryFileSaveLoad.save(fileName);

    }

    @Override
    public boolean load() {

        return dictionaryFileSaveLoad.load(fileName);

    }

    public String getName() {
        return fileName;
    }

    @Override
    public String toString() {
        String text = EMPTY_STRING;
        Formatter fmt = new Formatter();
        for (Map.Entry<Object, Object> entry : dict.entrySet()) {
            fmt.format(FORMAT, entry.getKey(), entry.getValue());
        }
        text+=fmt.toString();

        return text;
    }
}
