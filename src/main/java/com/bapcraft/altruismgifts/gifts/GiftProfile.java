package com.bapcraft.altruismgifts.gifts;

import org.bukkit.OfflinePlayer;

public class GiftProfile {
	
	private transient OfflinePlayer player;
	
	protected boolean isAllowing = true;
	protected int giftsSent, giftsReceived;
	
	public OfflinePlayer getPlayer() {
		return this.player;
	}
	
	public int getSent() {
		return this.giftsSent;
	}
	
	public int getReceived() {
		return this.giftsReceived;
	}
	
	public void save() {
		// TODO
	}
	
}
