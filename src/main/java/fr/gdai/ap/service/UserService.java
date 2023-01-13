package fr.gdai.ap.service;

import fr.gdai.ap.domain.User;

public interface UserService {
    boolean register(User user);
    String addProductByUser(String name, String barcode);
}
