package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import collection.Client;
import dao.ClientDAO;

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
@Service("ClientService")
public class ClientServiceImpl implements ClientService{
	/**
	 * @Autowired
	 * @since 1.0
	 */
	@Autowired
	private ClientDAO clientDAO;
	
	public List<Client> getAllClients() {
		
		return clientDAO.getAllClients();
	}
 
	public List<Client> getClientByName(Client client) {
		 return clientDAO.getClientByName(client);
	}
 
	public boolean insertClient(Client client) throws Exception {
		return clientDAO.insertClient(client);
	}
 
	public boolean updateClient(Client client) throws Exception {
		return clientDAO.updateClient(client);
	}
 
	public boolean deleteClient(Client client) throws Exception {
		return clientDAO.deleteClient(client);
	}
 
	public boolean deleteClientsByNumber(List<String> cliNumbers) throws Exception {
		return clientDAO.deleteClientsByNumber(cliNumbers);
	}
}
