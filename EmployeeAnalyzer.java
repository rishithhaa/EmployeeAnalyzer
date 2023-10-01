import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeAnalyzer {
    public static void main(String[] args) {
        String filePath = "Assignment_Timecard.xlsx"; // Replace with your file path
        analyzeEmployeeData(filePath);
    }

    public static void analyzeEmployeeData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String currentEmployee = null;
            Date shiftStart = null;
            Date previousDate = null;
            
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // Assuming CSV format
                String employee = parts[0];
                String position = parts[1];
                String dateStr = parts[2];
                double hoursWorked = Double.parseDouble(parts[3]);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = dateFormat.parse(dateStr);

                if (currentEmployee == null || !currentEmployee.equals(employee)) {
                    currentEmployee = employee;
                    shiftStart = date;
                    previousDate = date;
                } else {
                    long dayDifference = (date.getTime() - previousDate.getTime()) / (24 * 60 * 60 * 1000);
                    
                    if (dayDifference == 1) {
                        // Check conditions
                        if (hasWorkedFor7ConsecutiveDays(shiftStart, date)) {
                            System.out.println(employee + " has worked for 7 consecutive days in position " + position);
                        }
                        
                        if (hasLessThan10HoursBetweenShifts(shiftStart, date)) {
                            System.out.println(employee + " has less than 10 hours between shifts in position " + position);
                        }
                        
                        if (hasWorkedMoreThan14Hours(hoursWorked)) {
                            System.out.println(employee + " has worked more than 14 hours in a single shift in position " + position);
                        }
                    }
                    
                    shiftStart = date;
                    previousDate = date;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean hasWorkedFor7ConsecutiveDays(Date start, Date end) {
        // Implement logic to check for 7 consecutive days
        return false;
    }

    public static boolean hasLessThan10HoursBetweenShifts(Date start, Date end) {
        // Implement logic to check for less than 10 hours between shifts
        return false;
    }

    public static boolean hasWorkedMoreThan14Hours(double hoursWorked) {
        return hoursWorked > 14;
    }
}