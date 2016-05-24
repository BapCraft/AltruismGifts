package com.bapcraft.altruismgifts.json;

import java.lang.reflect.Type;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class OfflinePlayerJsonAdapter implements JsonSerializer<OfflinePlayer>, JsonDeserializer<OfflinePlayer> {

	@Override
	public JsonElement serialize(OfflinePlayer player, Type type, JsonSerializationContext ctx) {
		return new JsonPrimitive(player.getUniqueId().toString());
	}
	
	@Override
	public OfflinePlayer deserialize(JsonElement ele, Type type, JsonDeserializationContext ctx) throws JsonParseException {
		
		OfflinePlayer op = Bukkit.getOfflinePlayer(UUID.fromString(ele.getAsString()));
		return op.isOnline() ? op.getPlayer() : op;
		
	}
	
}
