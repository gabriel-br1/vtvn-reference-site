package com.emerald.vitruvian.services;

import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.Entities.ReportEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.enums.ReportType;
import com.emerald.vitruvian.mappers.ReportMapper;
import com.emerald.vitruvian.models.ReportDTO;
import com.emerald.vitruvian.repositories.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private ReportRepo reportRepo;

    @Autowired
    private ReportMapper reportMapper;

    public void add(ReportDTO report, ImageEntryEntity image, UserEntity user){
        report.setImage(image);
        report.setUser(user);
        report.setReportType(parseReportType(report.getReportTypeStr()));
        ReportEntity newReport = reportMapper.toEntity(report);
        reportRepo.save(newReport);
    }

    public ReportType parseReportType(String type){
        return switch (type) {
            case "spam" -> ReportType.SPAM;
            case "duplicate" -> ReportType.DUPLICATE;
            case "copyright" -> ReportType.COPYRIGHT_VIOLATION;
            case "law" -> ReportType.LAW_VIOLATION;
            default -> null;
        };
    }

}
