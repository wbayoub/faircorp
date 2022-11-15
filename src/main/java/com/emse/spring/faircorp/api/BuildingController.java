package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.BuildingDto;
import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.*;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController // (1)
@CrossOrigin
@RequestMapping("/api/buildings") // (2)
@Transactional // (3)
public class BuildingController {

    private final HeaterDao heaterDao;
    private final RoomDao roomDao;
    private final WindowDao windowDao;

    private final BuildingDao buildingDao;

    public BuildingController(HeaterDao heaterDao, RoomDao roomDao,BuildingDao buildingDao,WindowDao windowDao) { // (4)
        this.heaterDao = heaterDao;
        this.roomDao = roomDao;
        this.buildingDao = buildingDao;
        this.windowDao=windowDao;
    }

    @GetMapping(path = "/{id}")
    public BuildingDto findById(@PathVariable Long id) {
        return buildingDao.findById(id).map(BuildingDto::new).orElse(null); // (7)
    }

    @PostMapping // (8)
    public BuildingDto create(@RequestBody BuildingDto dto) {
        Building building;
        // On creation id is not defined
        if (dto.getId() == null) {
            building = buildingDao.save(new Building(dto.getOutsideTemperature()));
        }
        else {
            building = buildingDao.getById(dto.getId());  // (9)
        }
        return new BuildingDto(building);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        List<Heater> heaters=buildingDao.findHeatersByBuilding(id);
        for(Heater h : heaters ){
            heaterDao.deleteById(h.getId());
        }
        List<Window> windows=buildingDao.findWindowsByBuilding(id);
        for(Window w : windows){
            windowDao.deleteById(w.getId());
        }
        List<Room> rooms=buildingDao.findRoomsByBuilding(id);
        for(Room r : rooms){
            roomDao.deleteById(r.getId());
        }
        buildingDao.deleteById(id);
    }
}
