package com.example.session12.dao;

import com.example.session12.config.ConnectionDB;
import com.example.session12.model.Bus;
import com.example.session12.model.Seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO {
    public void generateSeats(Bus bus, int busId) throws Exception {
        Connection conn = ConnectionDB.getConnection();
        String sql = "INSERT INTO seat(name_seat, price, bus_id) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        double price = switch (bus.getBusType().toUpperCase()) {
            case "VIP" -> 150000;
            case "LUXURY" -> 200000;
            default -> 100000;
        };

        for (int row = 0; row < bus.getRowSeat(); row++) {
            for (int col = 0; col < bus.getColSeat(); col++) {
                String name = (char)('A' + row) + String.valueOf(col + 1);
                ps.setString(1, name);
                ps.setDouble(2, price);
                ps.setInt(3, busId);
                ps.addBatch();
            }
        }
        ps.executeBatch();
        conn.close();
    }

    public List<Seat> getByBusId(int busId) throws Exception {
        Connection conn = ConnectionDB.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM seat WHERE bus_id=?");
        ps.setInt(1, busId);
        ResultSet rs = ps.executeQuery();
        List<Seat> list = new ArrayList<>();
        while (rs.next()) {
            Seat s = new Seat(
                    rs.getInt("id"),
                    rs.getString("name_seat"),
                    rs.getDouble("price"),
                    rs.getInt("bus_id"),
                    rs.getString("status")
            );
            list.add(s);
        }
        return list;
    }
}
