package com.mkyong.springdatamongodb.tutorial1;

import java.util.UUID;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for initializing MongoDB with sample data
 * <p>
 * For a complete reference to MongoDB
 * see http://www.mongodb.org/
 * <p>
 * For transactions, see http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/transaction.html
 *
 * @author Krams at {@link http://krams915@blogspot.com}
 */
@Transactional
public class InitService {

    protected static Logger logger = Logger.getLogger("service");

//    @Resource(name="mongoTemplate")
    private MongoTemplate mongoTemplate;

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void init() {
        // Populate our MongoDB database
        logger.debug("Init MongoDB users");

        // Drop existing collection
        mongoTemplate.dropCollection("mycollection");

        // Create new object
        Person p = new Person ();
        p.setPid(UUID.randomUUID().toString());
        p.setFirstName("John");
        p.setLastName("Smith");
        p.setMoney(1000.0);

        // Insert to db
        mongoTemplate.insert("mycollection", p);

        // Create new object
        p = new Person ();
        p.setPid(UUID.randomUUID().toString());
        p.setFirstName("Jane");
        p.setLastName("Adams");
        p.setMoney(2000.0);

        // Insert to db
        mongoTemplate.insert("mycollection", p);

        // Create new object
        p = new Person ();
        p.setPid(UUID.randomUUID().toString());
        p.setFirstName("Jeff");
        p.setLastName("Mayer");
        p.setMoney(3000.0);

        // Insert to db
        mongoTemplate.insert("mycollection", p);
    }
}