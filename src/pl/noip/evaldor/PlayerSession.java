package pl.noip.evaldor;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerSession {
	private List<Player> ignoredPlayers = new ArrayList<Player>();
	public long lastMessage = 0L;
	
	public List<Player> getIgnoredPlayers() {
		return ignoredPlayers;
	}
	
	public boolean ignorePlayer(String player) {
		Player p = Bukkit.getPlayer(player);
		if (p != null) {
			ignoredPlayers.add(p);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isIgnoring(Player player) {
		return ignoredPlayers.contains(player);
	}
}
