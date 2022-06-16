package com.mrd.tool.repository;

import com.mrd.tool.entity.dto.RoleUserDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class RoleUserRepositoryCustom extends BaseRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleUserRepositoryCustom.class);


    @Autowired
    public RoleUserRepositoryCustom(EntityManagerFactory factory) {
        super(factory);
    }

    public List<RoleUserDTO> findAllRoleUser() {
        Session session = openSession();
        try {
            String sql = "SELECT id as id, uuid_user as uuidUser, role_code as roleCode "
                    + " FROM role_user ";
            return session.createNativeQuery(sql)
                    .addScalar("id", LongType.INSTANCE)
                    .addScalar("uuidUser", StringType.INSTANCE)
                    .addScalar("roleCode", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(RoleUserDTO.class))
                    .getResultList();
        } catch (Exception ex) {
            LOGGER.error(ex.toString());
        } finally {
            closeSession(session);
        }
        return null;
    }

}
