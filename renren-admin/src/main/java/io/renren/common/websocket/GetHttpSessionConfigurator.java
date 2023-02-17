package io.renren.common.websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Map;

/**
 * @tite 用来获取HttpSession对象.
 * @author kolt.yu
 * @date 2023-02-02 上午11:53:50
 */
public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {
    /**
     * @title 该配置可以在不手动传入HttpSession的情况下在websocket服务类中使用
     */
    @Override
    public void modifyHandshake(ServerEndpointConfig sec,
                                HandshakeRequest request,
                                HandshakeResponse response) {
        // 获取httpsession对象
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        // 存放httpsession对象
        Map<String, Object> userProperties = sec.getUserProperties();
        userProperties.put(HttpSession.class.getName(), httpSession);
    }
}
