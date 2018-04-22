package rest;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import collection.Account;
import service.AccountService;

@RestController
public class RestAccountServiceController {

	@Autowired
	private AccountService accountService;
	/**
	 * http://localhost:9090/com.example/getAllAccountInfo
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAllAccountInfo", method = RequestMethod.GET)
	public List<Account> getAllAccountInfo(){
		List<Account> accounts = accountService.getAllAccInfo();
		return accounts;		
	}
	
	/**
	 * http://localhost:9090/com.example/getAccountInfo
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAccountInfo", method = RequestMethod.GET)
	public List<Account> getAccountInfo(@QueryParam("accRegister") String accRegister){
		List<Account> accounts = accountService.getAccInfo(accRegister);
			return accounts;		
	}
	
}
