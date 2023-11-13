import java.util.ArrayList;

public interface Storage<T>{
    public boolean add(T object);
    public boolean delete(String uniqueIdentifier);
    public User get(String uniqueIdentifier);
}

public class InMemoryStorage implements Storage<User> {
    private ArrayList<User> users;

    public InMemoryStorage() {
        this.users = new ArrayList<>();
    }

    @Override
    public boolean add(User user) {
        for (User u : users) {
            if (u.getUserName().equals(user.getUserName())) {
                return false;
            }
        }
        users.add(user);
        return true;
    }

    @Override
    public boolean delete(String uniqueIdentifier) {
        for (User u : users) {
            if (u.getUserName().equals(uniqueIdentifier)) {
                users.remove(u);
                return true;
            }
        }
        return false;
    }

    @Override
    public User get(String uniqueIdentifier) {
        String username;
        for (User user : users) {
            username = user.getUserName();
            if (username.equals(uniqueIdentifier)) {
                return user;
            }
        }

        return null;
    }
}
