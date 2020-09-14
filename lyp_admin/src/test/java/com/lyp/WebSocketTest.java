package com.lyp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws/asset")
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class WebSocketTest {
    @Test
    public void WebSocketTestStart(){
        log.info("WebSocketTestStart");
    }
}
