package DictionarySpring.repository;

import DictionarySpring.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word,Long> {

    public List<Word> findAllByWord(String word);

    public List<Word> findAllByWordAndDictId(String word,Long dictId);
}
