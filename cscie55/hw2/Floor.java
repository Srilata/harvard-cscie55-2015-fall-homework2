package cscie55.hw2;

/**
 * @Author ssurabhi on 10/3/15.
 * @version 1.0
 */

public class Floor {
   private int numberOfPassengersOnFloor;
   private int floorNumber;
   private Building building;
   private boolean waitForElevatorFlag = false;

    /* The floor constructor */
    public Floor(Building building, int floorNumber){
          this.floorNumber = floorNumber;
          this.building = building;

    }

   /*Returns the number of passengers on the floor who are waiting for the elevator.
    @param
    */
    public int passengersWaiting(){
       return this.numberOfPassengersOnFloor;
    }

    protected void setPassengersWaiting(int numberOfPassengersOnFloor){
        this.numberOfPassengersOnFloor = numberOfPassengersOnFloor;
    }

    /*
    Called when a passenger on the floor wants to wait for the elevator.
    Calling this should cause the elevator to stop the next time it moves to the floor
    @param
    */
    public void waitForElevator(){
        setWaitForElevatorFlag(true);
        this.numberOfPassengersOnFloor++;
       }

     protected boolean isWaitForElevatorFlag() {
         return waitForElevatorFlag;
     }

    protected void setWaitForElevatorFlag(boolean waitForElevatorFlag) {
        this.waitForElevatorFlag = waitForElevatorFlag;
    }

    protected void clearPassengers() {
        setWaitForElevatorFlag(false);
        setPassengersWaiting(0);
    }
 }


