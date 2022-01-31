package vehiclerental;

import java.time.LocalTime;

public class Bike implements Rentable{

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
        return (int) (15 * minutes);
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
    }

    @Override
    public void rent(LocalTime time) {

    }

    @Override
    public void closeRent() {

    }

    @Override
    public int compareTo(Rentable o1) {

        Bike o2;
        return o1.getRentingTime()-(Rentable)o2.getRentingTime();
    }
}
