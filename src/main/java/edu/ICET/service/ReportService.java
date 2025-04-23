package edu.ICET.service;

import net.sf.jasperreports.engine.JRException;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public interface ReportService {
    byte[] generateOrderReport(String orderId) throws FileNotFoundException, JRException, SQLException;
    byte[] generateAllOrdersReport() throws FileNotFoundException, JRException;
}