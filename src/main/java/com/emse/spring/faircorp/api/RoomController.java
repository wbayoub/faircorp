package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.*;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController // (1)
@CrossOrigin
@RequestMapping("/api/rooms") // (2)
@Transactional // (3)
public class RoomController {
    private final WindowDao windowDao;

    private final BuildingDao buildingDao;
    private final RoomDao roomDao;

    private final HeaterDao heaterDao;

    public RoomController(WindowDao windowDao, RoomDao roomDao,BuildingDao buildingDao,HeaterDao heaterDao) { // (4)
        this.windowDao = windowDao;
        this.roomDao = roomDao;
        this.buildingDao=buildingDao;
        this.heaterDao = heaterDao;
    }

    @GetMapping // (5)
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());  // (6)
    }

    @GetMapping(path = "/{room_id}")
    public RoomDto findById(@PathVariable Long room_id) {
        return roomDao.findById(room_id).map(RoomDto::new).orElse(null); // (7)
    }

    @PostMapping // (8)
    public RoomDto create(@RequestBody RoomDto dto) {
        // RoomDto must always contain the window room
        Building building=buildingDao.getReferenceById(dto.getBuilding_id());
        Room room ;
        // On creation id is not defined
        if (dto.getId() == null) {
            room = roomDao.save(new Room(dto.getFloor(),dto.getName(),building,dto.getCurrent_temperature(),dto.getTarget_temperature()));
        }
        else {
            room = roomDao.getById(dto.getId());  // (9)
            room.setCurrent_temperature(dto.getCurrent_temperature());
            room.setTarget_temperature(dto.getTarget_temperature());
        }
        return new RoomDto(room);
    }

    @DeleteMapping(path = "/{room_id}")
    public void delete(@PathVariable Long room_id) {
        List<Window> windows=windowDao.findWindowsByRoomId(room_id);
        for (Window window : windows){
            windowDao.deleteById(window.getId());
        }
        List<Heater> heaters=heaterDao.findHeatersByRoomId(room_id);
        for (Heater heater : heaters){
            heaterDao.deleteById(heater.getId());
        }
        roomDao.deleteById(room_id);
    }

    @PutMapping(path = "/{room_id}/switchWindow")
    public RoomDto switchStatusWindows(@PathVariable Long room_id) {
        List<Window> windows=windowDao.findWindowsByRoomId(room_id);
        for (Window window : windows){
            window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
        }
        return roomDao.findById(room_id).map(RoomDto::new).orElse(null);
    }

    @PutMapping(path = "{room_id}/switchHeaters")
    public RoomDto switchStatusHeaters(@PathVariable Long room_id) {
        List<Heater> heaters=heaterDao.findHeatersByRoomId(room_id);
        for (Heater heater : heaters){
            heater.setHeater_status(heater.getHeater_status() == HeaterStatus.ON? HeaterStatus.OFF: HeaterStatus.ON);
        }
        return roomDao.findById(room_id).map(RoomDto::new).orElse(null);
    }
}