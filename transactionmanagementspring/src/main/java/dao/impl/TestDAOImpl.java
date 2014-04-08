package dao.impl;

import dao.TestDAO;
import model.Message;
import model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestDAOImpl implements TestDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertUser(User user) {
        user.setName(user.getUsername() + "mandatory1");
		sessionFactory.getCurrentSession().save(user);
	}

    @Override
    public void addMessage(String s) {
        Message message = new Message();
        message.setDescription(s);
        sessionFactory.getCurrentSession().save(message);
    }

}
