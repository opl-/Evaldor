package pl.noip.evaldor.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.noip.evaldor.Evaldor;
import pl.noip.evaldor.Messages;

public class CommandKickAll implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!Evaldor.hasPerm(sender, "evaldor.kickall")) {
			Evaldor.noPerm(sender);
		} else {
			Player[] plr = Bukkit.getOnlinePlayers();
			for (int i = 0; i < plr.length; i++) { // Pierwszy element tablicy to 0 a nie 1 ;)
				Bukkit.getServer().getPlayer(plr[i].getName()).kickPlayer(Messages.defaultkickallreason);
		}
		}
		return true;
	}

}
