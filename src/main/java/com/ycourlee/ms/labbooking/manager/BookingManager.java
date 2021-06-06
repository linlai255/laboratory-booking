package com.ycourlee.ms.labbooking.manager;

import com.github.pagehelper.PageHelper;
import com.ycourlee.ms.labbooking.enums.EBookingRecordStatus;
import com.ycourlee.ms.labbooking.mapper.BookingRecordMapper;
import com.ycourlee.ms.labbooking.mapper.BookingRecordTimeMapper;
import com.ycourlee.ms.labbooking.model.bo.request.BaseCurrentUserRequest;
import com.ycourlee.ms.labbooking.model.bo.request.BookingRecordRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabBookingRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabCancelBookingRequest;
import com.ycourlee.ms.labbooking.model.entity.BookingRecordEntity;
import com.ycourlee.ms.labbooking.model.entity.BookingRecordTimeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yongjiang
 */
@Component
public class BookingManager {

    @Autowired
    private BookingRecordMapper     bookingRecordMapper;
    @Autowired
    private BookingRecordTimeMapper bookingRecordTimeMapper;

    @Transactional(rollbackFor = Exception.class)
    public int saveBooking(LabBookingRequest request) {
        int bookingRecordId = saveBookingRecord(request);
        saveBookingTime(request, bookingRecordId);
        return bookingRecordId;
    }

    public int saveBookingRecord(LabBookingRequest request) {
        BookingRecordEntity record = buildBookingRecord(request);
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

    public BookingRecordEntity buildBookingRecord(LabBookingRequest request) {
        BookingRecordEntity entity = new BookingRecordEntity();
        entity.setLabId(request.getLabId());
        entity.setCourseId(request.getCourseId());
        entity.setMemo("");
        entity.setCreateUserId(request.getUserId());
        entity.setCreateUsername(request.getUsername());
        entity.setUpdateUserId(request.getUserId());
        entity.setUpdateUsername(request.getUsername());
        return entity;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveCancelBooking(LabCancelBookingRequest request) {
        bookingRecordTimeMapper.batchRemoveByIdList(request.getIdList());
        BookingRecordEntity record = new BookingRecordEntity();
        record.setId(request.getBookingRecordId());
        record.setUpdateUserId(request.getUserId());
        record.setUpdateUsername(request.getUsername());
        bookingRecordMapper.updateByPrimaryKey(record);
        int count = bookingRecordTimeMapper.countByBookingRecordId(request.getBookingRecordId());
        if (count > 0) {
            return;
        }
        bookingRecordMapper.removeByPrimaryKey(request.getBookingRecordId());
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveCancelBooking(Integer id, BaseCurrentUserRequest request) {
        bookingRecordMapper.removeAndUpdateUserByPrimaryKey(id, request.getUserId(), request.getUsername());
        bookingRecordTimeMapper.removeByBookingRecordId(id);
    }

    public List<BookingRecordEntity> list(BookingRecordRequest request, boolean needPaging) {
        if (needPaging) {
            PageHelper.startPage(request.getPage(), request.getPageSize());
        }
        return bookingRecordMapper.listByCourseId(request.getCourseId());
    }
}
