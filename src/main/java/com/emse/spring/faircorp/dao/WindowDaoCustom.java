package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;

import java.util.List;

public interface WindowDaoCustom {
    List<Window> findRoomOpenWindows(Long id);
    List<Window> findWindowsByRoom(String name);

    Window findByID(Long id);

    List<Window> findWindowsByRoomId(Long id);
    int deleteByRoom(Long ID);
}
