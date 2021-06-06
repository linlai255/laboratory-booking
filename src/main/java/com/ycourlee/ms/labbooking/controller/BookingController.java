package com.ycourlee.ms.labbooking.controller;

import com.ycourlee.ms.labbooking.model.bo.request.BaseCurrentUserRequest;
import com.ycourlee.ms.labbooking.model.bo.request.BookingRecordRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabBookingRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabCancelBookingRequest;
import com.ycourlee.ms.labbooking.model.vo.BookingRecordVO;
import com.ycourlee.ms.labbooking.service.BookingService;
import com.ycourlee.root.core.domain.context.ApiResponse;
import com.ycourlee.root.core.dto.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yongjiang
 */
@RestController
@RequestMapping("/v1/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PutMapping
    public ApiResponse<Object> booking(@Validated @RequestBody LabBookingRequest request) {
        request.fillCurrentUser();
        return ApiResponse.success(bookingService.booking(request));
    }

    @GetMapping
    public ApiResponse<PageResponse<BookingRecordVO>> bookingRecord(@Validated @RequestBody BookingRecordRequest request) {
        return ApiResponse.success(bookingService.bookingRecord(request));
    }

    @DeleteMapping("/{id:[0-9]+}")
    public ApiResponse<Object> cancelBooking(@PathVariable Integer id) {
        bookingService.cancelBooking(id, new BaseCurrentUserRequest().fillCurrentUser());
        return ApiResponse.success();
    }

    @DeleteMapping
    public ApiResponse<Object> cancelBooking(@Validated @RequestBody LabCancelBookingRequest request) {
        request.fillCurrentUser();
        bookingService.cancelBooking(request);
        return ApiResponse.success();
    }
}
