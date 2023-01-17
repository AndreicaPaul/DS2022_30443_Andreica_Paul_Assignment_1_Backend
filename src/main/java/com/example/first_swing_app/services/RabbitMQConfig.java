package com.example.first_swing_app.services;

import com.example.first_swing_app.controllers.SensorController;
import com.example.first_swing_app.dto.SensorReadingDto;
import com.example.first_swing_app.models.SensorReading;
import com.example.first_swing_app.security.utils.ApplicationContextHolder;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class RabbitMQConfig {

    SensorReadingService sensorReadingService = ApplicationContextHolder.getContext().getBean(SensorReadingService.class);
    SensorController  sensorController = ApplicationContextHolder.getContext().getBean(SensorController.class);
    MeteringDeviceService  meteringDeviceService = ApplicationContextHolder.getContext().getBean(MeteringDeviceService.class);


    public void subscribeToRabbitMQMessageBus() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUri("amqps://hffmdlnp:R8ZzOkgP4SJKlguOWffjC5F6XS0BqGvd@sparrow.rmq.cloudamqp.com/hffmdlnp");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            // Declare the 'sensors' queue
            channel.queueDeclare("sensors", false, false, false, null);

            // Set up a DeliverCallback to handle the incoming messages
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                Gson gson = new Gson();
                SensorReadingDto sensorReadingDto = gson.fromJson(message, SensorReadingDto.class);

                SensorReading sensorReading = new SensorReading();
                sensorReading.setMeasurementValue(sensorReadingDto.getMeasurement_value());
                sensorReading.setTimestamp(sensorReadingDto.getTimestamp());
                sensorReading.setMeteringDevice(meteringDeviceService.getForId(sensorReadingDto.getDevice_id()));
                sensorReadingService.saveReading(sensorReading);

                System.out.println("Timestamp: " + sensorReadingDto.getTimestamp() + " Device Id: " + sensorReadingDto.getDevice_id() + " Measurement: " + sensorReadingDto.getMeasurement_value());

                if(sensorReadingService.isDeviceLimitExceeded(sensorReadingDto.getDevice_id())) {
                    System.out.println("Limit exceeded for device with ID: " + sensorReadingDto.getDevice_id() + " sending notification");
                    sensorController.sendAlert("Limit exceeded for device with ID: " + sensorReadingDto.getDevice_id());
                }
            };
            channel.basicConsume("sensors", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
