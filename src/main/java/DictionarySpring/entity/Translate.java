package DictionarySpring.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Translate {

    @Id
    private Long id;

    private String translate;

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getTranslate() {
        return translate;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
