package org.yugh.coral.event.boot.passenger;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface PassengerRepository extends JpaRepository<Passenger, Long>, CustomPassengerRepository {

    Passenger findFirstByOrderBySeatNumberAsc();

    Passenger findTopByOrderBySeatNumberAsc();

    List<Passenger> findByOrderBySeatNumberAsc();
    
    List<Passenger> findByLastNameOrderBySeatNumberAsc(String lastName);
    
    List<Passenger> findByLastName(String lastName, Sort sort);

    List<Passenger> findByFirstNameIgnoreCase(String firstName);

}
