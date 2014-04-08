import exceptons.MyCheckedException;
import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.OuterBean;

public class Main 
{
    public static void main( String[] args )
    {
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-transactions.xml");
    	OuterBean testBean = (OuterBean) ctx.getBean("outerBeanImpl");
    	
    	User user = new User();
    	user.setUsername("johndoe" + Math.random() * 100);
    	user.setName("John Doe");


//        1. Test flow with/without default transactional annotation
        user.setName(user.getUsername().concat(" test mandatory withwithout222"));
        testBean.testMandatory(user);

//    	try{
//    		testBean.testRequired(user);
//    	} catch(Exception e){
//    		// catch exception raised from transaction rollback
//    	}
    	
//    	testBean.testRequiresNew(user);
    	
    }
}
