package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import collection.Account;

@Repository("AccountDAO")
public class AccountDAOImpl implements AccountDAO{

	@Autowired
	@Qualifier("mongoTemplate")
	private MongoTemplate mongoTemplate;
	
	public AccountDAOImpl() {
		
	}
	/**
	 * get all Account Info in DataBase
	 *
	 * @return <code>List<Account></code> if exist Account; <code>null</code>
	 *         otherwise.
	 * @since 1.0
	 */
	public List<Account> getAllAccInfo() {
		List<Account> account = mongoTemplate.findAll(Account.class);
		return account == null ? new ArrayList<Account>() : account;
	}
	
	/**
	 * get an Account Info in DataBase
	 *
	 * @return <code>List<Account></code> if exist Account; <code>null</code>
	 *         otherwise.
	 * @since 1.0
	 */
	public List<Account> getAccInfo(String accRegister) {
		
		Criteria criteria = Criteria.where("accRegister").in(accRegister);

		Query query = new Query(criteria);

		List<Account> listAccount = mongoTemplate.find(query, Account.class, "account");
		
		//hard code to test starts
		int maxC = 35;
		int minC = 25;
		
		int maxF = 98;
		int minF = 35;
		
		int maxDB = 98;
		int minDB = 35;
		
		Random rand = new Random(); 
		for (Account account : listAccount) {
			account.setAccTemperatureC(rand.nextInt((maxC - minC) + 1) + minC);
			account.setAccTemperatureF(rand.nextInt((maxF - minF) + 1) + minF);
			account.setAccSound(rand.nextInt((maxDB - minDB) + 1) + minDB);
		}
		//hard code to test ends

		return listAccount == null ? new ArrayList<Account>() : listAccount;
	}
}
