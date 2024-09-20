package src.utils;

import src.entities.BankAccount;
import src.entities.Company;
import src.entities.Individual;
import src.enums.CompanyType;
import src.enums.EmployeeType;
import src.interfaces.Employee;
import org.apache.poi.ss.usermodel.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static List<Employee> readExcelFile(String filePath) throws IOException {
        List<Employee> employees = new ArrayList<>();
        InputStream fileStream = getFileStream(filePath);

        Workbook workbook = WorkbookFactory.create(fileStream);
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();

        for (Row row : sheet) {
            if (row.getRowNum() < 3) continue; // Skip first 3 rows
            Employee employee = parseRow(row, dataFormatter);
            if (employee != null) {
                employees.add(employee);
            }
        }

        workbook.close();
        return employees;
    }

    private static InputStream getFileStream(String filePath) {
        ClassLoader classLoader = ExcelReader.class.getClassLoader();
        InputStream fileStream = classLoader.getResourceAsStream(filePath);
        if (fileStream == null) {
            throw new IllegalArgumentException("File not found: " + filePath);
        }
        return fileStream;
    }

    private static Employee parseRow(Row row, DataFormatter dataFormatter) {
        long id = (long) row.getCell(0).getNumericCellValue();

        String typeStr = dataFormatter.formatCellValue(row.getCell(1));
        if (typeStr.isEmpty()) return null;

        EmployeeType type = EmployeeType.valueOf(typeStr.toUpperCase());

        if (type != EmployeeType.IND && type != EmployeeType.COM) {
            type = EmployeeType.UNKNOWN;
        }

        String email = dataFormatter.formatCellValue(row.getCell(2));
        String phone = dataFormatter.formatCellValue(row.getCell(3));
        String address = dataFormatter.formatCellValue(row.getCell(4));
        BankAccount bankAccount = parseBankAccount(row);

        return switch (type) {
            case IND, UNKNOWN -> parseIndividual(row, id, email, phone, address, bankAccount, dataFormatter);
            case COM -> parseCompany(row, id, email, phone, address, bankAccount, dataFormatter);
        };
    }

    private static BankAccount parseBankAccount(Row row) {
        return new BankAccount(
                row.getCell(14).getStringCellValue(),
                row.getCell(15).getStringCellValue(),
                row.getCell(16).getStringCellValue()
        );
    }

    private static Individual parseIndividual(Row row, long id, String email, String phone, String address, BankAccount bankAccount, DataFormatter dataFormatter) {
        String firstName = dataFormatter.formatCellValue(row.getCell(6));
        String lastName = dataFormatter.formatCellValue(row.getCell(7));
        boolean hasChildren = row.getCell(8).getBooleanCellValue();
        int age = (int) row.getCell(9).getNumericCellValue();
        return new Individual(id, email, phone, address, bankAccount, firstName, lastName, hasChildren, age);
    }

    private static Company parseCompany(Row row, long id, String email, String phone, String address, BankAccount bankAccount, DataFormatter dataFormatter) {
        String companyName = dataFormatter.formatCellValue(row.getCell(11));
        String companyTypeStr = dataFormatter.formatCellValue(row.getCell(12));
        CompanyType companyType = CompanyType.valueOf(companyTypeStr.toUpperCase());
        return new Company(id, email, phone, address, bankAccount, companyName, companyType);
    }
}