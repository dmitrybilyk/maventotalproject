package quality_gate_cgi.fortest.build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Parent;

public class PatternClass extends Parent
{
   public static final  String  ABC_DEF  = "abc def";   public final static  String  ABC_DEF1 = "abc def1";   private static final Integer _KEY     = 5;
   public static        String  CODE     = "code";

   private Integer item1;
   private Double  item2;
   private Boolean item3;

   PatternClass(){
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
