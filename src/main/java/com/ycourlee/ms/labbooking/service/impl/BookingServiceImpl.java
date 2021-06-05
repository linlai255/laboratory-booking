package com.ycourlee.ms.labbooking.service.impl;

import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.manager.BookingManager;
import com.ycourlee.ms.labbooking.manager.CourseManager;
import com.ycourlee.ms.labbooking.manager.LaboratoryManager;
import com.ycourlee.ms.labbooking.model.bo.request.BaseCurrentUserRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabBookingRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabCancelBookingRequest;
import com.ycourlee.ms.labbooking.model.entity.CourseEntity;
import com.ycourlee.ms.labbooking.model.entity.LabEntity;
import com.ycourlee.ms.labbooking.service.BookingService;
import com.ycourlee.ms.labbooking.util.BizAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yongjiang
 */
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingManager    bookingManager;
    @Autowired
    private LaboratoryManager laboratoryManager;
    @Autowired
    private CourseManager     courseManager;

    @Override
    public Integer booking(LabBookingRequest request) {
        BizAssert.that(request.getEndWeekNo().compareTo(request.getBeginWeekNo()) >= 0, Errors.REQUEST_PARAMETER_VALIDATION_ERROR);
        BizAssert.that(request.getEndSectionNo().compareTo(request.getBeginSectionNo()) >= 0, Errors.REQUEST_PARAMETER_VALIDATION_ERROR);

        LabEntity labEntity = laboratoryManager.get(request.getLabId());
        BizAssert.that(labEntity != null, Errors.LAB_NOT_FOUND);
        CourseEntity courseEntity = courseManager.get(request.getCourseId());
        BizAssert.that(courseEntity != null, Errors.COURSE_NOT_FOUND);
        return bookingManager.saveBooking(request);
    }

    @Override
    public void cancelBooking(LabCancelBookingRequest request) {
        bookingManager.saveCancelBooking(request);
    }

    @Override
    public void cancelBooking(Integer id, BaseCurrentUserRequest request) {
        bookingManager.saveCancelBooking(id, request);
    }
}
