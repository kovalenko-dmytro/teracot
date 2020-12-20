package com.kovalenko.teracot.dto.collected;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CollectedStatisticReportInfoDTO {

    private String reportName;
    private CollectedStatisticFieldNameDTO fieldNames;
    private CollectedStatisticDTO current;
    private CollectedStatisticDTO lastApplied;
}
