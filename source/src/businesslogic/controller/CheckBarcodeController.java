package businesslogic.controller;

import businesslogic.barcodconvertersubsystem.BarcodeConverterController;
import businesslogic.barcodconvertersubsystem.IBarcodeConverter;
import dataaccess.BikeDA;
import entities.Bike;
import entities.BikeFactory;
import javafx.util.Pair;

import java.util.ArrayList;

public class CheckBarcodeController {


    public static String rentalCode = "";

    private static ArrayList<ArrayList<String>> listBike ;

    private static ArrayList<String> bikeIsRented;

    private static int bikeCode;
    /**
     * Check barcode
     * @param barcode : mã xe
     * @return  (mã check bikeCode, thông tin xe (nếu bikeCode đúng))
     */
    public static Pair<Boolean, Bike> checkBarcodeAndGetBikeIfTrue(String barcode){
        IBarcodeConverter bc = new BarcodeConverterController();
        boolean checkBarc = false;
        Bike bike = null;
        try{

            bikeCode = bc.convertBarcodeToBikeCode(barcode);
            listBike = BikeDA.getBikes();
            for (ArrayList<String> b : listBike){
                if (bikeCode == Integer.parseInt(b.get(0)) ){
                    checkBarc = true;
                    bikeIsRented = b;
                    break;
                }
            }
            if (checkBarc){
                bike = BikeFactory.getBike(bikeIsRented);
            }

        }catch (NumberFormatException e){
            System.out.println("Barcode không hợp lệ!");
            return new Pair<>(checkBarc, bike);
        }

        return new Pair<>(checkBarc, bike);
    }
}