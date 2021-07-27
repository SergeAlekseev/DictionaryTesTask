package DictionarySpring.entity;

import DictionarySpring.model.ModelNewWord;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    @Column(name = "id_dictionary")
    private Long dictId;

    @OneToMany(mappedBy = "word",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Translate> translates;

    public Word(){

    }

    public Word(ModelNewWord modelNewWord) {
        word=modelNewWord.getWord();
        dictId = modelNewWord.getIdDict();
        translates = new ArrayList<>();
        translates.add(new Translate(modelNewWord.getTranslate(),this));
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public Long getDictId() {
        return dictId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Translate> getTranslates() {
        return translates;
    }

    public void setTranslates(List<Translate> translates) {
        this.translates = translates;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
