package DictionaryLib;

import java.io.IOException;
import java.util.Formatter;
import java.util.Map;
import java.util.Properties;



public class DictionaryInFile implements DictionaryInFileInterface {

    public static final String EMPTY_STRING = "";
    public static final String FORMAT = "%s - %s+\n";
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
        return dict.setProperty(loweWord, translate) != null;
    }

    @Override
    public boolean delete(String word) {
        String loweWord = word.toLowerCase();
        return dict.remove(loweWord) != null;
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

    @Override
    public String toString() {
        String text = EMPTY_STRING;
        Formatter fmt = new Formatter();
        for (Map.Entry<Object, Object> entry : dict.entrySet()) {
            text += fmt.format(FORMAT, entry.getKey(), entry.getValue());
        }

        return text;
    }
}
