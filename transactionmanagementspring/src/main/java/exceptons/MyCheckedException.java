package exceptons;

/**
 * Created by dmitry.bilyk on 4/8/14.
 */
public class MyCheckedException extends Exception {
    public MyCheckedException(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return "My something wrong!";
    }
}
