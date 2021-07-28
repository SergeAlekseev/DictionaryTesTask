package DictionarySpring.repository;

import DictionarySpring.entity.Translate;
import DictionarySpring.entity.Word;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class TranslateRepository {
    private final SessionFactory sessionFactory;

    public TranslateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Translate> findAllByTranslate(String translate) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Translate> cq = cb.createQuery(Translate.class);
        Root<Translate> from = cq.from(Translate.class);
        cq.select(from).where(cb.equal(from.get("translate"), translate));
        Query<Translate> query = session.createQuery(cq);
        return query.getResultList();
    }

    public boolean deleteById(Long idTranslate) {
        Session session = sessionFactory.getCurrentSession();
        try {
            Translate translate = session.load(Translate.class, idTranslate);
            translate.getWord().getTranslates().remove(translate);
            session.delete(translate);
            session.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void saveAndFlush(Translate translate) {
        Session session = sessionFactory.getCurrentSession();
        session.save(translate);
        session.flush();
    }
}
