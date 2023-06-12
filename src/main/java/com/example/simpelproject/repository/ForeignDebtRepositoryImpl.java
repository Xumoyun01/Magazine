package com.example.simpelproject.repository;

import com.example.simpelproject.model.ForeignDebt;
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
public class ForeignDebtRepositoryImpl {
    private final EntityManager entityManager;

    public Page<ForeignDebt> getAdvancedSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }

        StringBuilder builder = buildParams(params);

        String strQuery = "select u from ForeignDebt u where 1=1";
        String countQuery = "select count(foreignId) from ForeignDebt u where 1=1";

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

        if (params.containsKey("foreignId")) {
            builder.append(params.get(" AND foreignId=: foreignId"));
        }

        if (params.containsKey("companyName")) {
            builder.append(params.get(" AND companyName=:companyName"));
        }

        if (params.containsKey("fullName")) {
            builder.append(params.get(" AND fullName= :fullName"));
        }

        if (params.containsKey("firstPhoneNumber")) {
            builder.append(params.get(" AND firstPhoneNumber=:firstPhoneNumber"));
        }

        if (params.containsKey("secondPhoneNumber")) {
            builder.append(params.get(" AND secondPhoneNumber=:secondPhoneNumber"));
        }

        return builder;
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("foreignId")) {
            query.setParameter(":foreignId", params.get("foreignId"));
        }
        if (params.containsKey("companyName")) {
            query.setParameter(":companyName", params.get("companyName"));
        }

        if (params.containsKey("fullName")) {
            query.setParameter(":fullName", params.get("fullName"));
        }

        if (params.containsKey("firstPhoneNumber")) {
            query.setParameter(":firstPhoneNumber", params.get("firstPhoneNumber"));
        }

        if (params.containsKey("secondPhoneNumber")) {
            query.setParameter(":secondPhoneNumber", params.get("secondPhoneNumber"));
        }

    }
}
