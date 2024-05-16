package com.fdmgroup.courierapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fdmgroup.courierapp.model.CustomerOrder;

import java.util.List;

@Repository
public interface CustomerOrderRepo extends JpaRepository<CustomerOrder,Long> {

    @Query(value = "SELECT co.* FROM customer_order co"
            + " INNER JOIN customer c"
            + " WHERE co.customer_id = c.account_id"
            + " AND c.account_id=?1"
            + " ORDER BY co.order_date DESC",
            nativeQuery = true)
    List<CustomerOrder> findAllWithCustomerIdDesc(long customerId);

    @Query(value = "SELECT co.* FROM customer_order co"
            + " INNER JOIN courier cr"
            + " WHERE co.courier_id = cr.account_id"
            + " AND cr.account_id=?1"
            + " ORDER BY co.order_date DESC",
            nativeQuery = true)
    List<CustomerOrder> findAllWithCourierIdDesc(long courierId);
}
