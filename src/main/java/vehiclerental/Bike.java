package vehiclerental;

import java.time.LocalTime;

public class Bike implements Rentable {

    private static final int PRICE_PER_MINUTE = 15;

    private String bikeId;
    private LocalTime rentingTime;

    public Bike(String bikeId) {
        this.bikeId = bikeId;
    }

    public String getBikeId() {
        return bikeId;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) (PRICE_PER_MINUTE * minutes);
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
