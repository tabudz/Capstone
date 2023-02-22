package businesslogic.controller;

import dataaccess.CardDA;

import java.util.ArrayList;

/**
 * Kiem tra xem the co dang duoc su dung hay khong
 */
public class CheckCardInUseController {
    /**Check card
     * @param cardCode chua thong tin the nguoi dung
     * @return ket qua kiem tra xem the co valid hay khong
     */
    public boolean checkCardInUse(String cardCode){
        boolean check = false;
        ArrayList<ArrayList<String>> cardInUse = CardDA.getAllCardInUse();
        for (ArrayList<String> card : cardInUse){
            if (card.get(0).equals(cardCode)){
            	System.out.println(card.get(0) + " " + cardCode);
                check = true;
                break;
            }
        }
        return check;
    }
}
