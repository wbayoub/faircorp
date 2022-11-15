package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.*;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@DataJpaTest

public class BuildingDaoTest {

    @Autowired
    private BuildingDao buildingDao;

    @Test
    public void shouldFindWindowsByBuilding() {

        List<Window> windows = buildingDao.findWindowsByBuilding(-9L);
        Assertions.assertThat(windows)
                .hasSize(4)
                .extracting("id", "windowStatus")
                .contains(Tuple.tuple(-10L, WindowStatus.CLOSED),Tuple.tuple(-9L, WindowStatus.CLOSED),Tuple.tuple(-8L, WindowStatus.OPEN),Tuple.tuple(-7L, WindowStatus.CLOSED));
    }

    @Test
    public void shouldFindRoomsByBuilding() {

        List<Room> rooms = buildingDao.findRoomsByBuilding(-9L);
        Assertions.assertThat(rooms)
                .hasSize(2)
                .extracting("id", "current_temperature")
                .contains(Tuple.tuple(-10L, 22.3),Tuple.tuple(-9L, null));
    }

    @Test
    public void shouldFindHeatersByBuilding() {

        List<Heater> heaters = buildingDao.findHeatersByBuilding(-9L);
        Assertions.assertThat(heaters)
                .hasSize(2)
                .extracting("id", "heater_status","power")
                .contains(Tuple.tuple(-9L, HeaterStatus.ON,null),Tuple.tuple(-10L, HeaterStatus.ON,2000));
    }
    @Test
    public void shouldFindAllHeaters() {
        List<Heater> heaters = buildingDao.findHeatersByBuilding(-9L);
        Assertions.assertThat(heaters).hasSize(2);
    }
    @Test
    public void findRoomsByBuilding() {
        List<Room> rooms = buildingDao.findRoomsByBuilding(-9L);
        Assertions.assertThat(rooms).hasSize(2);
    }
}
