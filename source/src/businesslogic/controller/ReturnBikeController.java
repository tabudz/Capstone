package businesslogic.controller;

import businesslogic.costcalculator.CostCalculator;
import businesslogic.interbanksubsystem.IInterbank;
import businesslogic.interbanksubsystem.InterbankSubsysController;
import dataaccess.BikeDA;
import dataaccess.RentBikeTransactionDA;
import entities.*;
import javafx.util.Pair;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import connection.MySQLDB;

/**
 * Thực hiện xử lý yêu cầu trả xe của người dùng
 *
 */
public class ReturnBikeController {
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    
    /**
     * Xử lý yêu cầu trả xe của người dùng
     * @return RentBikeTransaction nếu thành công và null nếu thất bại
     */
    public static Pair<String, RentBikeTransaction> processReturnBike(String rentalCode, Card card, String newDockID){
        RentBikeTransaction rentBikeTransaction = ReturnBikeController.getRentBikeTransaction(rentalCode);
        Bike bike = getBike(rentBikeTransaction.getBikeCode());

        System.out.println(rentBikeTransaction.getDetailInfo());
        CostCalculator costCalculator = new CostCalculator();
        costCalculator.setCostCalculationMethod(bike);
        //long rentTime = (System.nanoTime()-rentBikeTransaction.getStart())/1000000000;
        String returnTime = getCurrentLocalDateTimeStamp(PATTERN);
        long rentTime = getEpochSecond(returnTime, PATTERN) -getEpochSecond(rentBikeTransaction.getRentTime(), PATTERN);
        int rentBikeCost = costCalculator.calculateCost((int) rentTime/60);
        long refundAmount = rentBikeTransaction.getDeposit() -  rentBikeCost;
        System.out.println("Refund amount: " + refundAmount);
        assert refundAmount > 0;
        IInterbank interbank = new InterbankSubsysController();
        String respondCode = interbank.processTransaction(card, refundAmount, "refund", "Giao dịch hoàn tiền");
        System.out.println("respond code: " + respondCode);
        interbank.reset();

        
//        command = "DELETE FROM rentbiketransaction";

        //RentBikeScreen.rent = false;

        // create new transaction and save
        PaymentTransaction paymentTransaction = new PaymentTransaction(
                rentalCode, card.getCardCode(), card.getOwner(),
                "Trả xe", refundAmount,
                getCurrentLocalDateTimeStamp("HH:mm:ss"), getCurrentLocalDateTimeStamp("yyyy-MM-dd"));
        paymentTransaction.savePaymentTransaction();
        
        String command = "DELETE FROM paymenttransaction;";

        MySQLDB.execute(command);

        // update rentBikeTransaction
        rentBikeTransaction.updateReturnTimeAndCost(returnTime, rentBikeCost);
        
        command = "DELETE FROM rentbiketransaction;";
        MySQLDB.execute(command);
       
        // update Bike
        bike.updateInUseAndDockID(false, newDockID);
        

        return new Pair<>(respondCode, rentBikeTransaction);
    }

    /**
     * Lấy thông tin giao dịch tử cơ sở dữ liệu dưa trên mã thuê xe
     *
     * @param rentalCode: mã thuê xe
     * @return Đối tượng RentBikeTransaction
     */
    public static RentBikeTransaction getRentBikeTransaction(String rentalCode){
        ArrayList<ArrayList<String>> rentBikeTransactions = RentBikeTransactionDA.queryByRentalCode(rentalCode);
        assert rentBikeTransactions.size() == 1;
        ArrayList<String> s = rentBikeTransactions.get(0);
        int bikeCode = Integer.parseInt(s.get(1));
        String bikeType = s.get(2);
        int rentBikeCost = Integer.parseInt(s.get(3));
        String owner = s.get(4);
        String rentTime = s.get(5);
        String returnTime = s.get(6);
        int deposit = Integer.parseInt(s.get(7));
        return new RentBikeTransaction(
                rentalCode, bikeCode, bikeType, rentBikeCost, owner, rentTime, returnTime, deposit);
    }

    /**
     * Kiểm tra mã thuê xe người dùng nhập vào có hợp lệ hay không
     * @param rentalCode: mã thuê xe
     * @return boolean
     */
    public static boolean checkRentalCodeValid(String rentalCode) {
        ArrayList<ArrayList<String>> rentBikeTransactions = RentBikeTransactionDA.queryByRentalCode(rentalCode);
        return rentBikeTransactions.size() == 1;
    }

    /**
     * Lấy thông tin của xe từ cơ sở dữ liệu dựa theo bikeCode
     *
     * @param bikeCode: bikeCode của xe
     * @return ArrayList[String] là một mảng các thuộc tính của xe
     */
    public static Bike getBike(int bikeCode){
        ArrayList<ArrayList<String>> bikeTable = BikeDA.queryWithBikeCode(bikeCode);
        assert bikeTable.size() == 1;
        return BikeFactory.getBike(bikeTable.get(0));
    }

    /**
     * Lấy thời gian hiện tại và chuyển đổi thành format
     *
     * @param pattern: format thời gian
     * @return Thời gian hiện tại theo format thời gian phía trên
     */
    public static String getCurrentLocalDateTimeStamp(String pattern) {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(pattern));
    }
    public static long getEpochSecond(String time, String pattern) {
        return LocalDateTime.parse(time,DateTimeFormatter.ofPattern(pattern)).toEpochSecond(ZoneOffset.UTC);
    }

}
