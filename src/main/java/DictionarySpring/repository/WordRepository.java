package DictionarySpring.repository;

import DictionarySpring.entity.Translate;
import DictionarySpring.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WordRepository extends JpaRepository<Word,Long> {

    public List<Word> findAllByWord(String word);

    public List<Word> findAllByWordAndDictId(String word,Long dictId);

    public Word getByIdAndDictId(Long wordId, Long dictId);

}
