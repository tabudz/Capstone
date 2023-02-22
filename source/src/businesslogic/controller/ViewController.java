package businesslogic.controller;

import dataaccess.BikeDA;
import entities.Bike;
import entities.BikeFactory;
import entities.Dock;

import java.util.ArrayList;

/**
 * Khi bắt đầu chạy chương trình, giao diện chính lấy danh sách bãi xe thông qua class InitializeController
 */
public class ViewController {
    /**
     * Lấy tất cả các bãi xe từ cơ sở dữ liệu và chuyển thành đối tượng bãi xe tương ứng
     * @return danh sách các bãi xe
     */
    public static ArrayList<Dock> getDocks(){
        ArrayList<Dock> docks = new ArrayList<>();
        ArrayList<ArrayList<String>> dockTable = Dock.getDockTable();
        for(ArrayList<String> row: dockTable){
            String dockID = row.get(0);
            String name = row.get(1);
            String address = row.get(2);

            int numberOfDockingPoints = Integer.parseInt(row.get(4));
            ArrayList<Bike> bikes = getBikes(dockID);
            Dock dock = new Dock(dockID, name, address,  numberOfDockingPoints, bikes);
            docks.add(dock);
        }
        return docks;
    }

    /**
     * Lấy danh sách các xe trong một bãi xe cho trước từ cơ sở dữ liệu
     * @param dockID: ID của bãi xe
     * @return danh sách xe
     */
    public static ArrayList<Bike> getBikes(String dockID){
        ArrayList<ArrayList<String>> bikeTable = BikeDA.queryWithDockID(dockID);
        return ViewController.tableToBikes(bikeTable);
    }

    /**
     * chuyển kết quả dạng bảng sau khi query về danh sách các xe
     * @param bikeTable: mảng hai chiều các String
     * @return danh sách các xe
     */
    public static ArrayList<Bike> tableToBikes(ArrayList<ArrayList<String>> bikeTable){
        ArrayList<Bike> bikes = new ArrayList<>();
        for(ArrayList<String> row: bikeTable){
            Bike bike = BikeFactory.getBike(row);
            bikes.add(bike);
        }
        return bikes;
    }

}