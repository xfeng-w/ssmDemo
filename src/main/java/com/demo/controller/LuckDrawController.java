package com.demo.controller;

import com.demo.service.CheckTokenService;
import com.demo.service.LuckDrawService;
import com.demo.vo.LuckDrawVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 转盘抽奖
 */
@Controller
@RequestMapping("luckDraw")
public class LuckDrawController {

    @Autowired
    private LuckDrawService luckDrawService;

    @Autowired
    private CheckTokenService checkTokenService;

    @ResponseBody
    @GetMapping()
    public LuckDrawVO luckDraw(Long userId, String token, String activityCode) {
        checkTokenService.checkToken(userId, token);
        return luckDrawService.lottery(userId, activityCode);
    }
}
