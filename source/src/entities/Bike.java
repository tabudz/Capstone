package entities;

import dataaccess.BikeDA;

public class Bike {
    private int bikeCode;
    private boolean isInUse;
    private int value;
    private int numPedals;
    private int numSaddle;
    private int numBicycleSeat;
    private String dockID;
    private String licensePlate;

    // SETTER
    public void setNumPedals(int numPedals) { this.numPedals = numPedals; }
    public void setNumSaddle(int numSaddle) { this.numSaddle = numSaddle; }
    public void setNumBicycleSeat(int nBicycleSeat) { this.numBicycleSeat = nBicycleSeat; }
    public void setBikeCode(int bikeCode) {
        this.bikeCode = bikeCode;
    }
    public void setInUse(boolean inUse) {
        isInUse = inUse;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public void setDockID(String dockID) {
        this.dockID = dockID;
    }
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    // GETTER
    public boolean isInUse() {
        return isInUse;
    }
    public int getBikeCode() {
        return bikeCode;
    }
    public int getValue() {
        return value;
    }
    public int getNumPedals() { return numPedals; }
    public int getNumSaddle() { return numSaddle; }
    public int getNumBicycleSeat() { return numBicycleSeat; }
    public String getDockID() {
        return dockID;
    }
    public String getLicensePlate() { return licensePlate;
    }


    public void updateInUseAndDockID(boolean isInUse, String dockID){
        this.isInUse = isInUse;
        BikeDA.updateIsInUse(this.bikeCode, isInUse, dockID);
    }
    public boolean getIsInUse(){
        return this.isInUse;
    }
    public int calculateDeposit(){
        return 4 * getValue() / 10;
    }

    public String getMoreDetails(){ return "Không";}

    public String getType(){ return "Không có thông tin";}

    public String convertBikeCodeToBarCode(int bikeCode){
        if (bikeCode%100<10)
           return "X0"+bikeCode%100;
        return "X"+ bikeCode%100;
    }


    public String getGeneralInfo(){
        return convertBikeCodeToBarCode(bikeCode) + " - " + getType();
    }
}