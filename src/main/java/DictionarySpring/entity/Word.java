package DictionarySpring.entity;

import DictionarySpring.model.ModelNewWord;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    private String word;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "id_dictionary")
    private Dictionary dictionary;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "word", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Translate> translates;

    public Word() {

    }

    public Word(ModelNewWord modelNewWord, Dictionary dictionary) {
        word = modelNewWord.getWord();
        this.dictionary = dictionary;
        translates = new HashSet<>();
        translates.add(new Translate(modelNewWord.getTranslate(), this));
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set<Translate> getTranslates() {
        return translates;
    }

    public void setTranslates(Set<Translate> translates) {
        this.translates = translates;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
