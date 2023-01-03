package fr.gdai.ap.service;

import fr.gdai.ap.domain.MyProduct;
import org.springframework.stereotype.Service;

public interface MyProductService {
    MyProduct transferOpenStackProduct2MyProduct(String barcode);
}
