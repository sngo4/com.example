package service;

import java.util.List;

import collection.Account;

/**
 * 
 * @author Ngo Thi Ngoc Sang (ngosangoc@gmail.com)
 *
 */
public interface AccountService {
	public List<Account> getAllAccInfo();

	public List<Account> getAccInfo(String accRegister);
	
}
