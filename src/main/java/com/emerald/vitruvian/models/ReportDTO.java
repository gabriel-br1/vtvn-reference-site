package com.emerald.vitruvian.models;

import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.enums.ReportType;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ReportDTO {

    private ReportType reportType;

    @NotNull(message = "choose a report reason")
    private String reportTypeStr;

    @NotNull(message = "fill in a description of the report")
    private String description;

    private ImageEntryEntity image;

    private UserEntity user;

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public String getReportTypeStr() {
        return reportTypeStr;
    }

    public void setReportTypeStr(String reportTypeStr) {
        this.reportTypeStr = reportTypeStr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageEntryEntity getImage() {
        return image;
    }

    public void setImage(ImageEntryEntity image) {
        this.image = image;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ReportDTO{" +
                "reportType=" + reportType +
                ", reportTypeStr='" + reportTypeStr + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", user=" + user +
                '}';
    }
}
