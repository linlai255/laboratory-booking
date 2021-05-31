package com.ycourlee.ms.labbooking.service;

import com.ycourlee.ms.labbooking.model.bo.request.LabCreateRequest;

/**
 * @author yongjiang
 */
public interface LaboratoryService {

    Integer createLab(LabCreateRequest request);

    Integer deleteLab(Integer id);
}
