package entities;

import java.util.ArrayList;

public class BikeFactory {
    private BikeFactory() {
    }

    public static final Bike getBike(ArrayList<String> bike) {
        switch (bike.get(5)) {
            case "Xe đạp đơn thường":
                SingleBike singleBike = new SingleBike();
                singleBike.setBikeCode(Integer.parseInt(bike.get(0)));
                singleBike.setInUse(!bike.get(1).equals("0"));
                singleBike.setValue(Integer.parseInt(bike.get(2)));
                singleBike.setDockID(bike.get(4));
                singleBike.setLicensePlate(bike.get(3));
                singleBike.setType(bike.get(5));

                return singleBike;

            case "Xe đạp đôi thường":
                DoubleBike doubleBike = new DoubleBike();
                doubleBike.setBikeCode(Integer.parseInt(bike.get(0)));
                doubleBike.setInUse(!bike.get(1).equals("0"));
                doubleBike.setValue(Integer.parseInt(bike.get(2)));
                doubleBike.setDockID(bike.get(4));
                doubleBike.setLicensePlate(bike.get(3));
                doubleBike.setType(bike.get(5));

                return doubleBike;

            case "Xe đạp đơn điện":
                SingleElectricBike singleElectricBike = new SingleElectricBike();
                singleElectricBike.setBikeCode(Integer.parseInt(bike.get(0)));

                singleElectricBike.setInUse(!bike.get(1).equals("0"));
                singleElectricBike.setValue(Integer.parseInt(bike.get(2)));
                singleElectricBike.setDockID(bike.get(4));
                singleElectricBike.setLicensePlate(bike.get(3));
                singleElectricBike.setType(bike.get(5));
                singleElectricBike.setRemainBattery(Double.parseDouble(bike.get(6)));
                singleElectricBike.setMaxTime(Double.parseDouble(bike.get(7)));
                singleElectricBike.setMotor(bike.get(8));
                return singleElectricBike;
            default:
                throw new IllegalArgumentException("This bike type is unsupported");
        }
    }
}
