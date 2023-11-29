package com.cs4125.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cs4125.shop.model.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserCacheService {
    private static final String FILE_PATH = "src/main/resources/data/user_data.dat";
    private Map<String, User> userCache = new HashMap<>();

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        // Hash the password before saving.
        user.setHashedPassword(passwordEncoder.encode(user.getRawPassword()));

        userCache.put(user.getEmail(), user);
        saveUsersToFile();
    }

    public User getUserByEmail(String email) {
        loadUsersFromFile();
        return userCache.get(email);
    }

    private void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(userCache);
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadUsersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object obj = ois.readObject();
            if (obj instanceof Map) {
                userCache = (Map<String, User>) obj;
            }
        } catch (FileNotFoundException e) {
            // If the file doesn't exist, it will be created when saving users
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
