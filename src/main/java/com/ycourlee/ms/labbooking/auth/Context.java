package com.ycourlee.ms.labbooking.auth;

import com.ycourlee.ms.labbooking.model.bo.AdminBO;
import com.ycourlee.ms.labbooking.model.bo.TeacherBO;
import lombok.Builder;

/**
 * @author yongjiang
 */
@Builder
public class Context {

    private static final ThreadLocal<Context> VAR = new ThreadLocal<>();
    private              Integer              userId;
    /**
     * 用户名
     */
    private              String               username;
    /**
     * 真实姓名
     */
    private              String               name;
    private              String               phone;
    private              AdminBO              adminBO;
    private              TeacherBO            teacherBO;

    private Context() {}

    private Context(Integer userId, String username, String name, String phone, AdminBO adminBO, TeacherBO teacherBO) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.adminBO = adminBO;
        this.teacherBO = teacherBO;
        this.setContext(this);
    }

    public static void clean() {
        VAR.remove();
    }

    public void setContext(Context context) {
        VAR.remove();
        VAR.set(context);
    }

    public Context threadInstance() {
        return VAR.get();
    }
}
