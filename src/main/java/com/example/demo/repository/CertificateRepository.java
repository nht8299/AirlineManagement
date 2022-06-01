package com.example.demo.repository;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {

    //9.	Cho biết mã số của các phi công lái máy báy Boeing.
    @Query(value = "SELECT c.* FROM Certificate c " +
                    "WHERE c.airplane_id IN (SELECT airplane_id FROM Airplane WHERE airplane_type = :airplaneType)", nativeQuery = true)
    List<Certificate> findAllThePilotsWhoCanFlyATypeOfAirplane(@Param("airplaneType") String airplaneType);

    // 10.	Cho biết các nhân viên có thể lái máy bay có mã số 747.
    List<Certificate> findEmployeeByAirplaneAirplaneId(Integer airplaneId);

    //11.	Cho biết mã số của các loại máy bay mà nhân viên có họ Nguyễn có thể lái.
    List<Certificate> findAirplaneByEmployeeNameContaining(String name);

    //12.	Cho biết mã số của các phi công vừa lái được Boeing vừa lái được Airbus.
    //    List<Certificate> findEmployeeByAirplaneAirplaneTypeContainingAndAirplaneAirplaneTypeContaining(String firstType, String secondType);

    @Query(value = "SELECT c.employee_id FROM Certificate c WHERE airplane_id IN (SELECT airplane_id FROM Airplane WHERE airplane_type LIKE 'Boeing') " +
            "INTERSECT SELECT c.employee_id FROM Certificate c WHERE airplane_id IN (SELECT airplane_id FROM Airplane WHERE airplane_type LIKE 'Airbus') ", nativeQuery = true)
    List<Integer> findAllThePilotsWhoCanFlyTwoTypesOfAirplanes();

//    @Query(value = "SELECT c.* FROM Certificate c WHERE c.airplane_id IN (SELECT a.airplane_id FROM Airplane a WHERE airplane_type = :airplaneType1) ",nativeQuery = true)
//    List<Certificate> findPilotsWhoCanFlyBoeing(@Param("airplaneType1") String airplaneType1);



//    @Query(value = "SELECT c.* FROM Certificate c WHERE airplane_id IN (SELECT airplane_id FROM Airplane WHERE airplane_type = :airplaneType1) ", nativeQuery = true)
//    List<Certificate> findPilotsWhoCanFly(@Param("airplaneType")String airplaneType);
}
