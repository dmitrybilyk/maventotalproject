package com.mkyong.springdatamongodb.tutorial1;

/**
 * Created by dmitry.bilyk on 4/4/14.
 */
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.data.document.mongodb.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.data.document.mongodb.query.Criteria.where;

/**
 * Service for processing {@link Person} objects.
 * Uses Spring's {@link MongoTemplate} to perform CRUD operations.
 * <p>
 * For a complete reference to MongoDB
 * see http://www.mongodb.org/
 * <p>
 * For a complete reference to Spring Data MongoDB
 * see http://www.springsource.org/spring-data
 *
 * @author Krams at {@link http://krams915@blogspot.com}
 */
@Service("personService")
@Transactional
public class PersonServiceSpringData {

    protected static Logger logger = Logger.getLogger("service");

    //    @Resource(name="mongoTemplate")
    private MongoTemplate mongoTemplate;

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Retrieves all persons
     */
    public List<Person> getAll() {
        logger.debug("Retrieving all persons");

        // Find an entry where pid property exists
        Query query = new Query(where("pid").exists(true));
        // Execute the query and find all matching entries
        List<Person> persons = mongoTemplate.find(query, Person.class);

        return persons;
    }

    /**
     * Retrieves a single person
     */
    public Person get( String id ) {
        logger.debug("Retrieving an existing person");

        // Find an entry where pid matches the id
        Query query = new Query(where("pid").is(id));
        // Execute the query and find one matching entry
        Person person = mongoTemplate.findOne("mycollection", query, Person.class);

        return person;
    }

    /**
     * Retrieves a single person
     */
    public Person getByMoney( Double money ) {
        logger.debug("Retrieving an existing person");

        // Find an entry where pid matches the id
        Query query = new Query(where("money").is(money));
        // Execute the query and find one matching entry
        Person person = mongoTemplate.findOne("mycollection", query, Person.class);

        return person;
    }

    /**
     * Adds a new person
     */
    public Boolean add(Person person) {
        logger.debug("Adding a new user");

        try {

            // Set a new value to the pid property first since it's blank
            person.setPid(UUID.randomUUID().toString());
            // Insert to db
            mongoTemplate.insert("mycollection", person);

            return true;

        } catch (Exception e) {
            logger.error("An error has occurred while trying to add new user", e);
            return false;
        }
    }

    /**
     * Deletes an existing person
     */
    public Boolean delete(String id) {
        logger.debug("Deleting existing person");

        try {

            // Find an entry where pid matches the id
            Query query = new Query(where("pid").is(id));
            // Run the query and delete the entry
            mongoTemplate.remove(query);

            return true;

        } catch (Exception e) {
            logger.error("An error has occurred while trying to delete new user", e);
            return false;
        }
    }

    /**
     * Edits an existing person
     */
    public Boolean edit(Person person) {
        logger.debug("Editing existing person");

        try {

            // Find an entry where pid matches the id
            Query query = new Query(where("pid").is(person.getPid()));

            // Declare an Update object.
            // This matches the update modifiers available in MongoDB
            Update update = new Update();

            update.set("firstName", person.getFirstName());
            mongoTemplate.updateMulti(query, update);

            update.set("lastName", person.getLastName());
            mongoTemplate.updateMulti(query, update);

            update.set("money", person.getMoney());
            mongoTemplate.updateMulti(query, update);

            return true;

        } catch (Exception e) {
            logger.error("An error has occurred while trying to edit existing user", e);
            return false;
        }

    }
}