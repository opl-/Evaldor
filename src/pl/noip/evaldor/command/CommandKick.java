package pl.noip.evaldor.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.noip.evaldor.Messages;

public class CommandKick implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (args.length == 1) {
			Player target = sender.getServer().getPlayer(args[0]);
			if (target != null) {
				Bukkit.getServer().getPlayer(target.getName()).kickPlayer(Messages.defaultkickmsg);
			}
		else if (args.length == 0) {
				sender.sendMessage(Messages.wrongUsage+cmd.getUsage());
				return true;
		} else {
				String reason = args[1];
				for (int i = 1; i < args.length; i++) { reason += " " + args[i]; }
			}
		}
		return true;
	}

}
