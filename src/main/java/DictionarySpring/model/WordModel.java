package DictionarySpring.model;


public class WordModel {
    private Long idWord;
    private Long idDict;

    public WordModel() {
    }

    public WordModel(Long dictId) {
        idDict=dictId;
    }

    public void setIdDict(Long idDict) {
        this.idDict = idDict;
    }

    public Long getIdDict() {
        return idDict;
    }

    public Long getIdWord() {
        return idWord;
    }

    public void setIdWord(Long idWord) {
        this.idWord = idWord;
    }
}
