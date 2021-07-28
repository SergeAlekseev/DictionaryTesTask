package DictionarySpring.repository;

import DictionarySpring.entity.DictionaryEntity;
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
public class DictionaryRepository {

    private final SessionFactory sessionFactory;

    public DictionaryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public DictionaryEntity findDictById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(DictionaryEntity.class, id);
    }

    public List<DictionaryEntity> findDicts() {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<DictionaryEntity> cq = cb.createQuery(DictionaryEntity.class);
        Root<DictionaryEntity> from = cq.from(DictionaryEntity.class);
        Query<DictionaryEntity> query = session.createQuery(cq.select(from));
        return query.getResultList();
    }
}
