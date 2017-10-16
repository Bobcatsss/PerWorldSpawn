package me.bobcatsss.perworldspawn.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bobcatsss.perworldspawn.Core;

public class CommandSetSpawn implements CommandExecutor {
	
	private Core plugin;
	public CommandSetSpawn(Core pl) {
		this.plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(args.length == 0) {
				if(p.hasPermission("perworldspawn.setspawn")) {
					plugin.getLocations().set(p.getWorld().getName() + ".Location", p.getLocation());
					plugin.saveLocations();
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.SpawnSet")).replace("%world%", p.getWorld().getName()));
					return true;
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.NoPermission")));
				return true;
			}
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.Usage")));
			return true;
		}
		return false;
	}

}
