package io.renren.common.websocket;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author kolt.yu
 * 服务端发送给客户端的消息.
 * @date 2023-02-02 下午13:53:15
 */
public class ResultMessage {
    /**
     * 是否是系统消息
     */
    private Boolean systemMsgFlag;
    /**
     * 发送方姓名
     */
    private String fromName;
    /**
     * 发送方id
     */
    private String fromId;
    /**
     * 接收方姓名
     */
    private String toName;
    /**
     * 接收方id
     */
    private String toId;

    /**
     * 发送的数据
     */
    private Object message;
    /**
     * 接收到消息的日期时间
     */
    private String dateStr;
    /**
     * 存储对象数据
     */
    private Map<?, ?> map;

    public Map<?, ?> getMap() {
        return map;
    }

    public void setMap(Map<?, ?> map) {
        this.map = map;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getDateStr(Date date) {
        if (date == null) {
            date = new Date();
        }
        return dateFormat(date);
    }

    public void setDateStr() {
        this.dateStr = dateFormat(new Date());
    }

    public void setDateStr(Date date) {
        if (date == null) {
            date = new Date();
        }
        this.dateStr = dateFormat(date);
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public Boolean getSystemMsgFlag() {
        return systemMsgFlag;
    }

    public void setSystemMsgFlag(Boolean systemMsgFlag) {
        this.systemMsgFlag = systemMsgFlag;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public ResultMessage(Boolean systemMsgFlag, String fromName, Object message) {
        super();
        this.systemMsgFlag = systemMsgFlag;
        this.fromName = fromName;
        this.message = message;
    }

    public ResultMessage() {
        super();
    }

    @Override
    public String toString() {
        return "ResultMessage [systemMsgFlag=" + systemMsgFlag + ", fromName=" + fromName + ", message=" + message
                + "]";
    }

    private final static Logger LOGGER = LogManager.getLogger(ResultMessage.class);

    public static String dateFormat(Date date) {
        String str = "";
        if (null == date || StringUtils.isBlank(String.valueOf(date))) {
            date = new Date();
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            str = format.format(date);
        } catch (Exception e) {
            LOGGER.info("调用ResultMessage类中的dateFormat方法时出错...{}", e.getMessage());
        }
        return str;
    }
}