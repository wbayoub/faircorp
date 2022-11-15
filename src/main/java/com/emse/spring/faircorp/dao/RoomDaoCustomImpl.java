package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.emse.spring.faircorp.model.WindowStatus;
import org.hibernate.Session;

public class RoomDaoCustomImpl implements RoomDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Room findRoomByName(String name){
        String jpql = "select r from Room r where r.name = :name";
        return em.createQuery(jpql, Room.class)
                .setParameter("name", name)
                .getSingleResult();
    }

}
