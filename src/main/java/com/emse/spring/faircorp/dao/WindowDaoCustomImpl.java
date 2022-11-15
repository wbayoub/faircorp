package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class WindowDaoCustomImpl implements WindowDaoCustom{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Window> findRoomOpenWindows(Long id) {
        String jpql = "select w from Window w where w.room.id = :id and w.windowStatus= :status";
        return em.createQuery(jpql, Window.class)
                .setParameter("id", id)
                .setParameter("status", WindowStatus.OPEN)
                .getResultList();
    }

    @Override
    public Window findByID(Long id){
        String jpql = "select w from Window w where w.id = :id ";
        return em.createQuery(jpql, Window.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    @Override
    public List<Window> findWindowsByRoom(String name) {
        String jpql_ = "select r from Room r where r.name = :name";
        Room room = (Room) em.createQuery(jpql_).setParameter("name", name).getSingleResult();
        Long id =room.getId();
        String jpql = "select w from Window w where w.room.id = :id";
        return em.createQuery(jpql, Window.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Window> findWindowsByRoomId(Long id) {
        String jpql = "select w from Window w where w.room.id = :id";
        return em.createQuery(jpql, Window.class)
                .setParameter("id", id)
                .getResultList();
    }
    @Override
    public int deleteByRoom(Long id){
        String jpql = "delete from Window w where w.room.id = :id";
        return em.createQuery(jpql)
                .setParameter("id", id)
                .executeUpdate();
    }
}
