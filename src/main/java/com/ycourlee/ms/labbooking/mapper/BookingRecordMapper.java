package com.ycourlee.ms.labbooking.mapper;

import com.ycourlee.ms.labbooking.model.entity.BookingRecordEntity;

/**
 * @author yongjiang
 */
public interface BookingRecordMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(BookingRecordEntity record);

    int insertSelective(BookingRecordEntity record);

    BookingRecordEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookingRecordEntity record);

    int updateByPrimaryKey(BookingRecordEntity record);
}