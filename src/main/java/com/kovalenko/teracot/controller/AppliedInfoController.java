package com.kovalenko.teracot.controller;

import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.service.apply.AppliedInfoService;
import com.kovalenko.teracot.service.test.TestTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "tests/{testTypeID}/applied-info")
@RequiredArgsConstructor
public class AppliedInfoController {

    private final AppliedInfoService appliedInfoService;
    private final TestTypeService testTypeService;

    @GetMapping
    public ModelAndView getAppliedInfo(@PathVariable(name = "testTypeID") long testTypeID) {
        ModelAndView view = new ModelAndView();
        try {
            view.addObject("testType", testTypeService.findById(testTypeID));
            view.addObject("appliedInfo", appliedInfoService.getAppliedInfo(testTypeID));
            view.setViewName("/pages/tests/applied-info");
        } catch (ApplicationException e) {
            view.setViewName("/pages/tests/index");
        }
        return view;
    }
}
