package com.ycourlee.ms.labbooking.service.impl;

import com.ycourlee.ms.labbooking.manager.LaboratoryManager;
import com.ycourlee.ms.labbooking.model.bo.request.LabCreateRequest;
import com.ycourlee.ms.labbooking.service.LaboratoryService;
import com.ycourlee.ms.labbooking.util.BizAssert;
import com.ycourlee.ms.labbooking.util.RegexUtil;
import com.ycourlee.root.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yongjiang
 */
@Service
public class LaboratoryServiceImpl implements LaboratoryService {

    @Autowired
    private LaboratoryManager laboratoryManager;

    @Override
    public Integer createLab(LabCreateRequest request) {
        if (StringUtil.isNotEmpty(request.getOpenTime())) {
            BizAssert.that(RegexUtil.isHourMinute(request.getOpenTime()));
        }
        if (StringUtil.isNotEmpty(request.getCloseTime())) {
            BizAssert.that(RegexUtil.isHourMinute(request.getCloseTime()));
        }
        return laboratoryManager.saveLab(request);
    }

    @Override
    public Integer deleteLab(Integer id) {
        BizAssert.impossible(laboratoryManager.haveNotFinishedBooking(id));


        return null;
    }
}
