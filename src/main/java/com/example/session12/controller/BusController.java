package com.example.session12.controller;

import com.example.session12.dao.BusDAO;
import com.example.session12.dao.SeatDAO;
import com.example.session12.model.Bus;
import com.example.session12.model.Seat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bus")
public class BusController {
    BusDAO busDAO = new BusDAO();
    SeatDAO seatDAO = new SeatDAO();

    @GetMapping("/list")
    public String list(Model model) throws Exception {
        model.addAttribute("buses", busDAO.getAll());
        return "listBus";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("bus", new Bus());
        return "addBus";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Bus bus, BindingResult result, Model model) throws Exception {
        if (bus.getLicensePlate().isEmpty() || bus.getBusType().isEmpty() || bus.getRowSeat() <= 0 || bus.getColSeat() <= 0) {
            model.addAttribute("error", "Vui lòng nhập đầy đủ thông tin và số ghế hợp lệ!");
            return "addBus";
        }
        busDAO.addBus(bus);
        return "redirect:/bus/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") int id, Model model) throws Exception {
        Bus bus = busDAO.getById(id);
        List<Seat> seats = seatDAO.getByBusId(id);
        model.addAttribute("bus", bus);
        model.addAttribute("seats", seats);
        return "detailBus";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) throws Exception {
        busDAO.deleteBus(id);
        return "redirect:/bus/list";
    }

    // Hiển thị form sửa
    @GetMapping("/edit")
    public String editForm(@RequestParam("id") int id, Model model) throws Exception {
        Bus bus = busDAO.getById(id);
        model.addAttribute("bus", bus);
        return "editBus";
    }

    // Xử lý cập nhật thông tin xe
    @PostMapping("/update")
    public String update(@ModelAttribute Bus bus, BindingResult result, Model model) throws Exception {
        if (bus.getLicensePlate().isEmpty() || bus.getBusType().isEmpty() || bus.getRowSeat() <= 0 || bus.getColSeat() <= 0) {
            model.addAttribute("error", "Vui lòng nhập đầy đủ thông tin và số ghế hợp lệ!");
            return "editBus";
        }

        bus.setTotalSeat(bus.getRowSeat() * bus.getColSeat()); // Cập nhật total seat
        busDAO.updateBus(bus);
        return "redirect:/bus/list";
    }

}
