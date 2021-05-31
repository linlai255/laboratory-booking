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
    private              Integer              type;
    private              AdminBO              adminBO;
    private              TeacherBO            teacherBO;

    private Context() {}

    private Context(Integer userId, String username, String name, String phone, Integer type, AdminBO adminBO, TeacherBO teacherBO) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.type = type;
        this.adminBO = adminBO;
        this.teacherBO = teacherBO;
        this.setContext(this);
    }

    public static void clean() {
        VAR.remove();
    }

    public static Context threadInstance() {
        return VAR.get();
    }

    public static Integer getUserId() {
        return threadInstance().userId;
    }

    public static String getUsername() {
        return threadInstance().username;
    }

    public static String getName() {
        return threadInstance().name;
    }

    public static String getPhone() {
        return threadInstance().phone;
    }

    public static Integer getType() {
        return threadInstance().type;
    }

    public static AdminBO getAdminBO() {
        return threadInstance().adminBO;
    }

    public static TeacherBO getTeacherBO() {
        return threadInstance().teacherBO;
    }

    public void setContext(Context context) {
        VAR.remove();
        VAR.set(context);
    }
}
