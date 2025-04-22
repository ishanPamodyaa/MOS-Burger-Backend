package edu.ICET.service.impl;

import edu.ICET.service.OrderService;
import edu.ICET.service.ReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import lombok.RequiredArgsConstructor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final OrderService orderService;

    @Override
    public byte[] generateOrderReport(String orderId) throws FileNotFoundException, JRException {
        // Get the order details
        var order = orderService.getOrder(orderId);
        
        // Load the report template
        File file = ResourceUtils.getFile("classpath:reports/order_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        
        // Set parameters
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("orderId", orderId);
        parameters.put("customerName", order.getCustomerDto().getFstName()+ order.getCustomerDto().getLstName());
        parameters.put("orderDate", order.getOrderDate());
        
        // Create datasource for order details
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(order.getOrderDetailsDto());
        
        // Generate report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        
        // Return as PDF bytes
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @Override
    public byte[] generateAllOrdersReport() throws FileNotFoundException, JRException {
        var orders = orderService.getAllOrders();
        
        File file = ResourceUtils.getFile("classpath:reports/all_orders_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        
        Map<String, Object> parameters = new HashMap<>();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orders);
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}