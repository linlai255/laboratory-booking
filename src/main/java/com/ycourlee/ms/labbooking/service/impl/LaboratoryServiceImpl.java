package com.ycourlee.ms.labbooking.service.impl;

import com.ycourlee.ms.labbooking.enums.ELabStatus;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.manager.CourseManager;
import com.ycourlee.ms.labbooking.manager.LaboratoryManager;
import com.ycourlee.ms.labbooking.model.bo.request.LabBookingRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabSaveRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabSearchRequest;
import com.ycourlee.ms.labbooking.model.bo.response.LabDetailResponse;
import com.ycourlee.ms.labbooking.model.entity.CourseEntity;
import com.ycourlee.ms.labbooking.model.entity.LabEntity;
import com.ycourlee.ms.labbooking.model.vo.CodeNameVO;
import com.ycourlee.ms.labbooking.model.vo.LabSearchVO;
import com.ycourlee.ms.labbooking.service.LaboratoryService;
import com.ycourlee.ms.labbooking.util.BizAssert;
import com.ycourlee.ms.labbooking.util.RegexUtil;
import com.ycourlee.root.core.dto.PageResponse;
import com.ycourlee.root.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yongjiang
 */
@Service
public class LaboratoryServiceImpl implements LaboratoryService {

    @Autowired
    private LaboratoryManager laboratoryManager;
    @Autowired
    private CourseManager     courseManager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void booking(LabBookingRequest request) {
        BizAssert.that(request.getEndWeekNo().compareTo(request.getBeginWeekNo()) >= 0, Errors.REQUEST_PARAMETER_VALIDATION_ERROR);
        BizAssert.that(request.getEndSectionNo().compareTo(request.getBeginSectionNo()) >= 0, Errors.REQUEST_PARAMETER_VALIDATION_ERROR);

        LabEntity labEntity = laboratoryManager.get(request.getLabId());
        BizAssert.that(labEntity != null, Errors.LAB_NOT_FOUND);
        CourseEntity courseEntity = courseManager.get(request.getCourseId());
        BizAssert.that(courseEntity != null, Errors.COURSE_NOT_FOUND);

        int bookingRecordId = laboratoryManager.saveBookingRecord(request, labEntity, courseEntity);
        laboratoryManager.saveBookingTime(request, bookingRecordId);
    }

    @Override
    public PageResponse<LabSearchVO> search(LabSearchRequest request) {
        List<LabEntity> entityList = laboratoryManager.get(request, true);
        return new PageResponse<>(request.getPage(), request.getPageSize(), entityList.size(), laboratoryManager.buildSearchVoList(entityList));
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
    public LabDetailResponse get(Integer id) {
        return buildLabDetailResponse(laboratoryManager.get(id));
    }

    @Override
    public Integer delete(Integer id) {
        BizAssert.impossible(laboratoryManager.haveNotFinishedBooking(id));
        return laboratoryManager.remove(id);
    }

    private LabDetailResponse buildLabDetailResponse(LabEntity entity) {
        LabDetailResponse response = new LabDetailResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setMaxCapacity(entity.getMaxCapacity());
        response.setInstrumentAmount(entity.getInstrumentAmount());
        response.setInstrumentMemo(entity.getInstrumentMemo());
        response.setStatus(CodeNameVO.builder().code(entity.getStatus()).name(ELabStatus.getNameByCode(entity.getStatus())).build());
        response.setLocation(entity.getLocation());
        response.setOpenTime(entity.getOpenTime());
        response.setCloseTime(entity.getCloseTime());
        response.setMemo(entity.getMemo());
        response.setUpdateTime(entity.getUpdateTime());
        response.setUpdateUserId(entity.getUpdateUserId());
        response.setUpdateUsername(entity.getUpdateUsername());
        return response;
    }
}
