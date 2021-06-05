package com.ycourlee.ms.labbooking.manager;

import com.github.pagehelper.PageHelper;
import com.ycourlee.ms.labbooking.enums.ELabStatus;
import com.ycourlee.ms.labbooking.mapper.BookingRecordMapper;
import com.ycourlee.ms.labbooking.mapper.BookingRecordTimeMapper;
import com.ycourlee.ms.labbooking.mapper.LabMapper;
import com.ycourlee.ms.labbooking.model.bo.request.LabSaveRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabSearchRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabUpdateRequest;
import com.ycourlee.ms.labbooking.model.bo.response.LabDetailResponse;
import com.ycourlee.ms.labbooking.model.entity.BookingRecordTimeEntity;
import com.ycourlee.ms.labbooking.model.entity.LabEntity;
import com.ycourlee.ms.labbooking.model.vo.CodeNameVO;
import com.ycourlee.ms.labbooking.model.vo.LabSearchVO;
import com.ycourlee.ms.labbooking.util.BizAssert;
import com.ycourlee.root.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yongjiang
 */
@Component
public class LaboratoryManager {

    @Autowired
    private LabMapper               labMapper;
    @Autowired
    private BookingRecordMapper     bookingRecordMapper;
    @Autowired
    private BookingRecordTimeMapper bookingRecordTimeMapper;

    public int saveLab(LabSaveRequest request) {
        LabEntity record = buildLabEntity(request);
        labMapper.insertSelective(record);
        return record.getId();
    }

    public boolean haveNotFinishedBooking(Integer labId) {
        BizAssert.notNull(labId);
        List<Integer> recordIdList = bookingRecordMapper.listIdByLabId(labId);
        if (CollectionUtil.isEmpty(recordIdList)) {
            return false;
        }
        List<BookingRecordTimeEntity> bookingRecordEntityList = bookingRecordTimeMapper.listByBookingRecordIdList(recordIdList);
        if (CollectionUtil.isEmpty(bookingRecordEntityList)) {
            return false;
        }
        return true;
    }

    public LabEntity get(Integer id) {
        return labMapper.selectByPrimaryKey(id);
    }

    public List<LabEntity> list(LabSearchRequest request, boolean needPaging) {
        if (needPaging) {
            PageHelper.startPage(request.getPage(), request.getPageSize());
        }
        return labMapper.listOrderedUpdateTimeByDclName(request.getName());
    }

    public List<LabSearchVO> buildSearchVoList(List<LabEntity> entityList) {
        if (CollectionUtil.isEmpty(entityList)) {
            return Collections.emptyList();
        }
        List<LabSearchVO> labVoList = new ArrayList<>(entityList.size());
        for (LabEntity entity : entityList) {
            LabSearchVO labVO = new LabSearchVO();
            labVO.setId(entity.getId());
            labVO.setName(entity.getName());
            labVO.setMaxCapacity(entity.getMaxCapacity());
            labVO.setInstrumentAmount(entity.getInstrumentAmount());
            labVO.setStatus(CodeNameVO.builder().code(entity.getStatus()).name(ELabStatus.getNameByCode(entity.getStatus())).build());
            labVO.setLocation(entity.getLocation());
            labVO.setUpdateTime(entity.getUpdateTime());
            labVO.setUpdateUsername(entity.getUpdateUsername());
            labVoList.add(labVO);
        }
        return labVoList;
    }

    public void update(LabUpdateRequest request) {
        LabEntity entity = new LabEntity();
        entity.setId(request.getId());
        entity.setName(request.getName());
        entity.setMaxCapacity(request.getMaxCapacity());
        entity.setInstrumentAmount(request.getInstrumentAmount());
        entity.setInstrumentMemo(request.getInstrumentMemo());
        entity.setLocation(request.getLocation());
        entity.setOpenTime(request.getOpenTime());
        entity.setCloseTime(request.getCloseTime());
        entity.setMemo(request.getMemo());
        labMapper.updateByPrimaryKeySelective(entity);
    }

    public void remove(Integer id) {
        labMapper.removeByPrimaryKey(id);
    }

    public LabEntity buildLabEntity(LabSaveRequest request) {
        LabEntity record = new LabEntity();
        record.setName(request.getName());
        record.setMaxCapacity(request.getMaxCapacity());
        record.setOpenTime(request.getOpenTime());
        record.setCloseTime(request.getCloseTime());
        record.setInstrumentAmount(request.getInstrumentAmount());
        record.setInstrumentMemo(request.getInstrumentMemo());
        record.setMemo(request.getMemo());
        record.setLocation(request.getLocation());
        record.setCreateUserId(request.getUserId());
        record.setUpdateUserId(request.getUserId());
        record.setCreateUsername(request.getUsername());
        record.setUpdateUsername(request.getUsername());
        return record;
    }

    public LabDetailResponse buildLabDetailResponse(LabEntity entity) {
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
