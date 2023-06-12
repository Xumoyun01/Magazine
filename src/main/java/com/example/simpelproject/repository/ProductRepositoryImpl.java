package com.example.simpelproject.repository;

import com.example.simpelproject.model.Product;
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
public class ProductRepositoryImpl {
    private final EntityManager entityManager;

    public Page<Product> getAdvancedSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }

        StringBuilder builder = buildParams(params);

        String strQuery = "select u from Product u where 1=1";
        String countQuery = "select count(productId) from Product u where 1=1";

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

        if (params.containsKey("productId")) {
            builder.append(params.get(" AND productId=: productId"));
        }

        if (params.containsKey("prodName")) {
            builder.append(params.get(" AND prodName=:prodName"));
        }

        if (params.containsKey("receivedPrice")) {
            builder.append(params.get(" AND receivedPrice= :receivedPrice"));
        }

        if (params.containsKey("prodMass")) {
            builder.append(params.get(" AND prodMass=:prodMass"));
        }

        if (params.containsKey("sellingPrice")) {
            builder.append(params.get(" AND sellingPrice=:sellingPrice"));
        }

        if (params.containsKey("amount")) {
            builder.append(params.get(" AND amount=:amount"));
        }

        return builder;
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("productId")) {
            query.setParameter(":productId", params.get("productId"));
        }
        if (params.containsKey("prodName")) {
            query.setParameter(":prodName", params.get("prodName"));
        }

        if (params.containsKey("receivedPrice")) {
            query.setParameter(":receivedPrice", params.get("receivedPrice"));
        }

        if (params.containsKey("prodMass")) {
            query.setParameter(":prodMass", params.get("prodMass"));
        }

        if (params.containsKey("sellingPrice")) {
            query.setParameter(":sellingPrice", params.get("sellingPrice"));
        }

        if (params.containsKey("amount")) {
            query.setParameter(":amount", params.get("amount"));
        }

    }
}
