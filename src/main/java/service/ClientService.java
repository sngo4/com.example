package service;

import java.util.List;
import collection.Client;

/**
 * 
 * @author Ngo Thi Ngoc Sang (ngosangoc@gmail.com)
 *
 */
public interface ClientService {
	public List<Client> getAllClients();

	public List<Client> getClientByName(Client name);

	public boolean insertClient(Client client) throws Exception;

	public boolean updateClient(Client client) throws Exception;

	public boolean deleteClient(Client client) throws Exception;

	public boolean deleteClientsByNumber(List<String> cliNumbers) throws Exception;
}
