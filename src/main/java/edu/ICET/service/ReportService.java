package edu.ICET.service;

import net.sf.jasperreports.engine.JRException;
import java.io.FileNotFoundException;

public interface ReportService {
    byte[] generateOrderReport(String orderId) throws FileNotFoundException, JRException;
    byte[] generateAllOrdersReport() throws FileNotFoundException, JRException;
}