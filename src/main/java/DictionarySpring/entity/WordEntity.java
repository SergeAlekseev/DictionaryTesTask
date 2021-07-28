package DictionarySpring.entity;

import DictionarySpring.model.NewWordModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class WordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    private String word;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "id_dictionary")
    private DictionaryEntity dictionaryEntity;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "word", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TranslateEntity> translateEntities;

    public WordEntity() {

    }

    public WordEntity(NewWordModel newWordModel, DictionaryEntity dictionaryEntity) {
        word = newWordModel.getWord();
        this.dictionaryEntity = dictionaryEntity;
        translateEntities = new HashSet<>();
        translateEntities.add(new TranslateEntity(newWordModel.getTranslate(), this));
    }

    public void setDictionary(DictionaryEntity dictionaryEntity) {
        this.dictionaryEntity = dictionaryEntity;
    }

    public DictionaryEntity getDictionary() {
        return dictionaryEntity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set<TranslateEntity> getTranslates() {
        return translateEntities;
    }

    public void setTranslates(Set<TranslateEntity> translateEntities) {
        this.translateEntities = translateEntities;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
