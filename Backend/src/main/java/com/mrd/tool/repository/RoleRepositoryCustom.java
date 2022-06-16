package com.mrd.tool.repository;

import com.mrd.tool.common.ultils.StringUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;

@Repository
public class RoleRepositoryCustom {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleRepositoryCustom.class);

    private SessionFactory sessionFactory;

    @Autowired
    public RoleRepositoryCustom(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }

        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public boolean updateRoleCode(String newRoleCode, String newRoleDetail, String roleCodeOld) {
        Session session = openSession();
        try {
            session.getTransaction().begin();
            String sql = "UPDATE vsat02.role SET ";
            String condition = "";

            if (!StringUtil.isNullOrEmpty(newRoleCode)) {
                condition += " role_code = :newRoleCode ";
            }
            if (!StringUtil.isNullOrEmpty(newRoleDetail)) {
                condition += ", role_detail = :newRoleDetail ";
            }
            if (!StringUtil.isNullOrEmpty(newRoleCode)) {
                condition += ", role_name = :newRoleCode ";
            }

            condition += " WHERE role_code = :roleCodeOld";
            String sqlRun = sql + condition;

            NativeQuery query = session.createNativeQuery(sqlRun);
            if (!StringUtil.isNullOrEmpty(newRoleCode)) {
                query.setParameter("newRoleCode", newRoleCode);
            }
            if (!StringUtil.isNullOrEmpty(newRoleDetail)) {
                query.setParameter("newRoleDetail", newRoleDetail);
            }
            if (!StringUtil.isNullOrEmpty(roleCodeOld)) {
                query.setParameter("roleCodeOld", roleCodeOld);
            }
            int i = query.executeUpdate();
            session.getTransaction().commit();
            if (i > 0) {
                return true;
            }

        } catch (EntityNotFoundException ex) {
            session.getTransaction().rollback();
            LOGGER.error(ex.toString());
        } finally {
            closeSession(session);
        }

        return false;

    }

    private Session openSession() {
        Session session = this.sessionFactory.openSession();
        return session;
    }

    private void closeSession(Session session) {
        if (session.isOpen()) {
            session.disconnect();
            session.close();
        }
    }
}
