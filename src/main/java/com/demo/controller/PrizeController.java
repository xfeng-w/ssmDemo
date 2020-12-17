package com.demo.controller;

import com.demo.entity.Prize;
import com.demo.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prize")
public class PrizeController {

    @Autowired
    private PrizeService prizeService;

    @PostMapping
    public Prize add(@RequestBody Prize prize) {
        return prizeService.add(prize);
    }

    @PutMapping
    public Prize update(@RequestBody Prize prize) {
        return prizeService.updateById(prize);
    }
}
