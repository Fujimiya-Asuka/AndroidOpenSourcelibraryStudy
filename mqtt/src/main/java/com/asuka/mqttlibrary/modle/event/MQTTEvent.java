package com.asuka.mqttlibrary.modle.event;

import org.eclipse.paho.client.mqttv3.MqttMessage;
/**
 *  <pre>
 *  desc:   EventBus MQTT消息事件
 *  Author:  XuZhenHui
 *  Time:  2021/12/25 11:14
 *  </pre>
 */
public class MQTTEvent {
    private MqttMessage mqttMessage;

    public MQTTEvent(MqttMessage mqttMessage) {
        this.mqttMessage = mqttMessage;
    }

    public MqttMessage getMqttMessage() {
        return mqttMessage;
    }

    public void setMqttMessage(MqttMessage mqttMessage) {
        this.mqttMessage = mqttMessage;
    }
}
