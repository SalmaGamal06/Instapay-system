import java.io.*;
import java.util.ArrayList;

//Strategy Pattern
public interface Storage<T> {
    public boolean add(T object) throws IOException;

    public T read(String uniqueIdentifier) throws FileNotFoundException;

    public ArrayList<T> readAll() throws FileNotFoundException;
}


class InMemoryStorage implements Storage<User> {
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
    public User read(String uniqueIdentifier) {
        String username;
        for (User user : users) {
            username = user.getUserName();
            if (username.equals(uniqueIdentifier)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public ArrayList<User> readAll() {
        return users;
    }

    public boolean isMobileNumberExist(String mobileNumber) {
        for (User user : readAll()) {
            if (user.getMobileNumber().equals(mobileNumber)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserNameExist(String userName) {
        for (User user : readAll()) {
            if (user.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }
}

class FileStorage implements Storage<User> {
    @Override
    public boolean add(User user) throws IOException {
        try {
            FileWriter writer = new FileWriter("users.txt", true);
            if (user.getTypeOfInstapayAccount().equals("Bank")) {
                writer.write(user.getUserName() + " " + user.getPassword() + " " + user.getMobileNumber() + " " + user.getTypeOfInstapayAccount() + " " + user.getProvider() + " " + user.getBankAccountNumber() + "\n");
            } else {
                writer.write(user.getUserName() + " " + user.getPassword() + " " + user.getMobileNumber() + " " + user.getTypeOfInstapayAccount() + " " + user.getProvider() + " 0" + "\n");
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User read(String uniqueIdentifier) throws FileNotFoundException {
        FileReader reader = new FileReader("users.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(" ");
                if (words[0].equals(uniqueIdentifier)) {
                    User user = new User(words[0], words[1], words[2], words[3], words[4], words[5]);
                    return user;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User readByMobile(String mobile) throws FileNotFoundException {
        FileReader reader = new FileReader("users.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(" ");
                if (words[2].equals(mobile)) {
                    User user = new User(words[0], words[1], words[2], words[3], words[4], words[5]);
                    return user;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User readByAccountNum(String accountNum) throws FileNotFoundException {
        FileReader reader = new FileReader("users.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(" ");
                if (words.length == 6) {
                    if (words[5].equals(accountNum)) {
                        User user = new User(words[0], words[1], words[2], words[3], words[4], words[5]);
                        user.setBankAccountNumber(words[5]);
                        return user;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<User> readAll() throws FileNotFoundException {
        FileReader reader = new FileReader("users.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        ArrayList<User> users = new ArrayList<>();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(" ");
                User user = new User(words[0], words[1], words[2], words[3], words[4], words[5]);
                users.add(user);
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isMobileNumberExist(String mobileNumber) throws FileNotFoundException {
        for (User user : readAll()) {
            if (user.getMobileNumber().equals(mobileNumber)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserNameExist(String userName) throws FileNotFoundException {
        for (User user : readAll()) {
            if (user.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }
}
