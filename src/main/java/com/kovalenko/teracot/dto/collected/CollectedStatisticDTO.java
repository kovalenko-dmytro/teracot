package com.kovalenko.teracot.dto.collected;

import com.kovalenko.teracot.entity.ai.ActionItemCount;
import com.kovalenko.teracot.entity.time.TimeInfo;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CollectedStatisticDTO {

    private String version;
    private String dialectPair;
    private String sourceConnection;
    private String targetConnection;
    private String gcMemory;
    private String treeValidatorState;
    private int allObjectsCount;
    private int transformedObjectsCount;
    private int appliedObjectsCount;
    private int transformerFromErrorReportCount;
    private int nullPointerExceptionsCount;
    private String projectSize;
    private String targetSize;
    private String sourceSize;
    private List<TimeInfo> timeInfo;
    private List<ActionItemCount> actionItemCounts;
}
