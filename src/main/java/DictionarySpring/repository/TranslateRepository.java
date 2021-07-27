package DictionarySpring.repository;

import DictionarySpring.entity.Translate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TranslateRepository extends JpaRepository<Translate,Long> {

    public List<Translate> findAllByTranslate(String translate);

}
