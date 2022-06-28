import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class ParkingLot {
    int noOfSlots;

    ArrayList<Integer> availableSlots;

    //To Map Slot and Vehicle
    Map<Integer,Vehicle> mapSlotAndvehicle;

    //To Map registration Number and Slot
    Map<String,Integer> mapRegNoAndSlot;

    //To Map age and vehicle Registration Numbers
    Map<Integer,ArrayList<String>> mapAgeAndRegNo;

    public ParkingLot(){

        this.noOfSlots=0;
        this.availableSlots=new ArrayList<>();
        this.mapSlotAndvehicle=new HashMap<>();
        this.mapRegNoAndSlot=new HashMap<>();
        this.mapAgeAndRegNo=new HashMap<>();
    }
    public void createParkingLot(int slots){
            noOfSlots=slots;
            for(int i=1;i<=noOfSlots;i++){
                availableSlots.add(i);
            }
            System.out.println("Created parking of "+noOfSlots+" slots");
    }

    public void park(String reg_No,int age){
        if(noOfSlots==0){
            System.out.println("The Parking Lot is not Created");
        }
        else if(mapSlotAndvehicle.size()==noOfSlots){
            System.out.println("The Parking Lot is currently full");
        }
        else if(mapRegNoAndSlot.containsKey(reg_No)){
            System.out.println("Same Vehicle alreday Present in the Parking Lot");
        }
        else {
            Collections.sort(availableSlots);
            int nearestAvailableSlot=availableSlots.get(0);

            Vehicle vehicle=new Vehicle(reg_No, age);

            mapSlotAndvehicle.put(nearestAvailableSlot,vehicle);
            mapRegNoAndSlot.put(reg_No, nearestAvailableSlot);

            if(mapAgeAndRegNo.containsKey(age))
            {
            ArrayList<String> registrationList=mapAgeAndRegNo.get(age);
            registrationList.add(reg_No);
            mapAgeAndRegNo.put(age, registrationList);

            }
            else{
            ArrayList<String> registrationList=new ArrayList<>();
            registrationList.add(reg_No);
            mapAgeAndRegNo.put(age, registrationList);
            }
            availableSlots.remove(0);
            System.out.println("Car with vehicle registration number \""+reg_No+"\" has been parked at slot number "+nearestAvailableSlot);

            
        }
        

    }

    public void leave(int slot){
        if(noOfSlots==0){
            System.out.println("The Parking Lot is not Created");
        }
        else if(availableSlots.size()==noOfSlots){
            System.out.println("The Parking Lot is Empty");
        }
        else{
            Vehicle vehicleToLeave=mapSlotAndvehicle.get(slot);
          if(vehicleToLeave==null){
            System.out.println("There is no vehicle in that slot");
          }
          else{
            mapSlotAndvehicle.remove(slot);
            mapRegNoAndSlot.remove(vehicleToLeave.getRegistrationNumber());

            ArrayList<String> regNoList=mapAgeAndRegNo.get(vehicleToLeave.getDriverAge());
            
            if(regNoList.contains(vehicleToLeave.getRegistrationNumber())){
                mapAgeAndRegNo.get(vehicleToLeave.getDriverAge()).remove(vehicleToLeave.getRegistrationNumber());
            }
            availableSlots.add(slot);
            System.out.println("Slot number "+slot+" vacated, the car with vehicle registration number \""+vehicleToLeave.getRegistrationNumber()+"\" left the space, the driver of the car was of age "+vehicleToLeave.getDriverAge());
          }

        }


    }
    public void getSlotNumberFromVehicleRegistrationPlate(String regi_No){
            if(noOfSlots==0){
                System.out.println("The Parking Lot is not Created");
          }
            else if(mapRegNoAndSlot.containsKey(regi_No)){
                System.out.println(mapRegNoAndSlot.get(regi_No));
            }
            else{
                System.out.println("The Vehicle with registration number \""+regi_No+"\" not found in Parking Lot");
            }
    }

    public void getSlotNumbersFromDriverAge(int age){
        if(noOfSlots==0){
            System.out.println("The Parking Lot is not Created");
        }
        else if(mapAgeAndRegNo.containsKey(age) && mapAgeAndRegNo.get(age).size()>0){
            String slots="";
            for(String reg_No:mapAgeAndRegNo.get(age)){
                slots=slots+mapRegNoAndSlot.get(reg_No)+",";
            }
            System.out.println(slots.substring(0,slots.length()-1));
        }
        else{
            //System.out.println("There is no driver with age "+age);
            System.out.println("");
        }

    }

    public void getVehicleRegistrationNumbersFromAge(int age){
        if(noOfSlots==0){
            System.out.println("The Parking Lot is not Created");
        }
        else if(mapAgeAndRegNo.containsKey(age) && mapAgeAndRegNo.get(age).size()>0){
            String register_Numbers="";
            for(String reg_No:mapAgeAndRegNo.get(age)){
                register_Numbers=register_Numbers+reg_No+",";
            }
            System.out.println(register_Numbers.substring(0,register_Numbers.length()-1));
        }
        else{
            //System.out.println("There is no Vehicle driver with age "+age);
            System.out.println("");
        }

    }
}
