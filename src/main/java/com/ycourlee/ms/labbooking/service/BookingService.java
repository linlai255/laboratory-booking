package com.ycourlee.ms.labbooking.service;

import com.ycourlee.ms.labbooking.model.bo.request.BaseCurrentUserRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabBookingRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabCancelBookingRequest;

/**
 * @author yongjiang
 */
public interface BookingService {

    Integer booking(LabBookingRequest request);

    void cancelBooking(LabCancelBookingRequest request);

    void cancelBooking(Integer id, BaseCurrentUserRequest request);
}
