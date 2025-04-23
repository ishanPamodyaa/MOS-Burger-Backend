package edu.ICET.service.impl;

import edu.ICET.service.OrderService;
import edu.ICET.service.ReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final OrderService orderService;
    private final DataSource dataSource;

    @Override
    public byte[] generateOrderReport(String orderId) throws FileNotFoundException, JRException {
        // Get the order details
        var order = orderService.getOrder(orderId);

        System.out.println("sdsdsdsds "+ order);

        // Load the report template
        File file = ResourceUtils.getFile("classpath:reports/order_invice.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // Create datasource for order details
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(order.getOrderDetailsDto());


        // Set parameters
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("orderId", orderId);
//        parameters.put("customerName", order.getCustomerDto().getFstName()+ order.getCustomerDto().getLstName());
//        parameters.put("orderDate", order.getOrderDate());


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