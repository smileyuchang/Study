package io.renren.common.websocket;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kolt.yu
 * @Desc 数据的响应类.
 * @date 2023-02-02 下午13:53:15
 */
public class Result {

    /**
     * 响应标志，成功/失败
     */
    private boolean flag;

    /**
     * 响应给前台的消息
     */
    private String message;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 日期时间
     */
    private String dateStr = "";

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public void setDateStr() {
        this.dateStr = dateFormat(new Date());
    }

    public void setDateStr(Date date) {
        this.dateStr = dateFormat(date);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result(boolean flag, String message) {
        super();
        this.flag = flag;
        this.message = message;
    }

    public Result() {
        super();
    }

    @Override
    public String toString() {
        return "Result [flag=" + flag + ", message=" + message + "]";
    }

    private final static Logger LOGGER = LogManager.getLogger(Result.class);

    public static String dateFormat(Date date) {
        String str = "";
        if (null == date || StringUtils.isBlank(String.valueOf(date))) {
            date = new Date();
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            str = format.format(date);
        } catch (Exception e) {
            LOGGER.info("调用ChatEndpoint类中的dateFormat方法时出错...{}", e.getMessage());
        }
        return str;
    }
}