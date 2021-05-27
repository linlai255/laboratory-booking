package com.ycourlee.ms.labbooking.auth.filter;

import com.ycourlee.ms.labbooking.model.vo.AdminVO;
import com.ycourlee.ms.labbooking.model.vo.TeacherVO;
import lombok.Builder;

/**
 * @author yongjiang
 */
@Builder
public class Context {

    private static final ThreadLocal<Context> VAR = new ThreadLocal<>();

    public void clean() {
        VAR.remove();
    }

    public void setContext(Context context) {
        VAR.remove();
        VAR.set(context);
    }

    public Context threadInstance() {
        return VAR.get();
    }

    private Context() {}

    private Context(Integer userId, String username, String name, String phone, Byte refId, AdminVO adminVO, TeacherVO teacherVO) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.refId = refId;
        this.adminVO = adminVO;
        this.teacherVO = teacherVO;
        this.setContext(this);
    }

    private Integer   userId;
    /**
     * 用户名
     */
    private String    username;
    /**
     * 真实姓名
     */
    private String    name;
    private String    phone;
    private Byte      refId;
    private AdminVO   adminVO;
    private TeacherVO teacherVO;
}
