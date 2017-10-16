package me.bobcatsss.perworldspawn;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LocationsConfig {
	
	private Core p;
	private String fileName;
	private File file = null;
	private YamlConfiguration config;

	public LocationsConfig(Core p, String fileName) {
		this.p = p;
		this.fileName = fileName;
	}

	public void createNewFileConfig() {
		if (!getFile().exists()) {
			try {
				getFile().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void createDefaultFileConfig() {
		p.saveResource("fileName", false);
	}

	private File getFile() {
		if (file == null) {
			file = new File(p.getDataFolder(), fileName);
			return file;
		}
		return file;
	}

	public FileConfiguration getConfig() {
		if (config == null) {
			config = YamlConfiguration.loadConfiguration(getFile());
			return config;
		}
		return config;
	}

	public void reload() {
		config = YamlConfiguration.loadConfiguration(getFile());
	}

	public void save() {
		try {
			getConfig().save(getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
