package fr.lucidiax.episode6;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	Scroller scroller;
	
	public void onEnable(){
		scroller = new Scroller("§boici mon message §oqui défile !   §eJ'en suis extremement fier !    §5J'aime les yaourts, ils sont trop bons !", 70, 5, '§');
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			@Override
			public void run(){
				for(Player player : Bukkit.getOnlinePlayers())
					TitleManager.setPlayerList(player, scroller.next(), "§bBienvenue sur mon serveur !");
			}
		}, 0L, 3L);
	}
	
}
