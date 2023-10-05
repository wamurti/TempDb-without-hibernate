package com.example.temperaturdbapp.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Time;
import java.util.Date;


@Data
@NoArgsConstructor
public class DbModel {
    int id;
    float temperature;
    Date date;
    Time time;
}
