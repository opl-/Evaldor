package pl.noip.evaldor.command;

import java.io.IOException;


import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.noip.evaldor.Evaldor;
import pl.noip.evaldor.Messages;

public class CommandSetSpawn implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 0) {
			Player p = ((Player) sender);
			p.getWorld().setSpawnLocation((int)p.getLocation().getX(), (int)p.getLocation().getY(), (int)p.getLocation().getZ());
			sender.sendMessage(Messages.setspawnChanged.replaceAll("\\{spawn\\}", p.getWorld().getName())); // TODO: zapisac pitch i yaw dla domyslnych swiatow
			return true;
		} else if (args.length == 1) {
			if (!args[0].equalsIgnoreCase(((Player) sender).getWorld().getName())) {
				for (World w : sender.getServer().getWorlds()) {
					if (args[0].equalsIgnoreCase(w.getName())) {
						sender.sendMessage(Messages.setspawnCantOverrideDefault.replaceAll("\\{spawn\\}", w.getName()));
						return true;
					}
				}
				Player p = (Player) sender;
				Evaldor.inst().spawns.set(args[0]+".world", p.getWorld().getName());
				Evaldor.inst().spawns.set(args[0]+".x", p.getLocation().getX());
				Evaldor.inst().spawns.set(args[0]+".y", p.getLocation().getY());
				Evaldor.inst().spawns.set(args[0]+".z", p.getLocation().getZ());
				Evaldor.inst().spawns.set(args[0]+".pitch", p.getLocation().getPitch());
				Evaldor.inst().spawns.set(args[0]+".yaw", p.getLocation().getYaw());
				try {Evaldor.inst().spawns.save(Evaldor.inst().spawnsFile);} catch (IOException e) {
					e.printStackTrace();
					sender.sendMessage(Messages.setspawnError.replaceAll("\\{spawn\\}", args[0]));
					return true;
				}
				sender.sendMessage(Messages.setspawnChanged.replaceAll("\\{spawn\\}", args[0]));
				return true;
			} else {
				Player p = ((Player) sender);
				p.getWorld().setSpawnLocation((int)p.getLocation().getX(), (int)p.getLocation().getY(), (int)p.getLocation().getZ());
				sender.sendMessage(Messages.setspawnChanged.replaceAll("\\{spawn\\}", p.getWorld().getName())); // TODO: zapisac pitch i yaw dla domyslnych swiatow
				return true;
			}
		} else {
			sender.sendMessage(Messages.wrongUsage+command.getUsage());
			return true;
		}
	} // TODO: napisac dodawanie wlasnych spawnow
}
