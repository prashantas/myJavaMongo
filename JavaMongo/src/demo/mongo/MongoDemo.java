package demo.mongo;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class MongoDemo {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		DB db = mongoClient.getDB( "mydb" );
		DBCollection coll = db.getCollection("testCollection");
		BasicDBObject doc = new BasicDBObject("name", "MongoDB")
        .append("type", "database")
        .append("count", 1)
        .append("info", new BasicDBObject("x", 203).append("y", 102));
        WriteResult rt = coll.insert(doc);
		System.out.println("Done: " + rt );
	}

}
