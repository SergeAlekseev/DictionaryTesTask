package DictionarySpring.entity;

import DictionarySpring.model.NewWordModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "word")
public class WordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    private String word;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "id_dictionary")
    private DictionaryEntity dictionary;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "word", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TranslateEntity> translates;

    public WordEntity() {

    }

    public WordEntity(NewWordModel newWordModel, DictionaryEntity dictionaryEntity) {
        word = newWordModel.getWord();
        this.dictionary = dictionaryEntity;
        translates = new HashSet<>();
        translates.add(new TranslateEntity(newWordModel.getTranslate(), this));
    }

    public void setDictionary(DictionaryEntity dictionaryEntity) {
        this.dictionary = dictionaryEntity;
    }

    public DictionaryEntity getDictionary() {
        return dictionary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set<TranslateEntity> getTranslates() {
        return translates;
    }

    public void setTranslates(Set<TranslateEntity> translateEntities) {
        this.translates = translateEntities;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
