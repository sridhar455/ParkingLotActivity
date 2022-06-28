public class Vehicle{
     private String registrationNumber;
     private int driverAge;
    public Vehicle(String registrationNumber, int driverAge) {
        this.registrationNumber = registrationNumber;
        this.driverAge = driverAge;
    }
    public int getDriverAge(){
        return this.driverAge;
    }
    public String getRegistrationNumber(){
        return this.registrationNumber;
    } 
    }