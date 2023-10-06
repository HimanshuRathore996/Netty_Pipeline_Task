package com.task.netty.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.netty.entity.CurrentLocationEvent;
import com.task.netty.entity.DoorOpenAlert;
import com.task.netty.entity.SpeedAlertEvent;
import com.task.netty.repo.CurrentLocationEventRepository;
import com.task.netty.repo.DoorOpenAlertRepository;
import com.task.netty.repo.SpeedAlertEventRepository;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@Component
public class DataHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    private CurrentLocationEventRepository currentLocationRepository;
    
    @Autowired
    private SpeedAlertEventRepository speedAlertRepository;
    
    @Autowired
    private DoorOpenAlertRepository doorOpenRepository;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof String) {
            String jsonData = (String) msg;
            
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(jsonData);
                
                String eventType = jsonNode.get("event_type").asText();
                
                switch (eventType) {
                    case "current_location":
                        CurrentLocationEvent currentLocationEvent = objectMapper.readValue(jsonData, CurrentLocationEvent.class);
                        currentLocationRepository.save(currentLocationEvent);
                        break;
                    case "speed_alert":
                        SpeedAlertEvent speedAlertEvent = objectMapper.readValue(jsonData, SpeedAlertEvent.class);
                        speedAlertRepository.save(speedAlertEvent);
                        break;
                    case "door_open_alert":
                        DoorOpenAlert doorOpenAlert = objectMapper.readValue(jsonData, DoorOpenAlert.class);
                        doorOpenRepository.save(doorOpenAlert);
                        break;
                    default:
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
