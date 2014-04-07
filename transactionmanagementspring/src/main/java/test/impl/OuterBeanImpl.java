package test.impl;

import dao.TestDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import test.InnerBean;
import test.OuterBean;

@Service
public class OuterBeanImpl implements OuterBean {

	@Autowired
	private TestDAO testDAO;
	
	@Autowired
	private InnerBean innerBean;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void testRequired(User user) {
		testDAO.insertUser(user);
		try{
			innerBean.testRequired();
		} catch(RuntimeException e){
            System.out.println("Something wrong in outer bean - required strategy");
        }
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void testRequiresNew(User user) {
		testDAO.insertUser(user);
		try{
			innerBean.testRequiresNew();
		} catch(Exception e){
			// handle exception
		}
	}

}
