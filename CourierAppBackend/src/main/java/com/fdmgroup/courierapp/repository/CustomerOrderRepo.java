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
            + " ON co.customer_id = c.account_id"
            + " WHERE c.account_id=?1"
            + " ORDER BY co.order_date DESC",
            nativeQuery = true)
    List<CustomerOrder> findAllWithCustomerIdDesc(long customerId);

    @Query(value = "SELECT DISTINCT co.* FROM customer_order co"
            + " INNER JOIN trip tr"
            + " ON tr.customer_order_id = co.id"
            + " INNER JOIN courier cr"
            + " ON tr.courier_id = cr.account_id"
            + " WHERE cr.account_id=?1"
            + " ORDER BY co.order_date DESC",
            nativeQuery = true)
    List<CustomerOrder> findAllWithCourierIdDesc(long courierId);
}
