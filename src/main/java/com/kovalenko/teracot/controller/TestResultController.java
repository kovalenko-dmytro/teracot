package com.kovalenko.teracot.controller;

import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.service.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/tests")
@RequiredArgsConstructor
public class TestResultController {

    private final TestResultService testResultService;

    @GetMapping(value = "/{testTypeID}")
    public ModelAndView getSummaryApplyingStatistics(@PathVariable(name = "testTypeID") long testTypeID) {
        ModelAndView view = new ModelAndView();
        view.addObject("summary", testResultService.getSummaryApplyingStatistics(testTypeID));
        view.setViewName("/pages/tests/index");
        return view;
    }

    @PostMapping
    @ResponseBody
    public void uploadTestResults(@RequestParam(name = "testTypeID") long testTypeID,
                                  @RequestParam(name = "pathToResource") String pathToResource) {
        try {
            testResultService.uploadTestResults(testTypeID, pathToResource);
        } catch (ApplicationException e) {
            System.out.println(e.getMessage());
        }
    }
}
