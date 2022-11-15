package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.AdressSearchService;
import com.emse.spring.faircorp.dto.ApiGouvAdressDto;
import com.emse.spring.faircorp.dto.WindowDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController // (1)
@CrossOrigin
@RequestMapping("/api/adress") // (2)
@Transactional
public class AdressController {
    private final AdressSearchService adressSearchService;

    public AdressController(AdressSearchService adressSearchService){
        this.adressSearchService=adressSearchService;
    }

    @GetMapping // (5)
    public List<ApiGouvAdressDto> findAll(@RequestParam String keys) {
        return adressSearchService.findAdress(List.of(keys.split("-")));  // (6)
    }
}
