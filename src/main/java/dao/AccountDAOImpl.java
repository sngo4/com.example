package dao;

import java.util.ArrayList;
import java.util.List;

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

		List<Account> listClient = mongoTemplate.find(query, Account.class, "account");

		return listClient == null ? new ArrayList<Account>() : listClient;
	}
}
