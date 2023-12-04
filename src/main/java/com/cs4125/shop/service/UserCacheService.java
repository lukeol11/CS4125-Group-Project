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
    private static final String FILE_PATH = "src/main/resources/data/user.txt";
    private static UserCacheService instance;

    private List<User> userCache = new ArrayList<>();

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserCacheService() {
        // Added this private constructor to prevent external instantiation.
    }

    public static synchronized UserCacheService getInstance() {
        if (instance == null) {
            instance = new UserCacheService();
        }
        return instance;
    }

    public void registerUser(User user) {
        userCache.add(user);
        saveUsersToFile();
    }

    public User getUserByEmail(String email) {
        loadUsersFromFile();
        for(int i=0; i<userCache.size(); i++)
        {
            if(userCache.get(i).getEmail().equals(email))
                return userCache.get(i);
        }
        return null;
    }

    private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            for (User user : userCache) {
                writer.write("Username: " + user.getUsername() + "\n");
                writer.write("Email: " + user.getEmail() + "\n");
                writer.write("Hashed Password: " + user.getHashedPassword() + "\n");
                writer.write("Raw Password: " + user.getRawPassword() + "\n");
                writer.write("Loyalty Points: " + user.getLoyaltyPoints() + "\n");
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace(); // NOSONAR
        }
    }

    private void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith("Username: ")) {
                    User user = new User();
                    user.setUsername(currentLine.substring("Username: ".length()).trim());
                    user.setEmail(reader.readLine().substring("Email: ".length()).trim());
                    user.setHashedPassword(reader.readLine().substring("Hashed Password: ".length()).trim());
                    user.setRawPassword(reader.readLine().substring("Raw Password: ".length()).trim());
                    user.addLoyaltyPoints(Integer.parseInt(reader.readLine().substring("Loyalty Points: ".length()).trim()));
                    String line = reader.readLine(); // Read the empty line.
                    // Store "line" for later use.

                    userCache.add(user);
                }
            }
        }
            catch (IOException e) {
                e.printStackTrace(); // NOSONAR
        }
    }
}
