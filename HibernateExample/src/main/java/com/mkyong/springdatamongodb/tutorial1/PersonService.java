package com.mkyong.springdatamongodb.tutorial1;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
 
 
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
 
/**
 * Service for processing Persons.
 * <p>
 
 
 * For a complete reference to MongoDB
 * see http://www.mongodb.org/
 * <p>
 
 
 * For transactions, see http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/transaction.html
 */
@Service("personService")
@Transactional
public class PersonService {
 
 protected static Logger logger = Logger.getLogger("service");
  
 public PersonService() {
  // Initialize our database
  init();
 }
  
 /**
  * Retrieves all persons
  */
 public List<Person> getAll() {
  logger.debug("Retrieving all persons");
   
  // Retrieve collection
  DBCollection coll = MongoDBFactory.getCollection("mydb","mycollection");
  // Retrieve cursor for iterating records
     DBCursor cur = coll.find();
     // Create new list
  List<Person> items = new ArrayList<Person>();
  // Iterate cursor
        while(cur.hasNext()) {
         // Map DBOject to Person
         DBObject dbObject = cur.next();
         Person person = new Person();
          
         person.setPid(dbObject.get("id").toString());
         person.setFirstName(dbObject.get("firstName").toString());
         person.setLastName(dbObject.get("lastName").toString());
         person.setMoney(Double.valueOf(dbObject.get("money").toString()));
 
         // Add to new list
         items.add(person);
        }
         
        // Return list
  return items;
 }
  
 /**
  * Retrieves a single person
  */
 public Person get( String id ) {
  logger.debug("Retrieving an existing person");
   
  // Retrieve collection
  DBCollection coll = MongoDBFactory.getCollection("mydb","mycollection");
  // Create a new object
  DBObject doc = new BasicDBObject();
  // Put id to search
        doc.put("id", id);
         
        // Find and return the person with the given id
        DBObject dbObject = coll.findOne(doc);
         
        // Map DBOject to Person
     Person person = new Person();
     person.setPid(dbObject.get("id").toString());
     person.setFirstName(dbObject.get("firstName").toString());
     person.setLastName(dbObject.get("lastName").toString());
     person.setMoney(Double.valueOf(dbObject.get("money").toString()));
      
        // Return person
  return person;
 }
  
 /**
  * Retrieves a single mongo object
  */
 private DBObject getDBObject( String id ) {
  logger.debug("Retrieving an existing mongo object");
   
  // Retrieve collection
  DBCollection coll = MongoDBFactory.getCollection("mydb","mycollection");
  // Create a new object
  DBObject doc = new BasicDBObject();
  // Put id to search
        doc.put("id", id);
         
        // Find and return the mongo with the given id
  return coll.findOne(doc);
 }
  
 /**
  * Adds a new person
  */
 public Boolean add(Person person) {
  logger.debug("Adding a new user");
   
  try {
   // Retrieve collection
   DBCollection coll = MongoDBFactory.getCollection("mydb","mycollection");
   // Create a new object
   BasicDBObject doc = new BasicDBObject();
   // Generate random id using UUID type 4
   // See http://en.wikipedia.org/wiki/Universally_unique_identifier
         doc.put("id", UUID.randomUUID().toString() );
         doc.put("firstName", person.getFirstName());
         doc.put("lastName", person.getLastName());
         doc.put("money", person.getMoney());
//         doc.put("groups", person.getGroups());
         // Save new person
         coll.insert(doc);
          
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
   // Retrieve person to delete
   BasicDBObject item = (BasicDBObject) getDBObject( id );
   // Retrieve collection
   DBCollection coll = MongoDBFactory.getCollection("mydb","mycollection");
   // Delete retrieved person
         coll.remove(item);
          
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
   // Retrieve person to edit
   BasicDBObject existing = (BasicDBObject) getDBObject( person.getPid() );
    
   DBCollection coll = MongoDBFactory.getCollection("mydb","mycollection");
    
   // Create new object
   BasicDBObject edited = new BasicDBObject();
   // Assign existing details
   edited.put("id", person.getPid());
   edited.put("firstName", person.getFirstName());
   edited.put("lastName", person.getLastName());
   edited.put("money", person.getMoney());
   // Update existing person
         coll.update(existing, edited);
          
   return true;
    
  } catch (Exception e) {
   logger.error("An error has occurred while trying to edit existing user", e);
   return false;
  }
   
 }
  
 private void init() {
  // Populate our MongoDB database
 
  logger.debug("Init MongoDB users");
 
  // Drop existing collection
  MongoDBFactory.getCollection("mydb","mycollection").drop();
  // Retrieve collection. If not existing, create a new one
  DBCollection coll = MongoDBFactory.getCollection("mydb","mycollection");
   
  // Create new object
  BasicDBObject doc = new BasicDBObject();
  // Generate random id using UUID type 4
  // See http://en.wikipedia.org/wiki/Universally_unique_identifier
        doc.put("id", UUID.randomUUID().toString());
        doc.put("firstName", "John");
        doc.put("lastName", "Smith");
        doc.put("money", 1000);
        coll.insert(doc);
   
        // Create new object
        doc = new BasicDBObject();
  // Generate random id using UUID type 4
        doc.put("id", UUID.randomUUID().toString());
        doc.put("firstName", "Jane");
        doc.put("lastName", "Adams");
        doc.put("money", 2000);
        coll.insert(doc);
         
        // Create new object
        doc = new BasicDBObject();
  // Generate random id using UUID type 4
        doc.put("id", UUID.randomUUID().toString());
        doc.put("firstName", "Jeff");
        doc.put("lastName", "Mayer");
        doc.put("money", 3000);
        coll.insert(doc);
   
 }
}