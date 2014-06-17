package com.learn.bases.compare.copmarator.dateandstringcomparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by bid on 6/17/14.
 */
public class Main
{
	public static void main(String[] args)
	{
		Human human = new Human();
		human.setBusinessPartnerId("3");
		human.setBirthDate(new Date(1403009850928l));

		Human human2 = new Human();
//		human2.setBusinessPartnerId("5");
		Date date2 = new Date(1403008851928l);
		human2.setBirthDate(date2);

		Human human3= new Human();
//		human3.setBusinessPartnerId("3");
		human3.setBirthDate(new Date(1403004857928l));

		Human human4 = new Human();
		human4.setBusinessPartnerId("7");
		Date date1 = new Date(1403009651828l);
		human4.setBirthDate(date1);

		List<Human> arrayList = new ArrayList<Human>();
		arrayList.add(human);
		arrayList.add(human2);
		arrayList.add(human3);
		arrayList.add(human4);

		ComplexComparator complexComparator = new ComplexComparator();
		Collections.sort(arrayList, complexComparator);

		for (Human human1 : arrayList)
		{
			System.out.println(human1.getBusinessPartnerId());
			System.out.println(human1.getBirthDate());
//			System.out.println(new Date().getTime());
		}
	}
//	private void sortShipmentAddresses(final List<ShipmentAddressDTO> addresses)
//	{
//		AddressDTOLastUpdateDateComparator addressDTOLastUpdateDateComparator = new AddressDTOLastUpdateDateComparator();
//		Collections.sort(addresses, addressDTOLastUpdateDateComparator);
//	}
}
