package DictionaryLib;

import java.io.IOException;

public interface DictionariesControllerInterface {

    DictionaryInterface create(String name) throws IOException;

    boolean delete(String name);

    boolean hasDictionary(String name);

    DictionaryInterface getDictionary(String dictionaryName) throws IOException;

}
