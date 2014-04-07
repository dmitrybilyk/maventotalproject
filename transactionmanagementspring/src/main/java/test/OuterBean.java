package test;


import model.User;

public interface OuterBean {

	void testRequired(User user);
	
	void testRequiresNew(User user);

}
