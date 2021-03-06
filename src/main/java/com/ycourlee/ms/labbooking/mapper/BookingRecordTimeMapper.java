package com.ycourlee.ms.labbooking.mapper;

import com.ycourlee.ms.labbooking.model.entity.BookingRecordTimeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yongjiang
 */
public interface BookingRecordTimeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(BookingRecordTimeEntity record);

    int insertSelective(BookingRecordTimeEntity record);

    BookingRecordTimeEntity selectByPrimaryKey(Integer id);

    BookingRecordTimeEntity selectByPrimaryKeyEvenIfRemoved(Integer id);

    int updateByPrimaryKeySelective(BookingRecordTimeEntity record);

    int updateByPrimaryKey(BookingRecordTimeEntity record);

    List<BookingRecordTimeEntity> listByBookingRecordIdList(@Param("bookingRecordIdList") List<Integer> bookingRecordIdList);

    int batchInsertFcl(@Param("entityList") List<BookingRecordTimeEntity> entityList);

    int batchRemoveByIdList(@Param("idList") List<Integer> idList);

    int countByBookingRecordId(Integer bookingRecordId);

    int removeByBookingRecordId(Integer bookingRecordId);
}