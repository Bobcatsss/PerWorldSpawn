package me.bobcatsss.perworldspawn.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bobcatsss.perworldspawn.Core;

public class CommandReload implements CommandExecutor {

	private Core plugin;

	public CommandReload(Core pl) {
		this.plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("perworldspawn.reload")) {
				if (args.length >= 1) {
					if (args[0].equalsIgnoreCase("reload")) {
						plugin.reloadConfig();
						plugin.saveConfig();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("Messages.Reloaded-Config")));
						return true;
					}
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.Reload-Usage")));
					return true;
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.Reload-Usage")));
				return true;
			}
			p.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("Messages.NoPermission")));
			return true;
		}
		return false;
	}

}
