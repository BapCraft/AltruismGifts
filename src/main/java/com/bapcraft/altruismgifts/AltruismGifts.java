package com.bapcraft.altruismgifts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class AltruismGifts extends JavaPlugin {
	
	public static final String PREFIX = "" + ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + "Altruism" + ChatColor.DARK_PURPLE + "]"; 
	public static AltruismGifts INSTANCE;
	
	@Override
	public void onEnable() {
		
		INSTANCE = this;
		
	}
	
	@Override
	public void onDisable() {
		
		INSTANCE = null;
		
		this.getCommand("gift").setExecutor(new CommandGift());
		this.getCommand("togglegift").setExecutor(new CommandToggleGifts());
		
		Bukkit.getLogger().info(this.getName() + " is now ready to spread the love!");
		
	}
	
	public static String formatMessage(String message) {
		return String.format("%s " + ChatColor.LIGHT_PURPLE, PREFIX, message);
	}
	
	public static void message(CommandSender sender, String msg) {
		sender.sendMessage(formatMessage(msg));
	}
	
}
