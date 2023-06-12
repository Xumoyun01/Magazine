package com.example.simpelproject.repository;

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
public class UserRepositoryImpl {

    private final EntityManager entityManager;

    public Page<User> getAdvancedSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }

        StringBuilder builder = buildParams(params);

        String strQuery = "select u from User u where 1=1";
        String countQuery = "select count(id) from User u where 1=1";

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

        if (params.containsKey("middleName")) {
            builder.append(params.get(" AND middleName=:middleName"));
        }

        if (params.containsKey("lastName")) {
            builder.append(params.get(" AND lastName= :lastName"));
        }

        if (params.containsKey("firstName")) {
            builder.append(params.get(" AND firstName=:firstName"));
        }

        if (params.containsKey("userName")) {
            builder.append(params.get(" AND userName=:userName"));
        }

        if (params.containsKey("borrowName")) {
            builder.append(params.get(" AND borrowName=:borrowName"));
        }

        if (params.containsKey("phoneNumber")) {
            builder.append(params.get(" AND phoneNumber=:phoneNumber"));
        }
        if (params.containsKey("passportSeries")) {
            builder.append(" AND passportSeries =:passportSeries");
        }
        if (params.containsKey("firstAddress")) {
            builder.append(" AND firstAddress=:firstAddress");
        }
        if (params.containsKey("birthDate")) {
            builder.append(" AND birthDate=:birthDate");
        }
        return builder;
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("id")) {
            query.setParameter(":id", params.get("id"));
        }
        if (params.containsKey("middleName")) {
            query.setParameter(":middleName", params.get("middleName"));
        }

        if (params.containsKey("lastName")) {
            query.setParameter(":lastName", params.get("lastName"));
        }

        if (params.containsKey("firstName")) {
            query.setParameter(":firstName", params.get("firstName"));
        }

        if (params.containsKey("userName")) {
            query.setParameter(":userName", params.get("userName"));
        }

        if (params.containsKey("borrowName")) {
            query.setParameter(":borrowName", params.get("borrowName"));
        }
        if (params.containsKey("phoneNumber")) {
            query.setParameter(":phoneNumber", params.get("phoneNumber"));
        }

        if (params.containsKey("passportSeries")) {
            query.setParameter(":passportSeries", params.get("passportSeries"));
        }

        if (params.containsKey("firstAddress")) {
            query.setParameter(":firstAddress", params.get("firstAddress"));
        }

        if (params.containsKey("monthlyPrice")) {
            query.setParameter(":monthlyPrice", params.get("monthlyPrice"));
        }



    }
}
