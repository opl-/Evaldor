package pl.noip.evaldor.command;


import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import pl.noip.evaldor.Evaldor;
import pl.noip.evaldor.Messages;

public class CommandSpawn implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 0) {
			((Player) sender).teleport(((Player) sender).getWorld().getSpawnLocation());
			sender.sendMessage(Messages.spawnDefaultTeleported);
			return true;
		} else if (args.length == 1) {
			if (sender.getServer().getWorld(args[0]) != null) {
				((Player) sender).teleport(sender.getServer().getWorld(args[0]).getSpawnLocation());
				sender.sendMessage(Messages.spawnTeleported.replaceAll("\\{spawn\\}", args[0]));
				return true;
			} else {
				if (Evaldor.inst().spawns.getString(args[0]+".world") != null && sender.getServer().getWorld(Evaldor.inst().spawns.getString(args[0]+".world")) != null) {
					YamlConfiguration config = Evaldor.inst().spawns;
					((Player) sender).teleport(new Location(sender.getServer().getWorld(Evaldor.inst().spawns.getString(args[0]+".world")), config.getDouble(args[0]+".x"), config.getDouble(args[0]+".y"), config.getDouble(args[0]+".z"), (float)config.getDouble(args[0]+".yaw"), (float)config.getDouble(args[0]+".pitch")));
					sender.sendMessage(Messages.spawnTeleported.replaceAll("\\{spawn\\}", args[0]));
					return true;
				} else {
					sender.sendMessage(Messages.spawnDoesntExists.replaceAll("\\{spawn\\}", args[0]));
					return true;
				}
			}
		} else if (args.length == 2) {
			return true; // XXX
		} else {
			sender.sendMessage(Messages.wrongUsage+command.getUsage());
			return true;
		}
	}
}
