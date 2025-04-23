package edu.ICET.controller;


import edu.ICET.dto.OrderDto;
import edu.ICET.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    
    final OrderService orderService;
//    final ReportService reportService;

    @PostMapping("/add")
    public void addOrder(@RequestBody OrderDto orderDto){
        orderService.addOrder(orderDto);
    }

    @GetMapping("/getOrderById")
    public OrderDto getOrder(@RequestParam String orderId){
        return orderService.getOrder(orderId);
    }

    @GetMapping("/getAll")
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder (@PathVariable String orderId){
         orderService.deleteOrder(orderId);
    }

    @GetMapping("/search-by-name/{name}")
    public OrderDto getOrderByName(@PathVariable String name ){
        return orderService.getOrderByName(name);
    }

    @GetMapping("/search-by-contactNumber/{contactNumber}")
    public OrderDto getOrderContactNumber(String contactNumber){
        return orderService.getOrderContactNumber(contactNumber);
    }

//    @GetMapping("/report/{orderId}")
//    public ResponseEntity<byte[]> generateOrderReport(@PathVariable String orderId) {
//
//        System.out.println("order id  = "+ orderId);
//        try {
//            byte[] pdf = reportService.generateOrderReport(orderId);
//              return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice_" + orderId + ".pdf")
//                    .contentType(MediaType.APPLICATION_PDF)
//                    .body(pdf);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }

    @GetMapping("/invoice/all")
    public ResponseEntity<byte[]> generateAllOrdersReport() {
        try {
            byte[] report = orderService.generateAllOrdersReport();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=all_orders.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(report);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/invoice/{orderId}")
    public ResponseEntity<byte[]> generateInvoice(@PathVariable String orderId) {
        try {
            byte[] invoice = orderService.generateInvoice(orderId);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice_" + orderId + ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(invoice);
        } catch (Exception e) {
            e.printStackTrace(); // For debugging
            return ResponseEntity.internalServerError().build();
        }
    }
}
