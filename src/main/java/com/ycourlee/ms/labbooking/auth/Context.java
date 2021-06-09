package com.ycourlee.ms.labbooking.auth;

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
    private              String               nickname;
    /**
     * 真实姓名
     */
    private              String               name;
    private              String               phone;
    private              Integer              type;
    private              Integer              refId;

    private Context() {}

    private Context(Integer userId, String username, String nickname, String name, String phone, Integer type, Integer refId) {
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.name = name;
        this.phone = phone;
        this.type = type;
        this.refId = refId;
        setContext(this);
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

    public static String getNickname() {
        return threadInstance().nickname;
    }

    public static Integer getRefId() {
        return threadInstance().refId;
    }

    public void setContext(Context context) {
        VAR.remove();
        VAR.set(context);
    }
}
