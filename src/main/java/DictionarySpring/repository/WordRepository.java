package DictionarySpring.repository;

import DictionarySpring.entity.Dictionary;
import DictionarySpring.entity.Word;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.internal.compile.CriteriaQueryTypeQueryAdapter;
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
            Word word = session.load(Word.class, id);
            session.delete(word);
            session.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Word findWordById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Word.class, id);
    }

    public List<Word> findAllByWord(String word) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Word> cq = cb.createQuery(Word.class);
        Root<Word> from = cq.from(Word.class);

        cq.select(from).where(cb.equal(from.get("word"), word));
        Query<Word> query = session.createQuery(cq);
        return query.getResultList();
    }

    public List<Word> findAllByWordAndDictId(String word, Long dictId) {

        Session session = sessionFactory.getCurrentSession();

        //что-то не то
        Dictionary dictionary = session.get(Dictionary.class,dictId);

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Word> cq = cb.createQuery(Word.class);
        Root<Word> from = cq.from(Word.class);

        Predicate[] predicates = new Predicate[2];
        predicates[0] = cb.equal(from.get("word"),word);
        predicates[1] = cb.equal(from.get("dictionary"),dictionary);

        cq.select(from).where(predicates);
        Query<Word> query = session.createQuery(cq);
        return query.getResultList();
    }

    public void saveAndFlush(Word word) {
        Session session = sessionFactory.getCurrentSession();
        session.save(word);
        session.flush();
    }
}
