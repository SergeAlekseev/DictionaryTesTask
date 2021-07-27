package DictionarySpring.repository;

import DictionarySpring.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary,Long> {
}
