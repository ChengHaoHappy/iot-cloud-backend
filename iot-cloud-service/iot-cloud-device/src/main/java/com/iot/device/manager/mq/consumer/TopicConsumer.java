package com.iot.device.manager.mq.consumer;

import com.iot.common.core.mq.MqMessage;
import com.iot.device.dto.DeviceDto;
import com.iot.device.util.JacksonUtil;
import com.iot.websocket.dto.MsgDto;
import com.iot.websocket.feign.RemoteWebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by huqiaoqian on 2020/10/28
 */
@Slf4j
@Service
public class TopicConsumer {

    @Autowired
    private RemoteWebSocketService remoteWebSocketService;

    public void handlerSendMqMsg(String body, String topicName, String tags, String keys){
        log.info("handlerSendMqMsg:body={},topicName={},tags={},keys={}",body,topicName,tags,keys);
        DeviceDto deviceDto;
        try {
            deviceDto = JacksonUtil.parseJson(body, DeviceDto.class);
        } catch (IOException e) {
            log.error("发送短信MQ出现异常={}", e.getMessage(), e);
            throw new IllegalArgumentException("JSON转换异常", e);
        }
        if(deviceDto==null){
            log.error("消息体为空");
            return;
        }
        MsgDto<DeviceDto> msgDto = new MsgDto<>();
        msgDto.setMsg(deviceDto);
        msgDto.setMsgType("Device");
        msgDto.setId(deviceDto.getDeviceName());
        remoteWebSocketService.createWebsocketMsg(msgDto);
        //TODO:将设备数据传给规则，进行处理
    }
}
