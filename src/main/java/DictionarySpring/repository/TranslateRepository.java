package DictionarySpring.repository;

import DictionarySpring.entity.TranslateEntity;
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

    public List<TranslateEntity> findAllByTranslate(String translate) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TranslateEntity> cq = cb.createQuery(TranslateEntity.class);
        Root<TranslateEntity> from = cq.from(TranslateEntity.class);
        cq.select(from).where(cb.equal(from.get("translate"), translate));
        Query<TranslateEntity> query = session.createQuery(cq);
        return query.getResultList();
    }

    public boolean deleteById(Long idTranslate) {
        Session session = sessionFactory.getCurrentSession();
        try {
            TranslateEntity translateEntity = session.load(TranslateEntity.class, idTranslate);
            translateEntity.getWord().getTranslates().remove(translateEntity);
            session.delete(translateEntity);
            session.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void saveAndFlush(TranslateEntity translateEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(translateEntity);
        session.flush();
    }
}
