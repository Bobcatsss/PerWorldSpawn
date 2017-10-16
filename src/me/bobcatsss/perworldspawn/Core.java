package me.bobcatsss.perworldspawn;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.bobcatsss.perworldspawn.commands.CommandSetSpawn;
import me.bobcatsss.perworldspawn.commands.CommandSpawn;

public class Core extends JavaPlugin {
	
	private boolean teleportOnRespawn;
	private File locations;
	private FileConfiguration locs;
	
	public void onEnable() {
		createWorldsFile();
		saveDefaultConfig();
		registerCommands();
		registerEvents();
	}
	
	public void onDisable() {
		
	}
	
	public void registerEvents() {
		
	}
	
	public void registerCommands() {
		getCommand("setspawn").setExecutor(new CommandSetSpawn(this));
		getCommand("spawn").setExecutor(new CommandSpawn(this));
	}
	
	public void createWorldsFile() {
		locations = new File(getDataFolder(), "Locations.yml");
		if(!locations.exists()) {
			try {
				locations.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		locs = YamlConfiguration.loadConfiguration(locations);
	}
	
	public void saveLocations() {
		try {
			locs.save(locations);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Location getLocation(String world) {
		if(!getLocations().isConfigurationSection(world)) return null;
		if((Location) getLocations().get(world + ".Location") == null) return null;
		return (Location) getLocations().get(world + ".Location");
	}
	
	public FileConfiguration getLocations() {
		return locs;
	}
	
	public void reloadLocations() {
		locs = YamlConfiguration.loadConfiguration(locations);
	}
	
	public void setTeleportOnRespawn(boolean value) {
		this.teleportOnRespawn = value;
	}
	
	public boolean isTeleportOnRespawn() {
		return this.teleportOnRespawn;
	}

}
