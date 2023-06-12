package com.example.simpelproject.repository;

import com.example.simpelproject.model.Loaner;
import com.example.simpelproject.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class LoanerRepositoryImpl {
    private final EntityManager entityManager;

    public Page<Loaner> getAdvancedSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }

        StringBuilder builder = buildParams(params);

        String strQuery = "select u from Loaner u where 1=1";
        String countQuery = "select count(loanerId) from Loaner u where 1=1";

        Query query = entityManager.createQuery(strQuery + builder);
        Query queryTwo = entityManager.createQuery(countQuery + builder);

        setParams(query, params);
        setParams(queryTwo, params);

        query.setMaxResults(size);
        query.setFirstResult(size * page);
        long totalElements = Long.parseLong(queryTwo.getSingleResult().toString());

        return new PageImpl<>(query.getResultList(), PageRequest.of(page, size), totalElements);
    }

    private StringBuilder buildParams(Map<String, String> params) {
        StringBuilder builder = new StringBuilder();

        if (params.containsKey("loanerId")) {
            builder.append(params.get(" AND loanerId=: loanerId"));
        }
        if (params.containsKey("totalPrice")) {
            builder.append(params.get(" AND totalPrice=:totalPrice"));
        }


        return builder;
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("loanerId")) {
            query.setParameter(":loanerId", params.get("loanerId"));
        }
        if (params.containsKey("totalPrice")) {
            query.setParameter(":totalPrice", params.get("totalPrice"));
        }

    }
}
