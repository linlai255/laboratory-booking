package com.ycourlee.ms.labbooking.service;

import com.ycourlee.ms.labbooking.model.bo.request.LabSaveRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabSearchRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabUpdateRequest;
import com.ycourlee.ms.labbooking.model.bo.response.LabDetailResponse;
import com.ycourlee.ms.labbooking.model.vo.LabSearchVO;
import com.ycourlee.root.core.dto.PageResponse;

/**
 * @author yongjiang
 */
public interface LaboratoryService {


    Integer save(LabSaveRequest request);

    void update(LabUpdateRequest request);

    LabDetailResponse get(Integer id);

    PageResponse<LabSearchVO> search(LabSearchRequest request);

    void delete(Integer id);
}
