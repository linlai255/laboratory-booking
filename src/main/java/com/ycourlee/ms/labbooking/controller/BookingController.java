package com.ycourlee.ms.labbooking.controller;

import com.ycourlee.ms.labbooking.model.bo.request.BaseCurrentUserRequest;
import com.ycourlee.ms.labbooking.model.bo.request.BookingRecordRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabBookingRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabCancelBookingRequest;
import com.ycourlee.ms.labbooking.model.vo.BookingRecordVO;
import com.ycourlee.ms.labbooking.service.BookingService;
import com.ycourlee.root.core.domain.context.ApiResponse;
import com.ycourlee.root.core.dto.PageResponse;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("预约")
    @PutMapping
    public ApiResponse<Object> booking(@Validated @RequestBody LabBookingRequest request) {
        request.fillCurrentUser();
        return ApiResponse.success(bookingService.booking(request));
    }

    @ApiOperation("预约记录")
    @GetMapping
    public ApiResponse<PageResponse<BookingRecordVO>> bookingRecord(@Validated BookingRecordRequest request) {
        return ApiResponse.success(bookingService.bookingRecord(request));
    }

    @ApiOperation("取消预约")
    @DeleteMapping("/{id:[0-9]+}")
    public ApiResponse<Object> cancelBooking(@PathVariable Integer id) {
        bookingService.cancelBooking(id, new BaseCurrentUserRequest().fillCurrentUser());
        return ApiResponse.success();
    }

    @ApiOperation("部分取消预约")
    @DeleteMapping
    public ApiResponse<Object> cancelBooking(@Validated @RequestBody LabCancelBookingRequest request) {
        request.fillCurrentUser();
        bookingService.cancelBooking(request);
        return ApiResponse.success();
    }
}
