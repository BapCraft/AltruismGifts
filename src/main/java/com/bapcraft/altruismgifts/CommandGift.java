package com.bapcraft.altruismgifts;

import static com.bapcraft.altruismgifts.AltruismGifts.message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import com.bapcraft.altruismgifts.gifts.GiftHelper;
import com.bapcraft.altruismgifts.gifts.GiftProfile;
import com.bapcraft.altruismgifts.gifts.GiftingResult;

public class CommandGift implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (args.length != 1) return false;
		if (!(sender instanceof Player)) {
			
			message(sender, "You must be a player to use this command!");
			return true;
			
		}
		
		Player snder = (Player) sender;
		String recName = args[0];
		Player recer = Bukkit.getPlayer(recName);
		
		if (recer == null) {
			
			message(sender, "That player isn't online!");
			return true;
			
		}
		
		GiftProfile sp = GiftProfile.load(snder);
		GiftProfile rp = GiftProfile.load(recer);
		
		PlayerInventory pi = snder.getInventory(); 
		int slot = pi.getHeldItemSlot();
		
		if (pi.getItem(slot) == null) {
			
			message(sender, "There's nothing in your hand!");
			return true;
			
		}
		
		GiftingResult gr = GiftHelper.directSend(slot, sp, rp);
		
		switch (gr) {
			
			case RECIEVER_DISALLOW:
				
				message(sender, "That player isn't allowing to be sent gifts.");
				break;
				
			case RECIEVER_FULL_INVENTORY:
				
				message(sender, "That player has a full inventory!");
				break;
				
			case RECIEVER_NOT_ONLINE:
				
				message(sender, "That player isn't online!");
				break;
				
			case SENDER_NOT_HAVE_ITEM:
				
				message(sender, "You don't have that item? O.o?");
				break;
				
			case SENDER_NOT_ONLINE:
				
				message(sender, "You're not online? O.o?");
				break;
				
			case SUCCESS:
				
				message(sender, "Success!  You gifted: " + recer.getDisplayName());
				message(recer, "You were sent a gift by " + snder.getDisplayName() + ChatColor.LIGHT_PURPLE + "!");
				break;
				
			default:
				break;
			
		}
		
		return true;
		
	}
	
}
