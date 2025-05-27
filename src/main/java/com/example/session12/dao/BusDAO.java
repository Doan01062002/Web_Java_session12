package com.example.session12.dao;

import com.example.session12.config.ConnectionDB;
import com.example.session12.model.Bus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {
    public void addBus(Bus bus) throws Exception {
        Connection conn = ConnectionDB.getConnection();
        String sql = "INSERT INTO bus (license_plate, bus_type, row_seat, col_seat, total_seat, image) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, bus.getLicensePlate());
        ps.setString(2, bus.getBusType());
        ps.setInt(3, bus.getRowSeat());
        ps.setInt(4, bus.getColSeat());
        ps.setInt(5, bus.getRowSeat() * bus.getColSeat());
        ps.setString(6, bus.getImage());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            int busId = rs.getInt(1);
            new SeatDAO().generateSeats(bus, busId);
        }
        conn.close();
    }

    public List<Bus> getAll() throws Exception {
        List<Bus> list = new ArrayList<>();
        Connection conn = ConnectionDB.getConnection();
        String sql = "SELECT * FROM bus";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while (rs.next()) {
            Bus b = new Bus(
                    rs.getInt("id"),
                    rs.getString("license_plate"),
                    rs.getString("bus_type"),
                    rs.getInt("row_seat"),
                    rs.getInt("col_seat"),
                    rs.getInt("total_seat"),
                    rs.getString("image")
            );
            list.add(b);
        }
        conn.close();
        return list;
    }

    public Bus getById(int id) throws Exception {
        Connection conn = ConnectionDB.getConnection();
        String sql = "SELECT * FROM bus WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Bus(
                    rs.getInt("id"),
                    rs.getString("license_plate"),
                    rs.getString("bus_type"),
                    rs.getInt("row_seat"),
                    rs.getInt("col_seat"),
                    rs.getInt("total_seat"),
                    rs.getString("image")
            );
        }
        return null;
    }

    public void deleteBus(int id) throws Exception {
        Connection conn = ConnectionDB.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM bus WHERE id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        conn.close();
    }

    public void updateBus(Bus b) throws SQLException {
        String sql = "UPDATE bus SET license_plate=?, bus_type=?, row_seat=?, col_seat=?, total_seat=?, image=? WHERE id=?";
        Connection conn = ConnectionDB.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, b.getLicensePlate());
        ps.setString(2, b.getBusType());
        ps.setInt(3, b.getRowSeat());
        ps.setInt(4, b.getColSeat());
        ps.setInt(5, b.getRowSeat() * b.getColSeat());
        ps.setString(6, b.getImage());
        ps.setInt(7, b.getId());
        ps.executeUpdate();
    }
}
