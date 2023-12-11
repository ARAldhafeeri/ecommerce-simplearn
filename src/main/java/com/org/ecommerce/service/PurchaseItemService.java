package com.org.ecommerce.service;

import java.util.List;

import com.org.ecommerce.modal.PurchaseItem;

public interface PurchaseItemService {

	public PurchaseItem getItemById(long id);
		
	public List<PurchaseItem> getAllItemsByPurchaserId(long purchaseId);
		
	public int updateItem(PurchaseItem item);
	

	public void deleteItem(long id);

	public void deleteAllItemsForPurchaseId(long purchaseId);

	public PurchaseItem getPurchaseItemById(long id);

	public PurchaseItem createPurchaseItem(PurchaseItem purchaseItem);
}
