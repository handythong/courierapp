package com.fdmgroup.courierapp.util;

import com.fdmgroup.courierapp.model.Trip;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class TripSpecification {
    //columnEqual() can be any name you want
    public static Specification<Trip> columnEqual(List<TripFilter> filters) {
        return new Specification<Trip>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Trip> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                for (TripFilter filter : filters)
                {
                    String columnKey = filter.getColumnKey();
                    Object columnValue = filter.getColumnValue();

                    switch (columnKey) {
                        case "region":
                            // Perform the join for the region column
                            Join<Object, Object> join1 = root.join("customerOrder");
                            Join<Object, Object> join2 = join1.join("parties");
                            Join<Object, Object> join3 = join2.join("address");
                            predicates.add(criteriaBuilder.equal(join3.get("region"), columnValue));
                            break;
                        case "tripDate":
                            try {
                                Date dateValue = new SimpleDateFormat("yyyy-MM-dd").parse((String) columnValue);
                                predicates.add(criteriaBuilder.equal(criteriaBuilder.function("DATE", Date.class, root.get(columnKey)), dateValue));
                            } catch (ParseException e) {
                                // Handle parsing error
                            }
                            break;
                        default:
                            // No join needed for other columns
                            predicates.add(criteriaBuilder.equal(root.get(columnKey), columnValue));
                            break;
                    }
//                    Predicate predicate = criteriaBuilder.equal(root.get(filter.getColumnKey()), filter.getColumnValue());
//                    predicates.add(predicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }; //columnEqual() function ends
    }
}
