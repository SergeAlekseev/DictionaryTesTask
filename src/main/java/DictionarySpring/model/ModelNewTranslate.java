package DictionarySpring.model;

public class ModelNewTranslate {
    private String translate;
    private Long idWord;

    public Long getIdWord() {
        return idWord;
    }

    public void setIdWord(Long idWord) {
        this.idWord = idWord;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getTranslate() {
        return translate;
    }
}
