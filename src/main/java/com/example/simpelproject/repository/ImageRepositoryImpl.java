package com.example.simpelproject.repository;

import com.example.simpelproject.model.Image;
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
public class ImageRepositoryImpl {
    private final EntityManager entityManager;

    public Page<Image> getAdvancedSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }

        StringBuilder builder = buildParams(params);

        String strQuery = "select u from Image u where 1=1";
        String countQuery = "select count(id) from Image u where 1=1";

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

        if (params.containsKey("id")) {
            builder.append(params.get(" AND id=: id"));
        }

        if (params.containsKey("path")) {
            builder.append(params.get(" AND path=:path"));
        }

        if (params.containsKey("type")) {
            builder.append(params.get(" AND type= :type"));
        }

        if (params.containsKey("size")) {
            builder.append(params.get(" AND size=:size"));
        }

        return builder;
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("id")) {
            query.setParameter(":id", params.get("id"));
        }
        if (params.containsKey("path")) {
            query.setParameter(":path", params.get("path"));
        }

        if (params.containsKey("type")) {
            query.setParameter(":type", params.get("type"));
        }

        if (params.containsKey("size")) {
            query.setParameter(":size", params.get("size"));
        }

    }
}
