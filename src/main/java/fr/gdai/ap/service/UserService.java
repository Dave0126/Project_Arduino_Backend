package fr.gdai.ap.service;

import fr.gdai.ap.domain.User;

public interface UserService {
    boolean register(User user);
    void addProductByUser(String barcode);
}
