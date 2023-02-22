package dataaccess;

import connection.MySQLDB;

/**
 * Lưu hóa đơn ngân hàng vào cơ sở dữ liệu
 */
public class PaymentTransactionDA {
    /**
     * Lưu hóa đơn vào cơ sở dữ liệu
     * @param rentalCode: mã thuê xe
     * @param cardCode: mã thẻ
     * @param owner: tên chủ thẻ
     * @param transactionContent: nội dung giao dịch
     * @param amount: lượng tiền giao dịch
     * @param time: Thời gian giao dịch
     * @param day: ngày diễn ra giao dịch
     */
    public static void save(String rentalCode, String cardCode, String owner, String transactionContent,long amount, String time, String day){
        String command = "INSERT INTO paymenttransaction " +
                "VALUES " +
                '(' + '\'' + rentalCode + '\'' + ", " +
                '\'' + cardCode + '\'' + ", " +
                '\'' + owner + '\'' + ", " +
                '\'' + transactionContent + '\'' + ", " +
                amount + ", " +
                '\'' + time + '\'' + ", " +
                '\'' + day + '\'' + ')';
        MySQLDB.execute(command);
    }
}
