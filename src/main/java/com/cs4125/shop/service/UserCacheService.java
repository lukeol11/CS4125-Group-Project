package com.cs4125.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cs4125.shop.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserCacheService {
    private static final String FILE_PATH = "src/main/resources/data/user.txt"; // Changed the file name to "User.txt"
    private List<User> userCache = new ArrayList<>();

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        // Hash the password before saving.
        user.setHashedPassword(passwordEncoder.encode(user.getRawPassword()));

        userCache.add(user);
        saveUsersToFile();
    }

    public User getUserByEmail(String email) {
        loadUsersFromFile();
//        System.out.println(email);
        for(int i=0; i<userCache.size(); i++)
        {
//            System.out.println(userCache.get(i).getEmail());
            if(userCache.get(i).getEmail().equals(email))
                return userCache.get(i);
        }
        return null;
//        return userCache.stream()
//                .filter(u -> u.getEmail().equals(email))
//                .findFirst()
//                .orElse(null);
    }

    private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : userCache) {
                writer.write("Username: " + user.getUsername() + "\n");
                writer.write("Email: " + user.getEmail() + "\n");
                writer.write("Hashed Password: " + user.getHashedPassword() + "\n");
                writer.write("Raw Password: " + user.getRawPassword() + "\n");
                writer.write("Loyalty Points: " + user.getLoyaltyPoints() + "\n");
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Username: ")) {
                    User user = new User();
                    user.setUsername(line.substring("Username: ".length()).trim());
                    user.setEmail(reader.readLine().substring("Email: ".length()).trim());
                    user.setHashedPassword(reader.readLine().substring("Hashed Password: ".length()).trim());
                    user.setRawPassword(reader.readLine().substring("Raw Password: ".length()).trim());
                    user.addLoyaltyPoints(Integer.parseInt(reader.readLine().substring("Loyalty Points: ".length()).trim()));
                    reader.readLine(); // Read the empty line

                    userCache.add(user);
                }
            }
//            System.out.println("Size of User :" + userCache.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
