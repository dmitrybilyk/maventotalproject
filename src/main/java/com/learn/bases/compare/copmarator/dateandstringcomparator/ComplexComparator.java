package com.learn.bases.compare.copmarator.dateandstringcomparator;

import com.learn.bases.compare.copmarator.dateandstringcomparator.Human;
import java.util.Comparator;
import org.apache.commons.lang.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 07.05.12
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 */
public class ComplexComparator implements Comparator<Human> {

    public int compare(Human o1, Human o2)
	 {

     if (StringUtils.isNotEmpty(o1.getBusinessPartnerId()) && StringUtils.isEmpty(o2.getBusinessPartnerId())){
			 return -1;
		 }
		 if (StringUtils.isEmpty(o1.getBusinessPartnerId()) && StringUtils.isNotEmpty(o2.getBusinessPartnerId())){
			 return 1;
		 }

		 if (StringUtils.isNotEmpty(o1.getBusinessPartnerId()) && StringUtils.isNotEmpty(o2.getBusinessPartnerId()))
		 {
				 return o1.getBirthDate().before(o2.getBirthDate()) ? 1 : -1;
		 }

		 if (StringUtils.isEmpty(o1.getBusinessPartnerId()) && StringUtils.isEmpty(o2.getBusinessPartnerId()))
		 {
			 return o1.getBirthDate().before(o2.getBirthDate()) ? 1 : -1;
		 }
		 return 0;
	 }
}
