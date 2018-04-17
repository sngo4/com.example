package dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import collection.Client;

/**
 * 
 * @author Ngo Thi Ngoc Sang (ngosangoc@gmail.com)
 *
 */
/**
 * 
 * @Repository In Spring 2.0 and later, the @Repository annotation is a marker
 *             for any class that fulfills the role or stereotype (also known as
 *             Data Access Object or DAO) of a repository. Among the uses of
 *             this marker is the automatic translation of exceptions.
 * 
 *             Spring 2.5 introduces further stereotype
 *             annotations: @Component, @Service, and @Controller. @Component is
 *             a generic stereotype for any Spring-managed
 *             component. @Repository, @Service, and @Controller are
 *             specializations of @Component for more specific use cases, for
 *             example, in the persistence, service, and presentation layers,
 *             respectively.
 * 
 *             Therefore, you can annotate your component classes
 *             with @Component, but by annotating them
 *             with @Repository, @Service, or @Controller instead, your classes
 *             are more properly suited for processing by tools or associating
 *             with aspects. For example, these stereotype annotations make
 *             ideal targets for pointcuts.
 * 
 *             Thus, if you are choosing between using @Component or @Service
 *             for your service layer, @Service is clearly the better choice.
 *             Similarly, as stated above, @Repository is already supported as a
 *             marker for automatic exception translation in your persistence
 *             layer.
 *
 */
@Repository("ClientDAO")
public class ClientDAOimpl implements ClientDAO {
	/**
	 * @Autowired
	 * @Qualifier("mongoTemplate")
	 * @since 1.0
	 */
	@Autowired
	@Qualifier("mongoTemplate")
	private MongoTemplate mongoTemplate;

	public ClientDAOimpl() {
	}

	/**
	 * get all current Client in DataBase
	 *
	 * @return <code>List<Client></code> if exist client; <code>null</code>
	 *         otherwise.
	 * @since 1.0
	 */
	public List<Client> getAllClients() {

		List<Client> listClient = mongoTemplate.findAll(Client.class);

		return listClient == null ? new ArrayList<Client>() : listClient;
	}
	/**
	 * get all current Client in DataBase by name client
	 *
	 * @param client       the client to be check.
	 * @return <code>List<Client></code> if exist client; <code>null</code>
	 *         otherwise.
	 * @since 1.0
	 */
	public List<Client> getClientByName(Client client) {

		Criteria criteria = Criteria.where("cliName").is(client.getCliName());

		Query query = new Query(criteria);

		List<Client> listClient = mongoTemplate.find(query, Client.class, "client");

		return listClient == null ? new ArrayList<Client>() : listClient;
	}

	/**
	 * insert a client into Database
	 *
	 * @param client       the client to be insert.
	 * @return <code>true<Client></code> if the client was inserted successfully; <code>false</code>
	 *         otherwise.
	 * @since 1.0
	 */
	public boolean insertClient(Client client) {

		try {
			mongoTemplate.save(client, "client");
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * update a client information
	 *
	 * @param client       the client to be update.
	 * @return <code>true<Client></code> if the client was updated successfully; <code>false</code>
	 *         otherwise.
	 * @since 1.0
	 */
	public boolean updateClient(Client client) {
		try {
			mongoTemplate.save(client, "client");
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		return true;
	}
	/**
	 * delete a client
	 *
	 * @param client       the client to be delete.
	 * @return <code>true<Client></code> if the client was deleted successfully; <code>false</code>
	 *         otherwise.
	 * @since 1.0
	 */
	public boolean deleteClient(Client client) {
		try {
			mongoTemplate.remove(client, "client");
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		return true;
	}
	/**
	 * delete clients by number
	 *
	 * @param cliNumbers       the client number to be check to delete.
	 * @return <code>true<Client></code> if the client was delete successfully; <code>false</code>
	 *         otherwise.
	 * @since 1.0
	 */
	public boolean deleteClientsByNumber(List<String> cliNumbers) {

		Criteria criteria = Criteria.where("cliNumber").in(cliNumbers);

		Query query = new Query(criteria);

		try {
			mongoTemplate.remove(query, Client.class, "client");
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		return true;
	}
}
