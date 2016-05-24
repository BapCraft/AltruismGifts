package com.bapcraft.altruismgifts;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bapcraft.altruismgifts.gifts.GiftProfile;

import static com.bapcraft.altruismgifts.AltruismGifts.message;

public class CommandToggleGifts implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (args.length != 0) return false;
		if (!(sender instanceof Player)) {
			
			message(sender, "You must be a player to use this command!");
			return true;
			
		}
		
		Player p = (Player) sender;
		GiftProfile gp = GiftProfile.load(p.getUniqueId());
		
		gp.isAllowing = !gp.isAllowing;
		gp.save();
		
		message(p, String.format("Toggled gift mode! (Gifts: %s)", gp.isAllowing ? "YES" : "NO"));
		
		return true;
		
	}
	
}
