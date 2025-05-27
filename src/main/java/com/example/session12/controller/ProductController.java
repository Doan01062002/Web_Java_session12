package com.example.session12.controller;

import com.example.session12.dao.ProductDAO;
import com.example.session12.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductController extends HttpServlet {
    private ProductDAO dao = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/views/product-form.jsp").forward(req, resp);
                break;
            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                Product p = dao.findById(id);
                req.setAttribute("product", p);
                req.getRequestDispatcher("/views/product-form.jsp").forward(req, resp);
                break;
            case "delete":
                dao.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("products");
                break;
            default:
                List<Product> products = dao.getAll();
                req.setAttribute("list", products);
                req.getRequestDispatcher("/views/product-list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        int id = (idStr == null || idStr.isEmpty()) ? 0 : Integer.parseInt(idStr);
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        String quantityStr = req.getParameter("quantity");
        String image = req.getParameter("image");

        // Validate input
        String errorMsg = null;
        double price = 0;
        int quantity = 0;

        if (name == null || name.trim().isEmpty()) {
            errorMsg = "Tên sản phẩm không được để trống!";
        } else {
            try {
                price = Double.parseDouble(priceStr);
                if (price <= 0) {
                    errorMsg = "Giá phải lớn hơn 0!";
                }
            } catch (NumberFormatException e) {
                errorMsg = "Giá phải là số hợp lệ!";
            }
            try {
                quantity = Integer.parseInt(quantityStr);
                if (quantity < 0) {
                    errorMsg = "Số lượng không được âm!";
                }
            } catch (NumberFormatException e) {
                errorMsg = "Số lượng phải là số nguyên!";
            }
        }

        if (errorMsg != null) {
            req.setAttribute("error", errorMsg);
            Product p = new Product(id, name, price, quantity, image);
            req.setAttribute("product", p);
            req.getRequestDispatcher("/views/product-form.jsp").forward(req, resp);
            return;
        }

        Product p = new Product(id, name, price, quantity, image);

        if (id == 0) {
            dao.insert(p);
        } else {
            dao.update(p);
        }
        resp.sendRedirect("products");
    }
}
