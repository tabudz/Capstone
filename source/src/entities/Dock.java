package entities;

import businesslogic.controller.ViewController;
import dataaccess.DockDA;

import java.util.ArrayList;

public class Dock {
    private String dockID;
    private String name;
    private String address;

    private int numberOfDockingPoints;
    private ArrayList<Bike> bikes;

    public Dock(String dockID, String name, String address,  int numberOfDockingPoints, ArrayList<Bike> bikes) {
        this.dockID = dockID;
        this.name = name;
        this.address = address;

        this.numberOfDockingPoints = numberOfDockingPoints;
        this.bikes = bikes;
    }

    public ArrayList<Bike> getBikes() {
        return bikes;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }


    public String getDockID() {
        return dockID;
    }

    public void setDockID(String dockID) {
        this.dockID = dockID;
    }

    public int getNumberOfDockingPoints() {
        return numberOfDockingPoints;
    }


    public int getNumberOfBikes(){
        return this.bikes.size();
    }

//    public float getDistance(){
//        return -1f;
//    }

    public String getGeneralInfo() {
//        int n = this.getNumberOfDockingPoints() - this.getBikes().size();
//        String s = Integer.toString(n);
//        if(s.length() < 2)
//            s = "0" + s;
        return  String.format(" %-2s - %s", name, address);
    }

    public static ArrayList<ArrayList<String>> getDockTable(){
        return DockDA.getAllDocks();
    }
    public static Dock getDockById(String id){
        ArrayList<ArrayList<String>> docks = DockDA.getDockById(id);

        String dockID = docks.get(0).get(0);
        String name = docks.get(0).get(1);
        String address = docks.get(0).get(2);

        int numberOfDockingPoints = Integer.parseInt(docks.get(0).get(4));
        ArrayList<Bike> bikes = ViewController.getBikes(dockID);
        Dock dock = new Dock(dockID, name, address,  numberOfDockingPoints, bikes);
        return dock;

    }
}
