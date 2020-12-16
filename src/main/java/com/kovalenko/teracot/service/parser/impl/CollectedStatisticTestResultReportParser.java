package com.kovalenko.teracot.service.parser.impl;

import com.kovalenko.teracot.entity.ai.ActionItemCount;
import com.kovalenko.teracot.entity.collected.CollectedStatistic;
import com.kovalenko.teracot.entity.time.TimeInfo;
import com.kovalenko.teracot.entity.time.TimeInfoType;
import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.repository.TimeInfoTypeRepository;
import com.kovalenko.teracot.service.parser.TestResultReportParser;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectedStatisticTestResultReportParser implements TestResultReportParser<CollectedStatistic> {

    private static final String PATTERN = ".*{0},.+";
    private static final String COMMA = ",";

    @Value("${cs.template.field.version}")
    private String versionFieldName;
    @Value("${cs.template.field.dialect.pair}")
    private String dialectPairFieldName;
    @Value("${cs.template.field.source.connection}")
    private String sourceConnectionFieldName;
    @Value("${cs.template.field.target.connection}")
    private String targetConnectionFieldName;
    @Value("${cs.template.field.gc.memory}")
    private String gcMemoryFieldName;
    @Value("${cs.template.field.tree.validator}")
    private String treeValidatorFieldName;
    @Value("${cs.template.field.all.objects.count}")
    private String allObjectsCountFieldName;
    @Value("${cs.template.field.transformed.objects}")
    private String transformedObjectsFieldName;
    @Value("${cs.template.field.applied.objects}")
    private String appliedObjectsFieldName;
    @Value("${cs.template.field.transformer.from.error.report}")
    private String transformerFromErrorReportFieldName;
    @Value("${cs.template.field.null.pointer.exceptions}")
    private String nullPointerExceptionsFieldName;
    @Value("${cs.template.field.project.size}")
    private String projectSizeFieldName;
    @Value("${cs.template.field.target.size}")
    private String targetSizeFieldName;
    @Value("${cs.template.field.source.size}")
    private String sourceSizeFieldName;
    @Value("${cs.template.field.total.operation.time}")
    private String totalOperationTimeFieldName;
    @Value("${cs.template.field.loading.time}")
    private String loadingTimeFieldName;
    @Value("${cs.template.field.conversion.time}")
    private String conversionTimeFieldName;
    @Value("${cs.template.field.apply.time}")
    private String applyTimeFieldName;
    @Value("${cs.template.field.saving.time}")
    private String savingTimeFieldName;
    @Value("${cs.template.field.total.without.loading.time}")
    private String totalWithoutLoadingTimeFieldName;
    @Value("${cs.template.field.total.time}")
    private String totalTimeFieldName;
    @Value("${cs.template.field.action.item.code}")
    private String actionItemCodeFieldName;
    @Value("${cs.template.field.action.item.count}")
    private String actionItemCountFieldName;

    private final MessageSource messageSource;
    private final TimeInfoTypeRepository timeInfoTypeRepository;

    @Override
    public CollectedStatistic parse(String reportContent) throws ApplicationException {
        return CollectedStatistic.builder()
            .version(findFieldValue(this.versionFieldName, reportContent))
            .dialectPair(findFieldValue(this.dialectPairFieldName, reportContent))
            .sourceConnection(findFieldValue(this.sourceConnectionFieldName, reportContent))
            .targetConnection(findFieldValue(this.targetConnectionFieldName, reportContent))
            .gcMemory(findFieldValue(this.gcMemoryFieldName, reportContent))
            .treeValidatorState(findFieldValue(this.treeValidatorFieldName, reportContent))
            .allObjectsCount(Integer.parseInt(findFieldValue(this.allObjectsCountFieldName, reportContent)))
            .transformedObjectsCount(Integer.parseInt(findFieldValue(this.transformedObjectsFieldName, reportContent)))
            .appliedObjectsCount(Integer.parseInt(findFieldValue(this.appliedObjectsFieldName, reportContent)))
            .transformerFromErrorReportCount(Integer.parseInt(findFieldValue(this.transformerFromErrorReportFieldName, reportContent)))
            .nullPointerExceptionsCount(Integer.parseInt(findFieldValue(this.nullPointerExceptionsFieldName, reportContent)))
            .projectSize(findFieldValue(this.projectSizeFieldName, reportContent))
            .targetSize(findFieldValue(this.targetSizeFieldName, reportContent))
            .sourceSize(findFieldValue(this.sourceSizeFieldName, reportContent))
            .timeInfo(parseTimeInfo(reportContent))
            .actionItemCounts(parseActionItemCounts(reportContent))
            .build();
    }

    private Set<ActionItemCount> parseActionItemCounts(String reportContent) {
        Set<ActionItemCount> actionItemCounts = new LinkedHashSet<>();
        Arrays.stream(reportContent.split(System.lineSeparator()))
            .dropWhile(line -> !line.contains(this.actionItemCodeFieldName)).skip(1)
            .forEach(line -> {
                String[] array = line.split(COMMA);
                actionItemCounts.add(ActionItemCount.builder().aiName(array[0]).aiCount(Integer.parseInt(array[1])).build());
            });
        return actionItemCounts;
    }

    private Set<TimeInfo> parseTimeInfo(String reportContent) throws ApplicationException {
        Set<TimeInfo> timeInfo = new LinkedHashSet<>();
        for (TimeInfoType timeInfoType : timeInfoTypeRepository.findAll()) {
            timeInfo.add(
                TimeInfo.builder()
                    .timeValue(findFieldValue(timeInfoType.getTimeInfoTypeName(), reportContent))
                    .timeInfoType(timeInfoType)
                    .build());
        }
        return timeInfo;
    }

    private String findFieldValue(String fieldName, String reportContent) throws ApplicationException {
        return Optional.ofNullable(getValue(fieldName, reportContent))
            .orElseThrow(() ->
                new ApplicationException(messageSource.getMessage("", new Object[]{fieldName}, Locale.ENGLISH)));
    }

    private String getValue(String fieldName, String reportContent) {
        Pattern pattern = Pattern.compile(MessageFormat.format(PATTERN, fieldName), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(reportContent);
        return matcher.find()
            ? Optional.ofNullable(matcher.group()).map(line -> line.split(COMMA)[1]).orElse(null)
            : null;
    }
}
