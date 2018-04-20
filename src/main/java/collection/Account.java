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
@Document(collection = "account")
public class Account {
	/**
	 * @Id
	 * @Field
	 */
	@Id
	@Field("accRegister")
	private String accRegister;

	/**
	 * @Field
	 */
	@Field("accName")
	private String accName;

	@Field("accDate")
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@JsonSerialize(using = util.JSONSerializer.class)
	@JsonDeserialize(using = util.JSONDeserialize.class)
	private Date accDate;

	@Field("accTemperatureC")
	private int accTemperatureC;

	@Field("accTemperatureF")
	private int accTemperatureF;
	
	@Field("accSound")
	private int accSound;
	
	@Field("accImageUrl")
	private String accImageUrl;

	public String getAccRegister() {
		return accRegister;
	}

	public void setAccRegister(String accRegister) {
		this.accRegister = accRegister;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public Date getAccDate() {
		return accDate;
	}

	public void setAccDate(Date accDate) {
		this.accDate = accDate;
	}

	public int getAccTemperatureC() {
		return accTemperatureC;
	}

	public void setAccTemperatureC(int accTemperatureC) {
		this.accTemperatureC = accTemperatureC;
	}

	public int getAccTemperatureF() {
		return accTemperatureF;
	}

	public void setAccTemperatureF(int accTemperatureF) {
		this.accTemperatureF = accTemperatureF;
	}

	public int getAccSound() {
		return accSound;
	}

	public void setAccSound(int accSound) {
		this.accSound = accSound;
	}

	public String getAccImageUrl() {
		return accImageUrl;
	}

	public void setAccImageUrl(String accImageUrl) {
		this.accImageUrl = accImageUrl;
	}

}
