package com.ycourlee.ms.labbooking.service;

import com.ycourlee.ms.labbooking.model.bo.request.BaseCurrentUserRequest;
import com.ycourlee.ms.labbooking.model.bo.request.BookingRecordRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabBookingRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabCancelBookingRequest;
import com.ycourlee.ms.labbooking.model.vo.BookingRecordVO;
import com.ycourlee.root.core.dto.PageResponse;

/**
 * @author yongjiang
 */
public interface BookingService {

    Integer booking(LabBookingRequest request);

    PageResponse<BookingRecordVO> bookingRecord(BookingRecordRequest request);

    void cancelBooking(LabCancelBookingRequest request);

    void cancelBooking(Integer id, BaseCurrentUserRequest request);
}
