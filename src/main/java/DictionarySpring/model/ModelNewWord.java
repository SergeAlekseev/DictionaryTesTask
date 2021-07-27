package DictionarySpring.model;

public class ModelNewWord {
    private String word;
    private String translate;
    private Long idDict;

    public void setIdDict(Long idDict) {
        this.idDict = idDict;
    }

    public Long getIdDict() {
        return idDict;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getWord() {
        return word;
    }
}
