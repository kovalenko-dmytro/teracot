package com.kovalenko.teracot.controller;

import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.service.test.TestTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/tests/{testTypeID}")
@RequiredArgsConstructor
public class TestTypeController {

    private final TestTypeService testTypeService;

    @GetMapping
    public ModelAndView getTestTypeIndexView(@PathVariable(name = "testTypeID") long testTypeID) {
        ModelAndView view = new ModelAndView();
        try {
            view.addObject("testType", testTypeService.findById(testTypeID));
            view.setViewName("/pages/tests/index");
        } catch (ApplicationException e) {
            view.setViewName("index");
        }
        return view;
    }
}
