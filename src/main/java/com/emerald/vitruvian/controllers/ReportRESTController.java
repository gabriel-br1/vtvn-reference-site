package com.emerald.vitruvian.controllers;

import com.emerald.vitruvian.Entities.ReportEntity;
import com.emerald.vitruvian.repositories.ReportRepo;
import com.emerald.vitruvian.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
public class ReportRESTController {

    @Autowired
    private ReportRepo reportRepo;

    @Autowired
    private ReportService reportService;

    @GetMapping("reportPosts")
    @ResponseBody
    public List<ReportEntity> getAllReports(){
        List<ReportEntity> reports = StreamSupport.stream(reportRepo.findAll().spliterator(), false).toList();
        return reportService.formatReportEntityJSON(reports);
    }

}
