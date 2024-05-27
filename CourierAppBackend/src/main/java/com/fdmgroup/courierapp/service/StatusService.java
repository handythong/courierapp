package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.model.Status;
import com.fdmgroup.courierapp.repository.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    @Autowired
    StatusRepo statusRepo;

    public Status createStatus(Status newStatus) {
        return statusRepo.save(newStatus);
    }
}
