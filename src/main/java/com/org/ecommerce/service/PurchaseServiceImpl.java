package com.org.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.ecommerce.modal.Purchase;
import com.org.ecommerce.repository.PurchaseRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	PurchaseRepository purchaseRepository;
		
	@Override
		public Purchase getPurchaseById(long id){
			return purchaseRepository.getPurchaseById(id);
		}

		@Override
		public List<Purchase> getAllItems(){
			return purchaseRepository.findAll();
		}

		@Override
		public List<Purchase> getAllItemsByUserId(long userId){
			return purchaseRepository.getAllItemsByUserId(userId);
		}

		@Override
		public long  updatePurchase(Purchase purchase){
			return purchaseRepository.updatePurchase(
				purchase.getID(), 
				purchase.getUserId(), 
				purchase.getTotal(),
				purchase.getDate()
				);
		}
		
		@Override
		public void deletePurchase(long id){
			purchaseRepository.deletePurchase(id);
		}

		@Override
		public Purchase createPurchase(Purchase purchase){
			return purchaseRepository.save(purchase);
		}
	 
}
