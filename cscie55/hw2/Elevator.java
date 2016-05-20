package cscie55.hw2;

import cscie55.hw2.Direction;

/**
 * @Author ssurabhi on 9/13/15.
 * @version 1.1
 */

public class Elevator {

    /*
    As array counts index from 0, and we are counting floors from 1.
     To avoid confusion declaring array size as 8(7+1) and initializing them to false.
     Same logic applied for passengerCountPerFloor. So initialized them to zeros
     stopFlagArray flag shows weather stop required for a particular floor*/
    private boolean stopFlagArray[] = {false,false,false,false,false,false,false,false};

    /* Number of passengers destined for the floor*/
    private int passengerCountPerFloor[]={0,0,0,0,0,0,0,0};

    // number of passengers currently on the elevator
    private int passengers = 0;
    public static final int CAPACITY = 10;
    private int currentFloor = 1;
    Direction direction = Direction.UP;
    private Building building;

    /*
     Elevator constructor with building as parameter
     */
    public Elevator(Building building){
        this.building = building;
    }

      /*
       Takes the destination floor as parameter. This method adds to the elevator a passenger destined for the indicated floor
       and enables the stopFlagArray if any passengers destined to that floor.
       @Param destinationFloorNumber
      */
    public void boardPassenger(int destinationFloorNumber) throws ElevatorFullException{

       if(passengers >= CAPACITY){
          throw new ElevatorFullException("passenger count is more than expected");
        }
        passengers++;
        passengerCountPerFloor[destinationFloorNumber]++;
        stopFlagArray[destinationFloorNumber] = true;
    }

    /*
       This method modifies the Elevator state
       Modifies the current floor
       Modifies the direction of travel, if the ground floor or top floor has been reached
      Clears the array entry tracking the number of passengers destined for the floor that the elevator has just arrived at.
      Clears the array entry indicating that a stop is required on the floor that the elevator has just arrived at
      @param
     */
    public void move(){

        if(direction.equals(Direction.UP)) {
            currentFloor ++;
        }

        if(direction.equals(Direction.DOWN)) {
            currentFloor --;
            if (currentFloor == 1) {
               passengers = 0;
            }
        }

        if(currentFloor == 1){
            direction = Direction.UP;
        }

        if(currentFloor == Building.FLOORS){
            direction = Direction.DOWN;
        }

        if(stopFlagArray[currentFloor]){
            passengers = passengers - passengerCountPerFloor[currentFloor];
            passengerCountPerFloor[currentFloor] = 0;
            stopFlagArray[currentFloor] = false;
        }

        Floor currentFloorObj = this.building.floor(currentFloor);

        if(currentFloorObj.isWaitForElevatorFlag()){
            int passengersThatCanBoard =   CAPACITY-passengers;
            if(currentFloorObj.passengersWaiting() >= passengersThatCanBoard) {

                passengers= passengers+passengersThatCanBoard;
                currentFloorObj.setPassengersWaiting(currentFloorObj.passengersWaiting() - passengersThatCanBoard);
            }else{
                  passengers= passengers+currentFloorObj.passengersWaiting();
                currentFloorObj.clearPassengers();
               }

        }
    }

    /*
    Return the elevator's current floor number. I.e., this is the number of the floor reached by the last call to Elevator.move()
    @param
    */
    public int currentFloor() {
        return currentFloor;
    }

    /*
    Return the number of passengers currently on the elevator
    @param
    */
    public int passengers() {
        return passengers;
    }

















}
