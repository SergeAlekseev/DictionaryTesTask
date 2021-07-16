package DictionaryLib;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DictionariesInFileController implements DictionariesControllerInterface {

    @Override
    public DictionaryInFile create(String name) throws IOException {
        if (!hasDictionary(name)) {
            DictionaryInFile dictionary = new DictionaryInFile(name);
            dictionary.save();
            return dictionary;
        } else
            return null;
    }

    @Override
    public boolean delete(String name) {
        try {
            return Files.deleteIfExists(Paths.get(name + DictionaryFileSaveLoad.FORMAT));
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean hasDictionary(String name) {
        return Files.exists(Paths.get(name + DictionaryFileSaveLoad.FORMAT));
    }

    @Override
    public DictionaryInFile getDictionary(String name) throws IOException {
        DictionaryInFile dictionary = new DictionaryInFile(name);
        if (!dictionary.load())
            return null;
        return dictionary;
    }
}
