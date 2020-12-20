package com.kovalenko.teracot.dto.collected;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CollectedStatisticFieldNameDTO {

    private String versionFieldName;
    private String dialectPairFieldName;
    private String sourceConnectionFieldName;
    private String targetConnectionFieldName;
    private String gcMemoryFieldName;
    private String treeValidatorFieldName;
    private String allObjectsCountFieldName;
    private String transformedObjectsFieldName;
    private String appliedObjectsFieldName;
    private String transformerFromErrorReportFieldName;
    private String nullPointerExceptionsFieldName;
    private String projectSizeNameFieldName;
    private String targetSizeFieldName;
    private String sourceSizeFieldName;
    private String totalOperationTimeFieldName;
    private String loadingTimeFieldName;
    private String conversionTimeFieldName;
    private String applyTimeFieldName;
    private String savingTimeFieldName;
    private String totalWithoutLoadingTimeFieldName;
    private String totalTimeFieldName;
    private String actionItemCodeFieldName;
    private String actionItemCountFieldName;
}
