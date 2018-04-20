package rest;

import java.util.List;

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
	 * http://localhost:9090/com.example/getAccountInfo
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAccountInfo", method = RequestMethod.GET)
	public List<Account> getAccountInfo(){
		List<Account> accounts = accountService.getAccInfo();
		return accounts;		
	}
	
}
