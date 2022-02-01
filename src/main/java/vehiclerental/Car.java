package vehiclerental;

import java.time.LocalTime;

public class Car implements Rentable{

    private String carId;
    private LocalTime rentingTime;
    private int minutesPrice;

    public Car(String carId, int minutesPrice) {
        this.carId = carId;
        this.minutesPrice = minutesPrice;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) (minutesPrice * minutes);
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
    }

    @Override
    public void rent(LocalTime time) {
        rentingTime = time;

    }

    @Override
    public void closeRent() {
        rentingTime = null;
    }
}