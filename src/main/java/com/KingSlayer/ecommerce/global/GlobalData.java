package com.KingSlayer.ecommerce.global;

import com.KingSlayer.ecommerce.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Product> cart;

    static {
        cart = new ArrayList<>();
    }
}
