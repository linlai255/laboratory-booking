package com.ycourlee.ms.labbooking.service;

import com.ycourlee.ms.labbooking.model.bo.request.LabBookingRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabSaveRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabSearchRequest;
import com.ycourlee.ms.labbooking.model.bo.response.LabDetailResponse;
import com.ycourlee.ms.labbooking.model.vo.LabSearchVO;
import com.ycourlee.root.core.dto.PageResponse;

/**
 * @author yongjiang
 */
public interface LaboratoryService {

    void booking(LabBookingRequest request);

    PageResponse<LabSearchVO> search(LabSearchRequest request);

    Integer save(LabSaveRequest request);

    LabDetailResponse get(Integer id);

    Integer delete(Integer id);
}
