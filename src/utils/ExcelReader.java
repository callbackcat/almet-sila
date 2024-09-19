package src.utils;

import src.entities.Company;
import src.entities.Individual;
import src.enums.CompanyType;
import src.enums.EmployeeType;
import src.interfaces.Employee;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    public static List<Employee> readExcelFile(String filePath) throws IOException {
        List<Employee> employees = new ArrayList<>();
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();

        for (Row row : sheet) {
            if (row.getRowNum() < 3) continue; // Skip first 3 rows

            long id = (long) row.getCell(0).getNumericCellValue();
            String typeStr = dataFormatter.formatCellValue(row.getCell(1));
            if (typeStr.isEmpty()) continue;
            EmployeeType type = EmployeeType.valueOf(typeStr.toUpperCase());
            String email = dataFormatter.formatCellValue(row.getCell(2));
            String phone = dataFormatter.formatCellValue(row.getCell(3));
            String address = dataFormatter.formatCellValue(row.getCell(4));

            Employee employee = null;
            if (type == EmployeeType.IND) {
                String firstName = dataFormatter.formatCellValue(row.getCell(6));
                String lastName = dataFormatter.formatCellValue(row.getCell(7));
                boolean hasChildren = row.getCell(8).getBooleanCellValue();
                int age = (int) row.getCell(9).getNumericCellValue();
                employee = new Individual(id, email, phone, address, null, firstName, lastName, hasChildren, age);
            } else if (type == EmployeeType.COM) {
                String companyName = dataFormatter.formatCellValue(row.getCell(11));
                String companyTypeStr = dataFormatter.formatCellValue(row.getCell(12));
                CompanyType companyType = CompanyType.valueOf(companyTypeStr.toUpperCase());
                employee = new Company(id, email, phone, address, null, companyName, companyType);
            }

            employees.add(employee);
        }

        workbook.close();
        file.close();
        return employees;
    }
}