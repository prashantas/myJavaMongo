package demo.mavenPojo;


import java.net.UnknownHostException;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

//https://github.com/mongodb/morphia/wiki/QuickStart

/*class Hotel {

    private String name;
    private int stars;
    private Address address;

    // ... optional getters and setters
}
class Address {

    private String street;
    private String city;
    private String postCode;
    private String country;

    // ... optional getters and setters
}*/
/*
 * We want to save instances of these objects to MongoDB. All we need 
 * to do is 
 * add the Morphia annotations to the class fields we want to persist:
 */
@Entity
 class Hotel {

    @Id private ObjectId id;

    private String name;
    private int stars;

    @Embedded
    private Address address;

    public Hotel(){}
	public Hotel(ObjectId id, String name, int stars, Address address) {
		//super();
		this.id = id;
		this.name = name;
		this.stars = stars;
		this.address = address;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

    // ... optional getters and setters
}

@Embedded
 class Address {

    private String street;
    private String city;
    private String postCode;
    private String country;
     public Address() {}
	public Address(String street, String city, String postCode, String country) {
		//super();
		this.street = street;
		this.city = city;
		this.postCode = postCode;
		this.country = country;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

    // ... optional getters and setters
}
/*
 * We've annotated Hotel with @Entity, and Address with @Embedded 
 * since the Address is an object dependent on Hotel (and does not
 *  have a life outside the Hotel).

You can see that all the basic fields are automatically mapped by
 Morphia. If you want to exclude a field, just annotate it with @Transient.

Also note that we had to add a new field "id" to our Hotel class. 
The "id" value can be any persist-able type; like an int, uuid, or 
other object. If you want an auto-generated value just declare it as
 an ObjectId. 
If you don't use an ObjectId you must set the value before saving.
 */
public class DemoPojo {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		MongoClient mongo = new MongoClient( "localhost" , 27017 );
		Morphia morphia = new Morphia();
		morphia.map(Hotel.class).map(Address.class);
		Datastore ds = morphia.createDatastore(mongo, "mongoPojo");
		
		Hotel hotel = new Hotel();
		hotel.setName("Monalisa");
		hotel.setStars(5);

		Address address = new Address();
		address.setStreet("Jyotinagar");
		address.setCity("Bokakahat");
		address.setPostCode("785612");
		address.setCountry("India");
		//set address
		hotel.setAddress(address);
		 ds.save(hotel);
		 
		 System.out.println("Done Inserting");
		
		
		 //reading from DB		 
		 List<Hotel> fourStarHotels = ds.find(Hotel.class, "stars >=", 4).asList();
         System.out.println("read:" + fourStarHotels);
         for(Hotel ht : fourStarHotels)
         {
        	 System.out.println("star: " + ht.getStars() + "::name:" + ht.getName());
         }
	}

}
