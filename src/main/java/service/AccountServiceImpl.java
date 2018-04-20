package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import collection.Account;
import dao.AccountDAO;

/**
 * 
 * @author Ngo Thi Ngoc Sang (ngosangoc@gmail.com)
 *
 */
@Service("AccountService")
public class AccountServiceImpl  implements AccountService{

	@Autowired
	private AccountDAO accountDAO;
	public List<Account> getAllAccInfo(){
		return accountDAO.getAllAccInfo();
	}
	
	public List<Account> getAccInfo(String accRegister){
		return accountDAO.getAccInfo(accRegister);
	}
}
