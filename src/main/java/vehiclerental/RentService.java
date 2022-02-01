package vehiclerental;

import java.time.LocalTime;
import java.util.*;

public class RentService{
//set-hashset nem kellett sorrend
    private Set<User> users = new HashSet<>();
    private Set<Rentable> rentables = new HashSet<>();

//    map - treemap nem kellett sorrend
    private Map<Rentable,User> actualRenting = new TreeMap<>();

    public Set<User> getUsers() {
        return users;
    }

    public Map<Rentable, User> getActualRenting() {
        return actualRenting;
    }

    public Set<Rentable> getRentables() {
        return rentables;
    }

    public void registerUser(User user) {
        if(isUserNameTake(user.getUserName())) {
            throw new UserNameIsAlreadyTakenException("Username is taken!");
        }
        users.add(user);
    }
//név duplikáció ellenőrzése
    private boolean isUserNameTake(String name){
        return  users.stream()
                .map(User::getUserName)
                .anyMatch(s -> s.equals(name));
    }
//itt lehetett duplikáció
    public void addRentable(Rentable rentable) {
        rentables.add(rentable);
    }

//ellenőrzi hogy max-ra van e elég pénz
    public void rent(User user, Rentable rentable, LocalTime localTime) {
        if(rentable.getRentingTime() != null || user.getBalance() < rentable.calculateSumPrice(180)) {
            throw new IllegalStateException("Username is taken!");
        }
// ha van rent el és beteszi a listába
        rentable.rent(localTime);
        actualRenting.put(rentable,user);

    }
//van e egyáltalán rent
    public void closeRent(Rentable rentable, int minutes) {
        if(!actualRenting.containsKey(rentable)) {
            throw new IllegalArgumentException("No renting!");
        }

//ha van rent akkor lekéri a felhasználót, majd levonja a pénzt,és lezárja a rent-et
        User user = actualRenting.get(rentable);
        user.minusBalance(rentable.calculateSumPrice(minutes));
        actualRenting.remove(rentable);
        rentable.closeRent();
    }
}
