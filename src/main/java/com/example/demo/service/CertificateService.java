package com.example.demo.service;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.Employee;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CertificateService {
    Certificate saveCertificate(Certificate certificate);


    List<Certificate>getAll();

    Optional<Certificate>findCertificateById(Integer id);

    void deleteCertificateById(Integer id);
    //9.	Cho biết mã số của các phi công lái máy báy Boeing.
    public List<Certificate> findAllThePilotsWhoCanFlyATypeOfAirplane(String airplaneType);

    // 10.	Cho biết các nhân viên có thể lái máy bay có mã số 747.
    public List<Employee> findEmployeeByAirplaneAirplaneId(Integer airplaneId);

    //11.	Cho biết mã số của các loại máy bay mà nhân viên có họ Nguyễn có thể lái.
    public List<Certificate> findAirplaneByEmployeeNameContaining(String name);

    //12.	Cho biết mã số của các phi công vừa lái được Boeing vừa lái được Airbus.
    public List<Integer> findAllThePilotsWhoCanFlyTwoTypesOfAirplanes();
//    public List<Certificate> findPilotsWhoCanFly(@Param("airplaneType")String airplaneType);

//    public List<Certificate> findPilotsWhoCanFlyBoeing(@Param("airplaneType1") String airplaneType1);
}
