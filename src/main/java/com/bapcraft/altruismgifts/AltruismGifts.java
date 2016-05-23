package com.bapcraft.altruismgifts;

import org.bukkit.plugin.java.JavaPlugin;

public class AltruismGifts extends JavaPlugin {
	
	public static AltruismGifts INSTANCE;
	
	@Override
	public void onEnable() {
		
		INSTANCE = this;
		
	}
	
	@Override
	public void onDisable() {
		
		INSTANCE = null;
		
	}
	
}
