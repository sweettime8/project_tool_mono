package com.mrd.tool.repository;

import com.mrd.tool.common.ultils.StringUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.util.Map;

public class BaseRepository {
    protected SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    protected BaseRepository(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }

        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    protected Session openSession() {
        Session session = this.sessionFactory.openSession();
        return session;
    }

    protected void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            session.disconnect();
            session.close();
        }
    }

    public <T> TypedQuery<T> createQuery(final String sql,
                                         final Map<String, Object> params,
                                         final Class<T> clazz) {
        TypedQuery<T> query = this.getEntityManager().createQuery(sql, clazz);
        this.initQueryParams(query, params);
        return query;
    }

    public void initQueryParams(final Query query,
                                final Map<String, Object> params) {
        if (!StringUtil.isNullOrEmpty(params.toString())) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
    }

    public void initPaging(final Query query, int pageNo, int pageSize) {
        query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
    }
}
