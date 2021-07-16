package DictionaryLib;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class DictionaryFileSaveLoad {

    public static final String FORMAT = ".dict";
    Properties dict;

    DictionaryFileSaveLoad(Properties dict) {
        this.dict = dict;
    }

    public boolean save(String fileName) {
        FileOutputStream fout;
        try {
            fout = new FileOutputStream(fileName + FORMAT);
            dict.store(fout, null);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public boolean load(String fileName) {
        FileInputStream fin;
        try {
            fin = new FileInputStream(fileName + FORMAT);
            dict.load(fin);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

}
