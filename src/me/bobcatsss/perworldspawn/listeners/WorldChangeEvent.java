package me.bobcatsss.perworldspawn.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import me.bobcatsss.perworldspawn.Core;

public class WorldChangeEvent implements Listener {
	
	private Core plugin;
	public WorldChangeEvent(Core pl) {
		this.plugin = pl;
	}
	
	@EventHandler
	public void onChange(PlayerChangedWorldEvent e) {
		if(plugin.isTeleportOnWorldChange()) {
			if(plugin.getLocation(e.getPlayer().getWorld().getName()) != null) {
				e.getPlayer().teleport(plugin.getLocation(e.getPlayer().getWorld().getName()));
				e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.SpawnTp")));
			}
		}
	}
}
