package com.example.session12.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    private int id;
    private String nameSeat;
    private double price;
    private int busId;
    private String status;
}
