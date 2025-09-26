package com.emerald.vitruvian.Entities;

import com.emerald.vitruvian.enums.ReportType;
import jakarta.persistence.*;

@Entity
@Table(name = "Reports")
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reportId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @Transient
    private String reportTypeStr;

    @Column(nullable = false)
    private String description;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private ImageEntryEntity image;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private UserEntity user;

    public long getReportId() {
        return reportId;
    }

    public void setReportId(long reportId) {
        this.reportId = reportId;
    }

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
        return "ReportEntity{" +
                "reportId=" + reportId +
                ", reportType=" + reportType +
                ", reportTypeStr='" + reportTypeStr + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", user=" + user +
                '}';
    }
}
