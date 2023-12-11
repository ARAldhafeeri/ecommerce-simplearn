package com.org.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.ecommerce.modal.PurchaseItem;
import com.org.ecommerce.repository.PurchaseItemRepository;

@Service
public class PurchaseItemServiceImpl implements PurchaseItemService {

	@Autowired
	PurchaseItemRepository purchaseItemRepository;

	@Override
	public PurchaseItem getPurchaseItemById(long id) {
		return purchaseItemRepository.getPurchaseItemById(id);
	}

	@Override
	public PurchaseItem getItemById(long id) {
		return purchaseItemRepository.getItemById(id);
	}

	@Override
	public List<PurchaseItem> getAllItemsByPurchaserId(long id) {
		return purchaseItemRepository.getAllItemsByPurchaserId(id);
	}

	@Override
	public int updateItem(PurchaseItem item) {
		return purchaseItemRepository.updateItem(
			item.getID(), 
			item.getPurchaseId(), 
			item.getProductId(), 
			item.getUserId(),
			item.getRate(), 
			item.getQty(),
			item.getPrice()
			);
	}

	@Override
	public void deleteItem(long id) {
		purchaseItemRepository.deleteItem(id);
	}

	@Override
	public void deleteAllItemsForPurchaseId(long purchaseId) {
		purchaseItemRepository.deleteAllItemsForPurchaseId(purchaseId);
	}

	@Override
	public PurchaseItem createPurchaseItem(PurchaseItem purchaseItem) {
		return purchaseItemRepository.save(purchaseItem);
	}


	

}
