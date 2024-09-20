package src;

import src.entities.Company;
import src.entities.Individual;
import src.interfaces.Employee;
import src.utils.ExcelReader;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "test.xlsx";
        List<Employee> employees = ExcelReader.readExcelFile(filePath);

        int individualCount = 0;
        int companyCount = 0;

        for (Employee employee : employees) {
            if (employee instanceof Individual individual) {
                individualCount++;
                if (individual.getAge() < 20) {
                    System.out.println("Младше 20 лет: " + individual.getFirstName() + " " + individual.getLastName());
                }
            } else if (employee instanceof Company) {
                companyCount++;
            }
        }

        System.out.println("Количество физических лиц: " + individualCount);
        System.out.println("Количество компаний: " + companyCount);
    }
}
