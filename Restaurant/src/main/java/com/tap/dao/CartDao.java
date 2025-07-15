package com.tap.dao;

import com.tap.model.CartItem;
import java.sql.SQLException;
import java.util.List;

public interface CartDao {
    boolean addCartItem(CartItem item) throws SQLException;
    boolean updateCartItem(CartItem item) throws SQLException;
    boolean deleteCartItem(int id) throws SQLException;
    List<CartItem> getAllCartItems() throws SQLException;
}
