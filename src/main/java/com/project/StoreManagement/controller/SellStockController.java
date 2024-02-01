//package com.project.StoreManagement.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.project.StoreManagement.Kafka.SellStockProducer;
//import com.project.StoreManagement.Model.StockMgt;
//import com.project.StoreManagement.Services.StockService;
//
//@RestController
//public class SellStockController {
//    private final StockService stockService;
//    private final SellStockProducer sellStockProducer;
//
//    @Autowired
//    public SellStockController(StockService stockService, SellStockProducer sellStockProducer) {
//        this.stockService = stockService;
//        this.sellStockProducer = sellStockProducer;
//    }
//
//    @PostMapping("/sell")
//    public ResponseEntity<String> sellStock(
//            @RequestParam int stockId,
//            @RequestParam int typeId,
//            @RequestParam int productId,
//            @RequestParam int quantitySold) {
//
//        // Retrieve StockMgt from the database based on stockId, typeId, and productId
//        StockMgt stock = stockService.getStockByStockIdAndTypeIdAndProductId(stockId, typeId, productId);
//
//        if (stock != null) {
//            // Check if there is enough stock to sell
//            if (stock.getCurrentStockCount() >= quantitySold) {
//                // Send the event to Kafka
//                sellStockProducer.sendSellStockEvent(stockId, typeId, productId, quantitySold);
//
//                // Update StockMgt table (subtract sold quantity)
//                stock.setCurrentStockCount(stock.getCurrentStockCount() - quantitySold);
//                stockService.addStock(stock);
//
//                return ResponseEntity.ok("Stock sold successfully.");
//            } else {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough stock available to sell.");
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Stock with stockId " + stockId + ", typeId " + typeId + ", and productId " + productId + " not found.");
//        }
//    }
//}
