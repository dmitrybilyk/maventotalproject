package test.impl;

import dao.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import test.InnerBean;

@Service
public class InnerBeanImpl implements InnerBean {

    @Autowired
    private TestDAO testDAO;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void testRequired() {
        testDAO.addMessage("new message" + Math.random() * 100);
		throw new RuntimeException("Rollback this transaction!");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void testRequiresNew() {
        testDAO.addMessage("new mess  test of required new");
//		throw new RuntimeException("required new Rollback this transaction!");
	}

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void testMandatory() {
        testDAO.addMessage("new mess  test of mandatory");
    }

}
