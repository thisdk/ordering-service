package io.thisdk.github.ordering.util.dao.impl;

import io.thisdk.github.ordering.bean.CmsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class UserAccountDao {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserAccountDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public CmsUser getUserInfo(String username, String password) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        query.addCriteria(Criteria.where("password").is(password));
        return mongoTemplate.findOne(query, CmsUser.class);
    }

}
