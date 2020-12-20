package com.kovalenko.teracot.dto.report;

import com.kovalenko.teracot.dto.collected.CollectedStatisticReportInfoDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReportInfoDTO {

    private long reportID;
    private CollectedStatisticReportInfoDTO collectedStatisticReportInfoDTO;
}
