package com.kovalenko.teracot.dto.report;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReportTemplateDTO {

    private long reportID;
    private String reportName;
    private long testResultID;
}
