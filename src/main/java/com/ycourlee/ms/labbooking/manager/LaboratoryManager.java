package com.ycourlee.ms.labbooking.manager;

import com.github.pagehelper.PageHelper;
import com.ycourlee.ms.labbooking.enums.EBookingRecordStatus;
import com.ycourlee.ms.labbooking.enums.ELabStatus;
import com.ycourlee.ms.labbooking.mapper.BookingRecordMapper;
import com.ycourlee.ms.labbooking.mapper.BookingRecordTimeMapper;
import com.ycourlee.ms.labbooking.mapper.LabMapper;
import com.ycourlee.ms.labbooking.model.bo.request.LabBookingRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabCreateRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabSearchRequest;
import com.ycourlee.ms.labbooking.model.entity.BookingRecordEntity;
import com.ycourlee.ms.labbooking.model.entity.BookingRecordTimeEntity;
import com.ycourlee.ms.labbooking.model.entity.CourseEntity;
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

    public int saveLab(LabCreateRequest request) {
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

    public List<LabEntity> get(LabSearchRequest request, boolean needPaging) {
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

    public int remove(Integer id) {
        return labMapper.removeByPrimaryKey(id);
    }

    public int saveBookingRecord(LabBookingRequest request, LabEntity labEntity, CourseEntity courseEntity) {
        BookingRecordEntity record = buildBookingRecord(request, labEntity.getName(), courseEntity.getName());
        bookingRecordMapper.insertSelective(record);
        return record.getId();
    }

    public void saveBookingTime(LabBookingRequest request, int bookingRecordId) {
        List<BookingRecordTimeEntity> entityList = new ArrayList<>();
        for (int i = request.getBeginWeekNo(); i <= request.getEndWeekNo(); i++) {
            for (int j = request.getBeginSectionNo(); j <= request.getEndSectionNo(); j++) {
                BookingRecordTimeEntity entity = new BookingRecordTimeEntity();
                entity.setWeekNo(i);
                entity.setSectionNo(j);
                entity.setBookingRecordId(bookingRecordId);
                entity.setStatus(EBookingRecordStatus.WAITING_CLASS.getCode());
                entityList.add(entity);
            }
        }
        bookingRecordTimeMapper.batchInsertFcl(entityList);
    }

    public BookingRecordEntity buildBookingRecord(LabBookingRequest request, String labName, String courseName) {
        BookingRecordEntity entity = new BookingRecordEntity();
        entity.setLabId(request.getLabId());
        entity.setLabName(labName);
        entity.setCourseId(request.getCourseId());
        entity.setCourseName(courseName);
        entity.setMemo("");
        entity.setCreateUserId(request.getUserId());
        entity.setCreateUsername(request.getUsername());
        entity.setUpdateUserId(request.getUserId());
        entity.setUpdateUsername(request.getUsername());
        return entity;
    }

    public LabEntity buildLabEntity(LabCreateRequest request) {
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
}
