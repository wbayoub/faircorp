package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class BuildingDaoCustomImpl implements BuildingDaoCustom{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Window> findWindowsByBuilding(Long id){
        List<Window> windows = new ArrayList<Window>();
        String jpql = "select r from Room r where r.building.id = :id";
        List<Room> rooms = em.createQuery(jpql).setParameter("id", id).getResultList();
        for(int i=0;i<rooms.size();i++){
            windows.addAll(rooms.get(i).getWindows());
        }
        return windows;
    }
    @Override
    public List<Room> findRoomsByBuilding(Long id){
        String jpql = "select r from Room r where r.building.id = :id";
        List<Room> rooms = em.createQuery(jpql).setParameter("id", id).getResultList();
        return rooms;
    }
    @Override
    public List<Heater> findHeatersByBuilding(Long id){
        List<Heater> heaters = new ArrayList<Heater>();
        String jpql = "select r from Room r where r.building.id = :id";
        List<Room> rooms = em.createQuery(jpql).setParameter("id", id).getResultList();
        for(int i=0;i<rooms.size();i++){
            heaters.addAll(rooms.get(i).getHeaters());
        }
        return heaters;
    }
    @Override
    public Building findByID(Long id){
        String jpql = "select b from Building b where b.id = :id ";
        return em.createQuery(jpql, Building.class)
                .setParameter("id", id)
                .getSingleResult();
    }

}
