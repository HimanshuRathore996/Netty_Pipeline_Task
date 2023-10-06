package com.task.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;

public class NettyDataStreamer {

    private static final String CSV_FILE_PATH = ""; // Provide the path to your CSV file

    public static void main(String[] args) throws IOException, InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new StringEncoder(), new StringDecoder());
                        }
                    });

            Channel channel = bootstrap.connect("localhost", 9090).sync().channel();

            FileReader fileReader = new FileReader(CSV_FILE_PATH);
            CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(fileReader);

            for (CSVRecord record : csvParser) {
                String deviceId = record.get("device_id");
                double latitude = Double.parseDouble(record.get("latitude"));
                double longitude = Double.parseDouble(record.get("longitude"));
                double vehicleSpeed = Double.parseDouble(record.get("vehicle_speed"));
                String timestamp = record.get("timestamp");
                String eventType = record.get("event_type");

                // Create a JSON string using the CSV data
                String jsonData = String.format(
                        "{\"device_id\":\"%s\",\"latitude\":%f,\"longitude\":%f,\"vehicle_speed\":%f,\"timestamp\":\"%s\",\"event_type\":\"%s\"}",
                        deviceId, latitude, longitude, vehicleSpeed, timestamp,eventType); 

                System.out.print("JsonDate ================== "+ jsonData);
                channel.writeAndFlush(jsonData + "\n"); 
                Thread.sleep(1000); 
            }

            channel.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
