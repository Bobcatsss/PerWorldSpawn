package me.bobcatsss.perworldspawn.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.bobcatsss.perworldspawn.Core;

public class RespawnEvent implements Listener {
	
	private Core plugin;
	public RespawnEvent(Core pl) {
		this.plugin = pl;
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		if(plugin.isTeleportOnRespawn()) {
			if(plugin.getLocation(e.getPlayer().getWorld().getName()) != null) {
				e.getPlayer().teleport(plugin.getLocation(e.getPlayer().getWorld().getName()));
				e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.SpawnTp")));
			}
		}
	}
}
