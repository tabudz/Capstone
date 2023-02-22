package dataaccess;

import connection.MySQLDB;
import entities.Bike;
import entities.BikeFactory;

import java.util.ArrayList;

/**
 * Lấy thông tin của xe từ cơ sở dữ liệu theo yêu cầu
 * Cập nhật thông tin của xe trong cơ sở dữ liệu theo yêu cầu
 */
public class BikeDA {
    /**
     * Cập nhật trạng thái có đang được sử dụng hay không của xe và vị trí bãi xe mới của xe
     * @param bikeCode: bikeCode của xe
     * @param isInUse: trạng thái  của xe
     * @param dockID: ID bãi xe  mà xe đang ở
     */
    public static void updateIsInUse(int bikeCode, boolean isInUse, String dockID){
        int flag;
        if (isInUse)
            flag = 1;
        else
            flag = 0;
        String command = "UPDATE bike SET isInUse=" + flag + ", dockID=" + '\'' + dockID + '\'' + " WHERE bikeCode=" + bikeCode;
        MySQLDB.execute(command);
    }

    /**
     * Lấy danh sách xe tương ứng với địa chỉ bãi xe
     * @param dockID: ID của bãi xe
     * @return Mảng hai chiều các String để lưu các xe
     */
    public static ArrayList<ArrayList<String>> queryWithDockID(String dockID){
        ArrayList<ArrayList<String>> s = new ArrayList<>();
        String command = "SELECT * from bike WHERE dockID=" + '\'' + dockID + '\'';
        s = MySQLDB.query(command);
        return s;
    }

    /**
     * Lấy thông tin của xe dựa theo bikeCode
     * @param bikeCode: bikeCode của xe
     * @return Thông tin của xe lưu dưới dạng mảng một chiều
     */
    public static ArrayList<ArrayList<String>> queryWithBikeCode(int bikeCode){
        ArrayList<ArrayList<String>> s = new ArrayList<>();
        String command = "SELECT * from bike WHERE bikeCode=" + '\'' + bikeCode + '\'';
        s = MySQLDB.query(command);
        return s;
    }

    /**
     * Lấy tất cả các xe trong cơ sở dữ liệu
     * @return Mảng hai chiều các String lưu các bike ứng với bảng bike trong cơ sở dữ liệu
     */
    public static ArrayList<ArrayList<String>> getBikes(){
        ArrayList<ArrayList<String>> s = new ArrayList<>();
        String command = "SELECT * from bike";
        s = MySQLDB.query(command);
        return s;
    }
    public static ArrayList<ArrayList<String>> getRentingBikes(){
        ArrayList<ArrayList<String>> s = new ArrayList<>();
        String command = "SELECT  * FROM rentbiketransaction WHERE rentBikeCost = -1 ";
        s = MySQLDB.query(command);
        return s;
    }
    public static Bike getBikeByID(String id){
        ArrayList<ArrayList<String>> s = new ArrayList<>();
        String command = "SELECT  * FROM bike WHERE bikeCode = \' "+id+"\'";
        s = MySQLDB.query(command);
        if (s.size()>0)
           return BikeFactory.getBike(s.get(0));
        return null;
    }
}
