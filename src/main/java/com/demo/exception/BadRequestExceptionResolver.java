package com.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BadRequestExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        BadRequestException badRequestException;
        if (e instanceof BadRequestException) {
            badRequestException = (BadRequestException) e;
        } else {
            badRequestException = new BadRequestException(ErrorCode.INTERNAL_SYSTEM_ANOMALY);
        }

        ModelAndView modelAndView = new ModelAndView();
        MappingJackson2JsonView jackson2JsonView = new MappingJackson2JsonView();
        modelAndView.setView(jackson2JsonView);
        modelAndView.addObject("code", badRequestException.getCode());
        modelAndView.addObject("msg", badRequestException.getMsg());
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        return modelAndView;
    }
}
