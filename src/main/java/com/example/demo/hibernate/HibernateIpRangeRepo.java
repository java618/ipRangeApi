package com.example.demo.hibernate;

import com.example.demo.entities.IpRangeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class HibernateIpRangeRepo {

    @PersistenceContext
    EntityManager em;

    public Optional<IpRangeEntity> findByRange(long range){

        return em.createQuery("select t from IpRangeEntity t where " +
                "t.lowerRange <= :range AND t.upperRange >= :range",IpRangeEntity.class).setParameter("range",range)
                .getResultList().stream().findFirst();
    }
}
