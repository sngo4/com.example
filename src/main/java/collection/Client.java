package collection;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Model considerations: In MongoDB, collections are equivalent the tables in a
 * SQL database Note that the Client class uses annotations from
 * org.springframework.data.mongodb in order to configure the Collection. Also,
 * we are using two classes to handle the data serialization and deserialization
 * in Date attribute cliDatebirth, we are going to talk more about it later in
 * this post. Now, lets take a look in the Client DAO interface and
 * implementation to abstract and encapsulate all access to the MongoDB. To
 * perform the operations in the database, it was used the class MongoTemplate
 * 
 * * @author Ngo Thi Ngoc Sang (ngosangoc@gmail.com)
 */

/**
 * @Document
 */
@Document(collection = "client")
public class Client {
	/**
	 * @Id
	 * @Field
	 */
	@Id
	@Field("cliNumber")
	private String cliNember;

	/**
	 * @Field
	 */
	@Field("cliName")
	private String cliName;

	@Field("cliDatebirth")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonSerialize(using = util.JSONSerializer.class)
	@JsonDeserialize(using = util.JSONDeserialize.class)
	private Date cliDatebirth;

	@Field("cliRegister")
	private String cliRegister;

	@Field("cliCountry")
	private String cliCountry;

	public String getCliNember() {
		return cliNember;
	}

	public void setCliNember(String cliNember) {
		this.cliNember = cliNember;
	}

	public String getCliName() {
		return cliName;
	}

	public void setCliName(String cliName) {
		this.cliName = cliName;
	}

	public Date getCliDatebirth() {
		return cliDatebirth;
	}

	public void setCliDatebirth(Date cliDatebirth) {
		this.cliDatebirth = cliDatebirth;
	}

	public String getCliRegister() {
		return cliRegister;
	}

	public void setCliRegister(String cliRegister) {
		this.cliRegister = cliRegister;
	}

	public String getCliCountry() {
		return cliCountry;
	}

	public void setCliCountry(String cliCountry) {
		this.cliCountry = cliCountry;
	}

}
