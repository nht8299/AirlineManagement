package com.example.demo.service;

import com.example.demo.entity.Airplane;

import java.util.List;
import java.util.Optional;

public interface AirplaneService {

    Airplane saveAirplane(Airplane airplane);

    List<Airplane> getAll();

    Optional<Airplane> findAirplaneById(Integer id);

    void deleteAirplaneById(Integer id);

    Airplane saveAirPlane (Airplane airplane);



    //Cho biết các loại máy bay có tầm bay lớn hơn 10,000km.
    public List<Airplane> findAirplaneByFlyingDistanceGreaterThan(Integer flyingDistance);

    //7.	Có bao nhiêu loại máy báy Boeing.
    public int findHowManyAirplaneByAirplaneTypeContaining(String typeOfAirplane);
}
