package com.grocery.store.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.grocery.store.domain.Item;

public class PricingService {



	public double getTotalPrice(List<Item> items) {


		HashMap<String, Item> itemMap = new HashMap<String, Item>();		
		if(items!=null && items.size()!=0) {			
			for(int i=0; i <items.size(); i++) {				
				Item itemEach= items.get(i);
				itemMap.put(itemEach.getItemName(), itemEach);			
			}			
		}		

		return getActualPrice(itemMap);

	}

	private double getActualPrice(HashMap<String, Item> itemMap) {

		double actualPrice =0.0;
		double applePrice = 0.0;
		double orangePrice = 0.0;
		if(itemMap !=null && itemMap.size() !=0) {

			for(Map.Entry<String, Item> entry: itemMap.entrySet()) {


				if (entry!=null) {
					Item item = entry.getValue();
					if(item !=null ) {
						// If the quantity of Apple is >=2 then apply the discount.
						if(entry.getKey().equalsIgnoreCase("Apple") && item.getQuantity()>=2) {
							double discountPrice = applyDiscount(item.getPrice(), item.getDiscount());
							if(item.getQuantity()%2==0) {

								applePrice=item.getQuantity() * discountPrice;

							}else {
								// its an odd number so reminder will be one always, so calculate the price for half the quantity
								int halfSize = item.getQuantity()/2;

								applePrice = (halfSize +1) * item.getPrice();

							}
						}
						// If the quantity of Orange is >=3 then apply the discount.

						else if(entry.getKey().equalsIgnoreCase("Orange") && item.getQuantity()>=3) {
							double discountPrice = applyDiscount(item.getPrice(), item.getDiscount());
							if(item.getQuantity()%3 == 0) {
								orangePrice=item.getQuantity() * discountPrice;

							}else {
								int halfSize = item.getQuantity()/3;
								int reminder = item.getQuantity()%3;
								orangePrice =(halfSize+ reminder)*item.getPrice();
							}
						}
						actualPrice = applePrice+orangePrice;

					}

				}

			}

		}
		return actualPrice;

	}

	private double applyDiscount (double priceInCent, double discount ) {
		double calculateDiscount =0.0;

		calculateDiscount = (priceInCent *discount)/100;

		return calculateDiscount;

	}



}
