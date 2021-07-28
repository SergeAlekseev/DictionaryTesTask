package DictionarySpring.entity;

import DictionarySpring.model.NewTranslateModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class TranslateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    private String translate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "id_word")
    private WordEntity wordEntity;



    public TranslateEntity(String translate, WordEntity wordEntity) {
        this.translate = translate;
        this.wordEntity = wordEntity;
    }

    public TranslateEntity(NewTranslateModel newTranslateModel, WordEntity wordEntity) {
        translate = newTranslateModel.getTranslate();
        this.wordEntity = wordEntity;
    }

    public TranslateEntity() {

    }

    public void setWord(WordEntity wordEntity) {
        this.wordEntity = wordEntity;
    }

    public WordEntity getWord() {
        return wordEntity;
    }

    public TranslateEntity(String translate) {
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
