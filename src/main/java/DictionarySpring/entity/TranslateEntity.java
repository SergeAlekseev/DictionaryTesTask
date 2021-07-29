package DictionarySpring.entity;

import DictionarySpring.model.NewTranslateModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "translate")
public class TranslateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    private String translate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "id_word")
    private WordEntity word;



    public TranslateEntity(String translate, WordEntity wordEntity) {
        this.translate = translate;
        this.word = wordEntity;
    }

    public TranslateEntity(NewTranslateModel newTranslateModel, WordEntity wordEntity) {
        translate = newTranslateModel.getTranslate();
        this.word = wordEntity;
    }

    public TranslateEntity() {

    }

    public void setWord(WordEntity wordEntity) {
        this.word = wordEntity;
    }

    public WordEntity getWord() {
        return word;
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
