package com.kovalenko.teracot.service.apply.impl;

import com.kovalenko.teracot.dto.apply.AppliedInfoDTO;
import com.kovalenko.teracot.service.apply.AppliedInfoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppliedInfoServiceImpl implements AppliedInfoService {

    private final MessageSource messageSource;

    @Override
    public List<AppliedInfoDTO> getAppliedInfo(long testTypeID) {
        return null;
    }
}
