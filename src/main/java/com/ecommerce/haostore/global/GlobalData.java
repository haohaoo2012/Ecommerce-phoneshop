package com.ecommerce.haostore.global;

import com.ecommerce.haostore.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    //tao bien toan cuc
    public static List<Product> cart;

    static {
        cart = new ArrayList<>();
    }

}
