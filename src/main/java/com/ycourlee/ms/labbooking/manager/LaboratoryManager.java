package com.ycourlee.ms.labbooking.manager;

import com.ycourlee.ms.labbooking.mapper.BookingRecordMapper;
import com.ycourlee.ms.labbooking.mapper.BookingRecordTimeMapper;
import com.ycourlee.ms.labbooking.mapper.LabMapper;
import com.ycourlee.ms.labbooking.model.bo.request.LabCreateRequest;
import com.ycourlee.ms.labbooking.model.entity.BookingRecordTimeEntity;
import com.ycourlee.ms.labbooking.model.entity.LabEntity;
import com.ycourlee.ms.labbooking.util.BizAssert;
import com.ycourlee.root.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        // todo 添加新表 week_no, section_no, start_time, end_time.
        return true;
    }
}
