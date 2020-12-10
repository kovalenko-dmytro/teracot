package com.kovalenko.teracot.controller;

import com.kovalenko.teracot.service.test.TestTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final TestTypeService testTypeService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView view = new ModelAndView();
        view.addObject("testTypes", testTypeService.findAll());
        view.setViewName("index");
        return view;
    }
}
