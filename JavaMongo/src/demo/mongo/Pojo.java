package demo.mongo;

import java.io.Serializable;
import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ReflectionDBObject;
import com.mongodb.WriteResult;

class C extends ReflectionDBObject  implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int j;
    private int cnt;
    public int getcnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getj() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public C(){}
    
}

public class Pojo {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		DB db = mongoClient.getDB( "mydb" );
		DBCollection coll = db.getCollection("testPojo");
		DBObject bdbo = new BasicDBObject();
		C c = new C();
		c.setJ(1); 
		c.setCnt(5);
		//bdbo.put("a",c);
		 WriteResult rt = coll.insert(c);
		 
		 System.out.println("Done::" + rt);
	}

}
