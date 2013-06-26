package pl.noip.evaldor.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import pl.noip.evaldor.Evaldor;
import pl.noip.evaldor.Messages;
import pl.noip.evaldor.util.StringUtils;

public class CommandSay implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (!Evaldor.hasPerm(sender, "evaldor.say")) {
			sender.sendMessage(Messages.noPermission);
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage(Messages.wrongUsage + command.getUsage());
			return true;
		}
		String broadcastMessage = Evaldor.inst().getConfig().getString("chat.broadcast-format");
		String msg = args[0];
		for (int i = 1; i < args.length; i++) { msg += " " + args[i]; }
		broadcastMessage = StringUtils.colorize(broadcastMessage.replaceAll("\\{message\\}", msg));
		Bukkit.getServer().broadcastMessage(broadcastMessage);
		
		return true;
	}
	
}
