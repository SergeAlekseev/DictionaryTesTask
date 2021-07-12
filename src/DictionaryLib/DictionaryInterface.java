package DictionaryLib;

public interface DictionaryInterface {

    boolean add(String word, String translate);

    boolean delete(String word);

    String search(String word);

}
