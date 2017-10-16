package me.bobcatsss.perworldspawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.bobcatsss.perworldspawn.commands.CommandReload;
import me.bobcatsss.perworldspawn.commands.CommandSetSpawn;
import me.bobcatsss.perworldspawn.commands.CommandSpawn;
import me.bobcatsss.perworldspawn.listeners.RespawnEvent;
import me.bobcatsss.perworldspawn.listeners.WorldChangeEvent;

public class Core extends JavaPlugin {
	
	private boolean teleportOnRespawn; 
	private boolean spawnOnWorldChange;
	
	LocationsConfig locations;
	
	public void onEnable() {
		saveDefaultConfig();
		locations = new LocationsConfig(this, "Locations.yml");
		loadValues();
		registerCommands();
		registerEvents();
	}
	
	public void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new RespawnEvent(this), this);
		pm.registerEvents(new WorldChangeEvent(this), this);
	}
	
	public void registerCommands() {
		getCommand("setspawn").setExecutor(new CommandSetSpawn(this));
		getCommand("spawn").setExecutor(new CommandSpawn(this));
		getCommand("pws").setExecutor(new CommandReload(this));
	}
	
	public void saveLocations() {
		locations.save();
	}
	
	public Location getLocation(String world) {
		if(!getLocations().getConfig().isConfigurationSection(world)) return null;
		if((Location) getLocations().getConfig().get(world + ".Location") == null) return null;
		return (Location) getLocations().getConfig().get(world + ".Location");
	}
	
	public void loadValues() {
		if(getConfig().isConfigurationSection("Teleport-On-Respawn")) {
			teleportOnRespawn = getConfig().getBoolean("Teleport-On-Respawn");
		}
		if(getConfig().isConfigurationSection("Spawn-On-World-Change")) {
			spawnOnWorldChange = getConfig().getBoolean("Spawn-On-World-Change");
		}
	}
	public LocationsConfig getLocations() {
		return locations;
	}
	
	public void getSpawnOnWorldChange(boolean value) {
		this.spawnOnWorldChange = value;
	}
	
	public boolean isTeleportOnWorldChange() {
		return this.spawnOnWorldChange;
	}
	
	public void setTeleportOnRespawn(boolean value) {
		this.teleportOnRespawn = value;
	}
	
	public boolean isTeleportOnRespawn() {
		return this.teleportOnRespawn;
	}

}
