//package com.project.StoreManagement.Kafka;
//
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.Producer;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.springframework.stereotype.Service;
//
//import java.util.Properties;
//
//@Service
//public class SellStockProducer {
//    private final Producer<String, String> producer;
//
//    public SellStockProducer(Properties producerProps) {
//        // Ensure that key.serializer and value.serializer are set
//        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
//        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
//
//        try {
//            this.producer = new KafkaProducer<>(producerProps);
//        } catch (Exception e) {
//            // Log the exception or handle it according to your application's needs
//            e.printStackTrace();
//            // You might want to throw a custom exception or take other corrective actions
//            throw new RuntimeException("Failed to create KafkaProducer", e);
//        }
//    }
//
//    public void sendSellStockEvent(int stockId, int typeId, int productId, int quantitySold) {
//        String eventMessage = String.format("Sell Stock: StockID=%d, TypeID=%d, ProductID=%d, Quantity=%d",
//                stockId, typeId, productId, quantitySold);
//        producer.send(new ProducerRecord<>("sell-stock-topic", eventMessage));
//    }
//
//    public void close() {
//        producer.close();
//    }
//}
