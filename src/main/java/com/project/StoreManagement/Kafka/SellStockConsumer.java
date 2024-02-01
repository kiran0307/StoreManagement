//package com.project.StoreManagement.Kafka;
//
//import org.apache.kafka.clients.consumer.Consumer;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.project.StoreManagement.Model.StockMgt;
//import com.project.StoreManagement.Services.StockService;
//
//import java.time.Duration;
//import java.util.Collections;
//import java.util.Properties;
//
//@Component
//public class SellStockConsumer {
//
//    @Autowired
//    private StockService stockService;
//
//    private final String bootstrapServers = "localhost:9092";
//    private final String groupId = "sell-stock-group";
//    private final String topic = "sell-stock-topic";
//    // Additional Kafka consumer properties
//    private final String keyDeserializer = "org.apache.kafka.common.serialization.StringDeserializer";
//    private final String valuedDeserializer= "org.apache.kafka.common.serialization.StringDeserializer";
//
//    private Consumer<String, String> createConsumer() {
//        Properties properties = new Properties();
//        properties.setProperty("bootstrap.servers", bootstrapServers);
//        properties.setProperty("group.id", groupId);
//        //properties.setProperty("key.deserializer", StringDeserializer.class.getName());
//        //properties.setProperty("value.deserializer", StringDeserializer.class.getName());
//        properties.setProperty("key.deserializer", keyDeserializer);
//        properties.setProperty("value.deserializer", valuedDeserializer);
//        return new KafkaConsumer<>(properties);
//    }
//
//    public void consumeEvents() {
//        Consumer<String, String> consumer = createConsumer();
//        consumer.subscribe(Collections.singletonList(topic));
//
//        while (true) {
//            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
//
//            records.forEach(record -> {
//                String eventMessage = record.value();
//                processSellStockEvent(eventMessage);
//            });
//        }
//    }
//
//    private void processSellStockEvent(String eventMessage) {
//        // Example event message: "Sell Stock: StockID=123, TypeID=456, ProductID=789, QuantitySold=5"
//        // Parse the event message to extract relevant information
//        // Extract stockId, typeId, productId, and quantitySold from the event message
//
//        int stockId = extractValueFromEventMessage(eventMessage, "StockID");
//        int typeId = extractValueFromEventMessage(eventMessage, "TypeID");
//        int productId = extractValueFromEventMessage(eventMessage, "ProductID");
//        int quantitySold = extractValueFromEventMessage(eventMessage, "QuantitySold");
//
//        // Update StockMgt table
//        StockMgt stock = stockService.getStockByStockIdAndTypeIdAndProductId(stockId, typeId, productId);
//        if (stock != null) {
//            stock.setCurrentStockCount(stock.getCurrentStockCount() - quantitySold);
//            stockService.addStock(stock);
//        }
//    }
//
//    private int extractValueFromEventMessage(String eventMessage, String key) {
//        String[] keyValuePairs = eventMessage.split(", ");
//        for (String pair : keyValuePairs) {
//            String[] parts = pair.split("=");
//            if (parts.length == 2 && parts[0].trim().equals(key)) {
//                return Integer.parseInt(parts[1].trim());
//            }
//        }
//        return 0; // Return a default value or handle the case when the key is not found
//    }
//}
