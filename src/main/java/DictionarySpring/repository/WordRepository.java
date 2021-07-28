package DictionarySpring.repository;

import DictionarySpring.entity.DictionaryEntity;
import DictionarySpring.entity.WordEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class WordRepository {

    private final SessionFactory sessionFactory;

    public WordRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean deleteWord(Long id) {
        Session session = sessionFactory.getCurrentSession();
        try {
            WordEntity wordEntity = session.load(WordEntity.class, id);
            session.delete(wordEntity);
            session.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public WordEntity findWordById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(WordEntity.class, id);
    }

    public List<WordEntity> findAllByWord(String word) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<WordEntity> cq = cb.createQuery(WordEntity.class);
        Root<WordEntity> from = cq.from(WordEntity.class);

        cq.select(from).where(cb.equal(from.get("word"), word));
        Query<WordEntity> query = session.createQuery(cq);
        return query.getResultList();
    }

    public List<WordEntity> findAllByWordAndDictId(String word, Long dictId) {

        Session session = sessionFactory.getCurrentSession();

        //что-то не то
        DictionaryEntity dictionaryEntity = session.get(DictionaryEntity.class,dictId);

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<WordEntity> cq = cb.createQuery(WordEntity.class);
        Root<WordEntity> from = cq.from(WordEntity.class);

        Predicate[] predicates = new Predicate[2];
        predicates[0] = cb.equal(from.get("word"),word);
        predicates[1] = cb.equal(from.get("dictionary"), dictionaryEntity);

        cq.select(from).where(predicates);
        Query<WordEntity> query = session.createQuery(cq);
        return query.getResultList();
    }

    public void saveAndFlush(WordEntity wordEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(wordEntity);
        session.flush();
    }
}
