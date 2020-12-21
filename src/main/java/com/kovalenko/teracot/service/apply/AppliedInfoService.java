package com.kovalenko.teracot.service.apply;

import com.kovalenko.teracot.dto.apply.AppliedInfoDTO;
import java.util.List;

public interface AppliedInfoService {

    List<AppliedInfoDTO> getAppliedInfo(long testTypeID);
}
