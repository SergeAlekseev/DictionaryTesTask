package DictionarySpring.entity;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "dictionary")
public class DictionaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    private String regex;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dictionary", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<WordEntity> words;

    public Set<WordEntity> getWords() {
        return words;
    }

    public void setWords(Set<WordEntity> words) {
        this.words = words;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
