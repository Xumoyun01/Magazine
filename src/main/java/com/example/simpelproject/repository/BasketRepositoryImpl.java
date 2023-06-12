package com.example.simpelproject.repository;

import com.example.simpelproject.model.Basket;
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
public class BasketRepositoryImpl {
    private final EntityManager entityManager;

    public Page<Basket> getAdvancedSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }

        StringBuilder builder = buildParams(params);

        String strQuery = "select u from Basket u where 1=1";
        String countQuery = "select count(basketId) from Basket u where 1=1";

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

        if (params.containsKey("basketId")) {
            builder.append(params.get(" AND basketId=: basketId"));
        }

        if (params.containsKey("prodMass")) {
            builder.append(params.get(" AND prodMass=:prodMass"));
        }

        if (params.containsKey("prodPrice")) {
            builder.append(params.get(" AND prodPrice= :prodPrice"));
        }

        if (params.containsKey("totalPrice")) {
            builder.append(params.get(" AND totalPrice=:totalPrice"));
        }
        return builder;
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("basketId")) {
            query.setParameter(":basketId", params.get("basketId"));
        }
        if (params.containsKey("prodMass")) {
            query.setParameter(":prodMass", params.get("prodMass"));
        }

        if (params.containsKey("prodPrice")) {
            query.setParameter(":prodPrice", params.get("prodPrice"));
        }

        if (params.containsKey("totalPrice")) {
            query.setParameter(":totalPrice", params.get("totalPrice"));
        }



    }
}
