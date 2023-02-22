package businesslogic.controller;

//import businesslogiclayer.interbanksubsystem.IInterbank;
//import businesslogiclayer.interbanksubsystem.InterbankSubsysController;

import businesslogic.interbanksubsystem.IInterbank;
import businesslogic.interbanksubsystem.InterbankSubsysController;
import entities.Bike;
import entities.Card;
import entities.PaymentTransaction;
import entities.RentBikeTransaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 */
public class RentBikeController {

    public static String rentalCode;

    /**
     * Xử lý giao dịch thuê xe
     * Nếu giao dịch thành công thì sẽ tiến hành lưu lại giao dịch thanh toán,
     * thông tin phiên thuê xe và cập nhật xe thành đang sử dụng
     * Và lưu lại thông tin thẻ đang thuê xe.
     *
     * Nếu giao dịch thất bại thì sẽ đưa ra thông báo lỗi và không lưu lại thông tin.
     * @param bike : Xe khách hàng muốn thuê
     * @param card : thẻ giao dịch
     * @return bike
     */
    public static String processRentBike(Card card, Bike bike){
        System.out.println("Xử lý giao dịch.");

        // chuyen bike code thanh rental code
        rentalCode = bike.convertBikeCodeToBarCode(bike.getBikeCode());
        int deposit = bike.calculateDeposit();
        // xu ly giao dich
        IInterbank interbank = new InterbankSubsysController();
        String code = interbank.processTransaction(card, deposit, "pay", "Giao dịch trừ tiền cọc");
        //Neu thanh cong thi luu giao dich thanh toan, giao dich thue xe, update trang thai xe, trang thai nguoi dung
        //luu thong tin the dang thue


        if (code.equals("00")){
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            DateFormat day = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat time = new SimpleDateFormat("HH:mm:ss");
            DateFormat daytime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


            //RentBikeScreen.rent = true;

            //luu giao dich thue xe
            RentBikeTransaction rentBikeTransaction = new RentBikeTransaction(
                    rentalCode, bike.getBikeCode(), bike.getType(),
                    -1, card.getOwner(), daytime.format(date),
                    "", deposit);

            rentBikeTransaction.saveRentBikeTransaction();

            //luu giao dich tru tien coc
            PaymentTransaction paymentTransaction = new PaymentTransaction(
                    rentalCode, card.getCardCode(), card.getOwner(),
                    "Giao dịch trừ tiền cọc", deposit,
                    time.format(date), day.format(date));
            paymentTransaction.savePaymentTransaction();

            // cap nhat trang thai xe
            bike.updateInUseAndDockID(true, bike.getDockID());


        }
        return code;
    }
}