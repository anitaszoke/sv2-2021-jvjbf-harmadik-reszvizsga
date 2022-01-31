package vehiclerental;

import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RentService{

    private Collection<User> users;
    private Collection<Rentable> rentables;


    private Map<Rentable,User> actualRenting = new HashMap<>();

    public Collection<User> getUsers() {
        return users;
    }

    public Map<Rentable, User> getActualRenting() {
        return actualRenting;
    }

    public Collection<Rentable> getRentables() {
        return rentables;
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public void addRentable(Rentable rentable) {
        rentables.add(rentable);
    }


    public void rent(User user, Rentable rentable, LocalTime localTime) {
        if (user.getBalance() > rentable.calculateSumPrice(180)) {

        }
    }

    public void closeRent(Rentable rentable, int mintes) {
        actualRenting.get(getUsers().remove(rentable));
        actualRenting.remove(rentable);

    }
}
