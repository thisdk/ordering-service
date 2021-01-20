package io.thisdk.github.ordering.dao.impl;

import io.thisdk.github.ordering.bean.CmsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class UserAccountDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * 根据用户账号查询用户账号对象
     *
     *            用户账号
     * @return
     */
    public CmsUser getUserInfo(String username,String password) {
        Query query = new Query();
        query.addCriteria(Criteria.where(username).is(username));
        query.addCriteria(Criteria.where(password).is(password));
        return mongoTemplate.findOne(query,CmsUser.class);
    }
}
