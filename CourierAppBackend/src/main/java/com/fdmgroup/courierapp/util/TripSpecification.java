package com.fdmgroup.courierapp.util;

import com.fdmgroup.courierapp.model.Party;
import com.fdmgroup.courierapp.model.PartyEnum;
import com.fdmgroup.courierapp.model.RouteEnum;
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

                            Expression<Object> partyTypeExpression = criteriaBuilder.selectCase()
                                    .when(criteriaBuilder.equal(root.get("route"), RouteEnum.INBOUND), PartyEnum.SENDER)
                                    .when(criteriaBuilder.equal(root.get("route"), RouteEnum.OUTBOUND), PartyEnum.RECIPIENT);

                            Predicate partyTypePredicate = criteriaBuilder.equal(join2.get("partyType"), partyTypeExpression);
                            Predicate regionPredicate = criteriaBuilder.equal(join3.get("region"), columnValue);
                            predicates.add(criteriaBuilder.and(partyTypePredicate, regionPredicate));
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
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
