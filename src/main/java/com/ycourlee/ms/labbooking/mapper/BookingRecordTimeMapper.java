package com.ycourlee.ms.labbooking.mapper;

import com.ycourlee.ms.labbooking.model.entity.BookingRecordTimeEntity;

/**
 * @author yongjiang
 */
public interface BookingRecordTimeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(BookingRecordTimeEntity record);

    int insertSelective(BookingRecordTimeEntity record);

    BookingRecordTimeEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookingRecordTimeEntity record);

    int updateByPrimaryKey(BookingRecordTimeEntity record);
}