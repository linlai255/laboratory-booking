package com.ycourlee.ms.labbooking.service.impl;

import com.github.pagehelper.PageInfo;
import com.ycourlee.ms.labbooking.manager.LaboratoryManager;
import com.ycourlee.ms.labbooking.model.bo.request.LabSaveRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabSearchRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabUpdateRequest;
import com.ycourlee.ms.labbooking.model.bo.response.LabDetailResponse;
import com.ycourlee.ms.labbooking.model.entity.LabEntity;
import com.ycourlee.ms.labbooking.model.vo.LabSearchVO;
import com.ycourlee.ms.labbooking.service.LaboratoryService;
import com.ycourlee.ms.labbooking.util.BizAssert;
import com.ycourlee.ms.labbooking.util.RegexUtil;
import com.ycourlee.root.core.dto.PageResponse;
import com.ycourlee.root.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yongjiang
 */
@Service
public class LaboratoryServiceImpl implements LaboratoryService {

    @Autowired
    private LaboratoryManager laboratoryManager;

    @Override
    public PageResponse<LabSearchVO> search(LabSearchRequest request) {
        List<LabEntity> entityList = laboratoryManager.list(request, true);
        return new PageResponse<>(request.getPage(), request.getPageSize(), new PageInfo<>().getTotal(), laboratoryManager.buildSearchVoList(entityList));
    }

    @Override
    public Integer save(LabSaveRequest request) {
        if (StringUtil.isNotEmpty(request.getOpenTime())) {
            BizAssert.that(RegexUtil.isHourMinute(request.getOpenTime()));
        }
        if (StringUtil.isNotEmpty(request.getCloseTime())) {
            BizAssert.that(RegexUtil.isHourMinute(request.getCloseTime()));
        }
        return laboratoryManager.saveLab(request);
    }

    @Override
    public void update(LabUpdateRequest request) {
        laboratoryManager.update(request);
    }

    @Override
    public LabDetailResponse get(Integer id) {
        return laboratoryManager.buildLabDetailResponse(laboratoryManager.get(id));
    }

    @Override
    public void delete(Integer id) {
        BizAssert.impossible(laboratoryManager.haveNotFinishedBooking(id));
        laboratoryManager.remove(id);
    }
}
