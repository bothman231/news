package com.botham;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoConnect {

	public static void main(String[] args) {
		//connectWithoutAuth();

		
		//connectWithAuth();
		
		try {
			//insert();
			get();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to demonstrate how to connect mongoDB database 
	 * without auth , it means anonymously and print all the database available,
	 */
	public static void connectWithoutAuth()  {
		try {
		// provide IP/hostname or port to connect mongoDB server
		MongoClient mongoClient = new MongoClient( "localhost", 27017 );
	 
		List<String> dbList = mongoClient.getDatabaseNames();
		for(String dbName : dbList) {
			System.out.println("dbName: " + dbName);
		}
	 
		} catch (Exception e) {
		  e.printStackTrace();
		}
	}

	
	
	public static void connectWithAuth() {
		try {
	 
		// using release 2.13 or later
		//MongoCredential credential = MongoCredential.	("mycore", "coreService", "abc123".toCharArray());
	 
		// using release 2.11 or 2.12 till 2.6 of mongoDB
		// if authentication Mechanism is MONGODB-CR then user following	
		//MongoCredential credential = MongoCredential.createMongoCRCredential("myblogUser", "admin", "secret".toCharArray());
	 
		// if authentication Mechanism is SCRAM-SHA-1 then user following, 
		//by default from mongodb 3.0 and ownward uses SCRAM-SHA-1 authentication mechanism
		MongoCredential credential = MongoCredential.createScramSha1Credential("sa", "admin", "index1".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("localhost"), Arrays.asList(credential));
	 
		DB db = mongoClient.getDB("config");
		DBCollection coll = db.getCollection("products");
	 
		System.out.println("total count in collection: " + coll.getCount());
	 
		// Get added Data and print on console
		DBCursor cursor = coll.find();
		while(cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static MongoClient connect() throws UnknownHostException {
		MongoCredential credential = MongoCredential.createScramSha1Credential("sa", "admin", "index1".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("localhost"), Arrays.asList(credential));
		return mongoClient;
	 
	}
	
	
	public static void insert() throws UnknownHostException {
		
		MongoClient mongoClient = connect();
		
		try {
	 


		DB db = mongoClient.getDB("config");
		DBCollection coll = db.getCollection("products");
	 
		System.out.println("total count in collection: " + coll.getCount());
	 
		// Get added Data and print on console
		DBCursor cursor = coll.find();
		while(cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		
		BasicDBObject product = new BasicDBObject();
		product.put("name", "hop bag");
		product.put("price", .99);
		product.put("supplier", "southern");
		
		coll.insert(product);
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
public static void get() throws UnknownHostException {
		
		MongoClient mongoClient = connect();
		
		try {
	 


		DB db = mongoClient.getDB("config");
		DBCollection coll = db.getCollection("products");
		
		
		BasicDBObject whereQuery = new BasicDBObject();
		
		whereQuery.put("name", "airlock");
		DBCursor cursor = coll.find(whereQuery);
		while(cursor.hasNext()) {
		    System.out.println(cursor.next());
		}
		
		
	
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
