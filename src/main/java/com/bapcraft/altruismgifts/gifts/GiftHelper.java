package com.bapcraft.altruismgifts.gifts;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class GiftHelper {
	
	public static GiftingResult trySendGift(ItemStack item, GiftProfile sender, GiftProfile reciever) {
		
		if (reciever.isAllowing) {
			
			// Get the sender's inventory.
			OfflinePlayer senderPlayer = (Player) sender.getPlayer();
			if (!senderPlayer.isOnline()) return GiftingResult.SENDER_NOT_ONLINE;
			Player sp = (Player) senderPlayer;
			PlayerInventory spi = sp.getInventory();
			
			// Get the reciever's inventory.
			OfflinePlayer recieverPlayer = (Player) reciever.getPlayer();
			if (!recieverPlayer.isOnline()) return GiftingResult.RECIEVER_NOT_ONLINE;
			Player rp = (Player) recieverPlayer;
			PlayerInventory rpi = rp.getInventory();
			
			// See if the sender has the item.
			ItemStack source = null;
			int srcSlot = -1;
			for (int i = 0; i < spi.getSize(); i++) {
				
				ItemStack slotItem = spi.getItem(i);
				
				if (slotItem.isSimilar(item) && slotItem.getAmount() >= item.getAmount()) {
					
					source = slotItem;
					srcSlot = i;
					break;
					
				}
				
			}
			
			if (source == null) return GiftingResult.SENDER_NOT_HAVE_ITEM;
			
			// See if the reciever can get the item.
			int destSlot = -1;
			for (int i = 0; i < rpi.getSize(); i++) {
				
				ItemStack slotItem = rpi.getItem(i);
				
				// FIXME Cheaty!  Doesn't account for itemstack that can take the item.
				if (slotItem == null || slotItem.getAmount() == 0) {
					
					destSlot = i;
					break;
					
				}
				
			}
			
			if (destSlot == -1) return GiftingResult.RECIEVER_FULL_INVENTORY;
			
			// Deliver the item!
			ItemStack real = source.clone();
			real.setAmount(source.getAmount() - item.getAmount());
			spi.setItem(srcSlot, real);
			rpi.setItem(destSlot, source);
			
			// Up the stats.
			sender.giftsSent++;
			reciever.giftsReceived++;
			sender.save();
			reciever.save();
			
			return GiftingResult.SUCCESS;
			
		}
		
		return GiftingResult.RECIEVER_DISALLOW;
		
	}
	
}
