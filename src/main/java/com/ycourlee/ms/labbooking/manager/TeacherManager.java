package com.ycourlee.ms.labbooking.manager;

import com.ycourlee.ms.labbooking.mapper.TeacherMapper;
import com.ycourlee.ms.labbooking.model.entity.TeacherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yongjiang
 */
@Component
public class TeacherManager {

    @Autowired
    private TeacherMapper teacherMapper;

    public TeacherEntity get(Integer id) {
        return teacherMapper.selectByPrimaryKey(id);
    }

    public String nameOf(Integer id) {
        TeacherEntity entity = teacherMapper.selectByPrimaryKeyEvenIfRemoved(id);
        return entity == null ? "" : entity.getName();
    }
}
