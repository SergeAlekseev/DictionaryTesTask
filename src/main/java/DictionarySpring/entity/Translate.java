package DictionarySpring.entity;

import DictionarySpring.model.ModelNewTranslate;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Translate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    private String translate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "id_word")
    private Word word;



    public Translate(String translate, Word word) {
        this.translate = translate;
        this.word = word;
    }

    public Translate(ModelNewTranslate modelNewTranslate, Word word) {
        translate = modelNewTranslate.getTranslate();
        this.word = word;
    }

    public Translate() {

    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Word getWord() {
        return word;
    }

    public Translate(String translate) {
        this.translate = translate;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getTranslate() {
        return translate;
    }
}
