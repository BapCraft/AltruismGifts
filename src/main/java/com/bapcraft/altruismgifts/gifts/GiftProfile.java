package com.bapcraft.altruismgifts.gifts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.bapcraft.altruismgifts.AltruismGifts;
import com.bapcraft.altruismgifts.json.OfflinePlayerJsonAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.md_5.bungee.api.ChatColor;

public class GiftProfile {
	
	private transient OfflinePlayer player;
	
	public boolean isAllowing = true;
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
		
		// Serialize.
		Gson gson = buildGson();
		String data = gson.toJson(this);
		
		try {
			
			// Write it.
			File f = this.getDataFile();
			if (!f.exists()) {
				
				f.getParentFile().mkdirs();
				f.createNewFile();
				
			}
			
			FileWriter fw = new FileWriter(f);
			fw.write(data);
			fw.close();
			
		} catch (IOException e) {
			
			if (this.player.isOnline()) {
				
				Player p = this.player.getPlayer();
				p.sendMessage("" + ChatColor.RED + "Something happened when saving your Gift information.  Your gift(s) probably sent, but you should notify an admin about this message.");
				
			}
			
			throw new NullPointerException("Something happened for player " + this.player.getUniqueId().toString() + "! (saving)");
			
		}
		
	}
	
	public static GiftProfile load(UUID uuid) {
		
		GiftProfile gp = null;
		
		try {
			
			// Get the reader.
			FileReader fr = new FileReader(GiftProfile.getDataFile(uuid));
			
			// Deserialize.
			Gson gson = buildGson();
			gp = gson.fromJson(fr, GiftProfile.class);
			
		} catch (FileNotFoundException e) {
			
			gp = new GiftProfile(); 
			
		}
		
		gp.player = Bukkit.getOfflinePlayer(uuid); // Populate this so items can be sent back and forth and such.
		return gp;
		
	}
	
	public static GiftProfile load(OfflinePlayer op) {
		return load(op.getUniqueId());
	}
	
	public File getDataFile() {
		return getDataFile(this.player.getUniqueId());
	}
	
	public static File getDataFile(UUID uuid) {
		return new File(new File(AltruismGifts.INSTANCE.getDataFolder(), "data"), String.format("%s.json", uuid.toString()));
	}
	
	public static Gson buildGson() {
		
		GsonBuilder gb = new GsonBuilder();
		
		// Setup.
		gb.enableComplexMapKeySerialization();
		gb.setPrettyPrinting();
		
		// Deal with crap.
		gb.registerTypeAdapter(OfflinePlayer.class, new OfflinePlayerJsonAdapter());
		
		return gb.create();
		
	}
	
}
