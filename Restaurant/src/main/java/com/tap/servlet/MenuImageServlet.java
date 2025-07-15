package com.tap.servlet;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/menuImage")
public class MenuImageServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/sepoctdb";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing menu ID");
            return;
        }

        int menuId;
        try {
            menuId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid menu ID");
            return;
        }

        String sql = "SELECT image_path FROM menu WHERE menu_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, menuId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                byte[] imageBytes = rs.getBytes("image_path");
                if (imageBytes != null && imageBytes.length > 0) {
                    resp.setContentType("image/jpeg");
                    resp.setContentLength(imageBytes.length);
                    resp.getOutputStream().write(imageBytes);
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found");
                }
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Menu item not found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}
