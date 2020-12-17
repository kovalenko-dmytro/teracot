package com.kovalenko.teracot.controller;

import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.service.report.ReportTemplateService;
import com.kovalenko.teracot.service.test.TestResultService;
import com.kovalenko.teracot.service.test.TestTypeService;
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
@RequestMapping(value = "tests/{testTypeID}/test-results")
@RequiredArgsConstructor
public class TestResultController {

    private final TestResultService testResultService;
    private final TestTypeService testTypeService;
    private final ReportTemplateService reportTemplateService;

    @GetMapping
    public ModelAndView getTestResults(@PathVariable(name = "testTypeID") long testTypeID) {
        ModelAndView view = new ModelAndView();
        try {
            view.addObject("testResults", testResultService.getTestResults(testTypeID));
            view.addObject("testType", testTypeService.findById(testTypeID));
            view.setViewName("/pages/tests/test-results");
        } catch (ApplicationException e) {
            view.setViewName("/pages/tests/index");
        }
        return view;
    }

    @GetMapping(value = "/{testResultID}")
    public ModelAndView getTestResultByID(@PathVariable(name = "testTypeID") long testTypeID,
                                          @PathVariable(name = "testResultID") long testResultID) {
        ModelAndView view = new ModelAndView();
        try {
            view.addObject("testType", testTypeService.findById(testTypeID));
            view.addObject("reports", reportTemplateService.getAvailableReports(testTypeID, testResultID));
            view.setViewName("/pages/tests/test-result");
        } catch (ApplicationException e) {
            view.setViewName("/pages/tests/index");
        }
        return view;
    }

    @PostMapping
    @ResponseBody
    public void uploadTestResults(@PathVariable(name = "testTypeID") long testTypeID,
                                  @RequestParam(name = "pathToResource") String pathToResource) {
        try {
            testResultService.uploadTestResults(testTypeID, pathToResource);
        } catch (ApplicationException e) {
            System.out.println(e.getMessage());
        }
    }
}
