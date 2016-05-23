package com.bapcraft.altruismgifts.gifts;


public enum GiftingResult {
	
	// Common
	SUCCESS,
	RECIEVER_DISALLOW,
	
	// Errors
	SENDER_NOT_ONLINE,
	RECIEVER_NOT_ONLINE,
	SENDER_NOT_HAVE_ITEM,
	RECIEVER_FULL_INVENTORY;
	
}
