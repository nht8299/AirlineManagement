package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.Employee;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService{
    @Autowired
    CertificateRepository certificateRepository;

    @Override
    public Certificate saveCertificate(Certificate certificate)
    {
        return certificateRepository.save(certificate);
    }

    @Override
    public List<Certificate> getAll() {
        return certificateRepository.findAll();
    }

    @Override
    public Optional<Certificate> findCertificateById(Integer id) {
        return certificateRepository.findById(id);
    }

    @Override
    public void deleteCertificateById(Integer id) {
        certificateRepository.deleteById(id);
    }


    @Override
    public List<Certificate> findAllThePilotsWhoCanFlyATypeOfAirplane(String airplaneType) {
        return certificateRepository.findAllThePilotsWhoCanFlyATypeOfAirplane(airplaneType);
    }

    // 10.	Cho biết các nhân viên có thể lái máy bay có mã số 747.
    @Override
    public List<Employee> findEmployeeByAirplaneAirplaneId(Integer airplaneId)
    {
        List<Employee> listOfEmployeesWhoCanFlyAnAirplane = new ArrayList<>();
        for (Certificate certificate: certificateRepository.findEmployeeByAirplaneAirplaneId(airplaneId)
             ) {
            listOfEmployeesWhoCanFlyAnAirplane.add(certificate.getEmployee());
        }
        return listOfEmployeesWhoCanFlyAnAirplane;
    }

    //11.	Cho biết mã số của các loại máy bay mà nhân viên có họ Nguyễn có thể lái.
    @Override
    public List<Certificate> findAirplaneByEmployeeNameContaining(String name)
    {
        return certificateRepository.findAirplaneByEmployeeNameContaining(name);
    }

    //12.	Cho biết mã số của các phi công vừa lái được Boeing vừa lái được Airbus.
    @Override
    public List<Integer> findAllThePilotsWhoCanFlyTwoTypesOfAirplanes()
    {
        return certificateRepository.findAllThePilotsWhoCanFlyTwoTypesOfAirplanes();
    }

//    @Override
//    public List<Certificate> findPilotsWhoCanFlyBoeing(@Param("airplaneType1") String airplaneType1)
//    {
//        return certificateRepository.findPilotsWhoCanFlyBoeing(airplaneType1);
//    }


}
