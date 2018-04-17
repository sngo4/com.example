package rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import collection.Client;
import service.ClientService;

/**
 * 
 * @author Ngo Thi Ngoc Sang (ngosangoc@gmail.com)
 *
 */
/**
 * @RestController A convenience annotation that is itself annotated
 *                 with @Controller and @ResponseBody. Types that carry this
 *                 annotation are treated as controllers where @RequestMapping
 *                 methods assume @ResponseBody semantics by default.
 * 
 *                 NOTE: @RestController is processed if an appropriate
 *                 HandlerMapping-HandlerAdapter pair is configured such as the
 *                 RequestMappingHandlerMapping-RequestMappingHandlerAdapter
 *                 pair which are the default in the MVC Java config and the MVC
 *                 namespace.
 *
 * @Controller Indicates that an annotated class is a "Controller" (e.g. a web
 *             controller). This annotation serves as a specialization
 *             of @Component, allowing for implementation classes to be
 *             autodetected through classpath scanning. It is typically used in
 *             combination with annotated handler methods based on the
 *             RequestMapping annotation.
 * @ResponseBody Annotation that indicates a method return value should be bound
 *               to the web response body. Supported for annotated handler
 *               methods in Servlet environments. As of version 4.0 this
 *               annotation can also be added on the type level in which case it
 *               is inherited and does not need to be added on the method level.
 */
@RestController
public class RestServiceController {
	@Autowired
	private ClientService clientService;

	/**
	 * http://localhost:9090/Basic/mongoGetAllClients
	 * 
	 * @return
	 */
	@RequestMapping(value = "/mongoGetAllClients", method = RequestMethod.GET)
	public List<Client> mongoGetAllClients() {

		List<Client> clients = clientService.getAllClients();

		return clients;
	}

	/**
	 * http://localhost:9090/Basic/mgGetClientByName?name=Sngo4
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/mgGetClientByName", method = RequestMethod.POST)
	public List<Client> getClientByName(@QueryParam("name") String name) {

		Client client = new Client();
		client.setCliName(name);

		List<Client> clients = clientService.getClientByName(client);

		return clients;

	}

	/**
	 * http://localhost:9090/Basic/insertClient?name=Sngo4&
	 * dateBirth=02/05/1993&register=9876543210&&country=VIETNAM
	 * 
	 * @param name
	 * @param lastname
	 * @param dateBirth
	 * @param register
	 * @param country
	 * @return
	 */
	@RequestMapping(value = "/insertClient", method = RequestMethod.POST)
	public boolean insertClient(@QueryParam("name") String name,
			@QueryParam("dateBirth") String dateBirth, @QueryParam("register") String register,
			@QueryParam("country") String country) {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Client client = new Client();

		boolean bol = false;

		try {
			client.setCliName(name);
			client.setCliDatebirth(dateFormat.parse(dateBirth));
			client.setCliRegister(register);
			client.setCliCountry(country);

			bol = clientService.insertClient(client);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bol;
	}

	/**
	 * http://localhost:9090/Basic/updateClient?name=Sngo4&dateBirth=02/05/1993&
	 * register=9876543210&&country=VIETNAM
	 * 
	 * @param name
	 * @param lastname
	 * @param dateBirth
	 * @param register
	 * @param country
	 * @return
	 */
	@RequestMapping(value = "/updateClient", method = RequestMethod.POST)
	public boolean updateClient(@QueryParam("name") String name, @QueryParam("lastname") String lastname,
			@QueryParam("dateBirth") String dateBirth, @QueryParam("register") String register,
			@QueryParam("country") String country) {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Client client = new Client();

		boolean bol = false;

		try {
			client.setCliName(name);
			client.setCliDatebirth(dateFormat.parse(dateBirth));
			client.setCliRegister(register);
			client.setCliCountry(country);

			bol = clientService.insertClient(client);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bol;
	}

	/**
	 * http://localhost:9090/Basic/deleteClient?name=Sngo4&
	 * dateBirth=02/05/1993&register=9876543210&&country=VIETNAM
	 * 
	 * @param name
	 * @param lastname
	 * @param dateBirth
	 * @param register
	 * @param country
	 * @return
	 */
	@RequestMapping(value = "/deleteClientByNumbers", method = RequestMethod.POST)
	public boolean deleteClientByNumbers(@QueryParam("checked") String checked) {

		boolean bol = false;

		try {

			ArrayList<String> decArray = new ArrayList<String>();

			for (String s : checked.split(",")) {
				decArray.add(s);
			}

			bol = clientService.deleteClientsByNumber(decArray);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bol;
	}

	/**
	 * http://localhost:9090/Basic/mgGetClientByName?clientNumbers=?,?,?,?,?
	 * 
	 * @param clientNumbers
	 * @return
	 */
	@RequestMapping(value = "/deleteClientsByNumber", method = RequestMethod.POST)
	public boolean deleteClientsByNumber(@QueryParam("clientNumbers") String clientNumbers) {

		ArrayList<String> list = new ArrayList<String>();

		boolean bol = false;

		for (String s : clientNumbers.split(",")) {
			list.add(s);
		}

		try {
			bol = clientService.deleteClientsByNumber(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bol;
	}
}
