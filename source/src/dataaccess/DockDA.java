package dataaccess;

import connection.MySQLDB;

import java.util.ArrayList;

/**
 * Lấy thông tin của bãi xe từ cơ sở dữ liệu theo yêu cầu
 */
public class DockDA {
    /**
     * Lấy tất cả các bãi xe trong cơ sở dữ liệu
     * @return Mảng hai chiều các String
     */
    public static ArrayList<ArrayList<String>> getAllDocks(){
        String command = "SELECT * FROM dock";
        return MySQLDB.query(command);
    }
    public static ArrayList<ArrayList<String>> getDockById(String id){
        String command = "SELECT * FROM dock WHERE dockID = \'"+id+'\'';
        return MySQLDB.query(command);
    }
}
