package edu.ICET.service.impl;

import edu.ICET.dto.CustomerDto;
import edu.ICET.dto.OrderDetailsDto;
import edu.ICET.dto.OrderDto;
import edu.ICET.dto.ProductDto;
import edu.ICET.entity.*;
import edu.ICET.repocitory.CustomerRepocitory;
import edu.ICET.repocitory.OrderDetsilsRepocitory;
import edu.ICET.repocitory.OrderRepocitory;
import edu.ICET.repocitory.ProductRepocitory;
import edu.ICET.service.OrderService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import org.springframework.util.ResourceUtils;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.parameters;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    final OrderRepocitory orderRepocitory;
    final ProductRepocitory productRepocitory;
    final OrderDetsilsRepocitory orderDetsilsRepocitory;
    final CustomerRepocitory customerRepocitory;
    final ModelMapper mapper;
    final DataSource dataSource;

    @Override
    @Transactional
    public void addOrder(OrderDto orderDto) {
        if (orderDto.getOrderDetailsDto() == null || orderDto.getOrderDetailsDto().isEmpty()) {
            throw new RuntimeException("Order must contain at least one product");
        }

        Customer customer = customerRepocitory.findByCustomerId(orderDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found: " + orderDto.getCustomerId()));

        Order order = mapper.map(orderDto, Order.class);
        order.setCustomer(customer);

        List<OrderDetail> orderDetailList = new ArrayList<>();

        // Process order details and validate stock
        for (OrderDetailsDto orderDetailsDto : orderDto.getOrderDetailsDto()) {
            Product product = productRepocitory.findByProductId(orderDetailsDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + orderDetailsDto.getProductId()));

            if (product.getQtyInHand() < orderDetailsDto.getOrderQty()) {
                throw new RuntimeException("Insufficient stock for product: " + orderDetailsDto.getProductId());
            }

            // Update quantity in hand
            product.setQtyInHand(product.getQtyInHand() - orderDetailsDto.getOrderQty());
            productRepocitory.save(product);

            OrderDetails_pk orderDetailsPk = new OrderDetails_pk(orderDto.getOrderId(), orderDetailsDto.getProductId());
            OrderDetail orderDetailObj = new OrderDetail();
            orderDetailObj.setId(orderDetailsPk);
            orderDetailObj.setOrder(order);
            orderDetailObj.setProduct(product);
            orderDetailObj.setOrderQty(orderDetailsDto.getOrderQty());
            orderDetailObj.setDiscount(orderDetailsDto.getDiscount());
            orderDetailObj.setPrice(orderDetailsDto.getPrice());

            orderDetailList.add(orderDetailObj);
        }

        order.setOrderDetails(orderDetailList);
        orderRepocitory.save(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderDto> orderResults = new ArrayList<>();
        List<Order> results = orderRepocitory.findAll();

        results.forEach(order -> {
            OrderDto orderDto = mapper.map(order, OrderDto.class);

            // Populate CustomerDto
            if (order.getCustomer() != null) {
                orderDto.setCustomerDto(mapper.map(order.getCustomer(), CustomerDto.class));
            }

            // Populate OrderDetailsDto
            if (order.getOrderDetails() != null) {
                List<OrderDetailsDto> details = order.getOrderDetails().stream()
                        .map(detail -> mapper.map(detail, OrderDetailsDto.class))
                        .collect(Collectors.toList());
                orderDto.setOrderDetailsDto(details);
            }

            orderResults.add(orderDto);
        });
        return orderResults;
    }

    @Override
    public OrderDto getOrder(String orderId) {

        OrderDto orderDto = mapper.map(orderRepocitory.findByOrderId(orderId), OrderDto.class);

        CustomerDto customerDto = mapper.map(customerRepocitory.findByCustomerId(orderDto.getCustomerId()),
                CustomerDto.class);
        orderDto.setCustomerDto(customerDto);
        List<OrderDetail> orderDetailList = orderDetsilsRepocitory.findByOrderOrderId(orderId);

        List<OrderDetailsDto> orderDetailsDtoList = orderDetailList.stream()
                .map(orderDetail -> mapper.map(orderDetail, OrderDetailsDto.class))
                .collect(Collectors.toList());

        orderDto.setOrderDetailsDto(orderDetailsDtoList);

        return orderDto;
    }

    @Override
    public OrderDto getOrderByName(String name) {
        return null;
    }

    @Override
    public OrderDto getOrderContactNumber(String contactNumber) {
        return null;
    }

    @Override
    public void deleteOrder(String orderId) {
        orderRepocitory.deleteById(orderId);
    }

    @Override
    public byte[] generateInvoice(String orderId) throws Exception {
        try {
            File file = ResourceUtils.getFile("classpath:reports/order_invice.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("order_id", orderId);

            JasperPrint print = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    dataSource.getConnection());
            return JasperExportManager.exportReportToPdf(print);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public byte[] generateAllOrdersReport() throws Exception {

        try {
            File file = ResourceUtils.getFile("classpath:reports/allOrderReport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    new HashMap<>(),
                    dataSource.getConnection());

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
