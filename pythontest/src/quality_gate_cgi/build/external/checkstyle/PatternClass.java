package quality_gate_cgi.build.external.checkstyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Parent;

/**
 * Here goes 1 line entity description: aim...
 * Here goes 2 line entity description: et...
 *
 * @module      ModuleName
 * @version     1.0v Jan 01, 2013
 * @author      symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author      Jane Doe <jane.doe@symmetrics.de>
 * @author      Joe Doe <joe.doe@symmetrics.de>
 * @link        http://www.symmetrics.de
 * @see		"https://wiki.hybris.com"
 * @copyright	2013 symmetrics - a CGI Group brand
 *
 *
 */
public class PatternClass extends Parent
{
   public static final  String  ABC_DEF  = "abc def";
   public final static  String  ABC_DEF1 = "abc def1";
   private static final Integer _KEY     = 5;
   public static        String  CODE     = "code";

   private Integer item1;
   private Double  item2;
   private Boolean item3;

   PatternClass()
   {
      //default constructor
   }

   PatternClass(final Integer _item1)
   {
      super();
      this.item1 = _item1;
   }

   /**
    *  This is javadoc method example.
    *
    *  Doc on someMethod1
    *  @param value bla
    *  @param someClass bla
    *  @param _value bla
    */
   public final void someMethod1(final String value, final SomeClass someClass, final Integer _value)
   {
      final String someVar1 = "somevar1";
      final List<String> list = new ArrayList<>();
      final Map<Integer, String> map = new HashMap<>();
      String someVar2 = "somevar2"; //comment here

      if (value.equals(someVar1))
      {
         list.add(someVar2);
         for (String str : list)
         {
            System.out.println();
         }
      }
      else
		{
			map.put(_KEY, "Bla-bla");
			System.out.println("Bla-bla1");
		}
		
		try
		{
			someMethod2();		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

      final Object a = null;
      final Object b = null;
      Boolean x = (a == b) ? false : true;
	}

	/**
	 *  This is javadoc method example.
	 *
	 *  Doc on someMethod2
	 *  @return some text
	 */
	public static String someMethod2()
	{	   
		int b = 1;

		switch (b)
		{
			case 1:
			return CODE + "1";
//				break;
			case 2:
				return CODE + "2";
//				break;
		}
      return null;
	}

	private void getMethod3() throws Exception
	{		
		throw new Exception("bla-bla");
	} 
}
