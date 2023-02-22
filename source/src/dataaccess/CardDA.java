package dataaccess;

import connection.MySQLDB;

import java.util.ArrayList;

public class CardDA {
    public static ArrayList<ArrayList<String>> getAllCardInUse(){
        String command = "Select * from card";
        ArrayList<ArrayList<String>> s = new ArrayList<>();
        s = MySQLDB.query(command);
        return s;
    }

    public static void saveCardInfo(String cardCode, String owner, String CVV, String expiredDate){
        String command = "INSERT INTO card values" +
                "(" + '\'' + cardCode + '\'' + ", " +
                '\'' + owner + '\'' + ", " +
                '\'' + CVV + '\'' + ", " +
                '\'' + expiredDate + '\'' + ");";
        MySQLDB.execute(command);
    }

    public static void deleteCardInfo(String cardCode){
        String command = "DELETE FROM card WHERE" +
                " cardCode" + "=" + '\'' + cardCode + '\'' + ";";
        MySQLDB.execute(command);
    }
}
