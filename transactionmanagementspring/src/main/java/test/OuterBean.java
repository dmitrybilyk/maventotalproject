package test;


import exceptons.MyCheckedException;
import model.User;
import org.springframework.transaction.annotation.Transactional;

public interface OuterBean {

    @Transactional
    void testFlowWithTransactionalDefault(User user) throws MyCheckedException;

    void testRequired(User user);
	
	void testRequiresNew(User user);

    void testMandatory(User user);
}
