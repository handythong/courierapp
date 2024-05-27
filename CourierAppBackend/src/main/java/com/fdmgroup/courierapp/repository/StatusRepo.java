package com.fdmgroup.courierapp.repository;

import com.fdmgroup.courierapp.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepo extends JpaRepository<Status, Long> {

}
