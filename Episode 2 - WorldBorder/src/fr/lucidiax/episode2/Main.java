package fr.lucidiax.episode2;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	public void onEnable(){
		for(World world : Bukkit.getWorlds()){
			WorldBorder wb = world.getWorldBorder();
			wb.setCenter(0, 0);
			wb.setSize(1000);
			wb.setSize(100, 30);
			wb.setDamageAmount(0.1);
			wb.setDamageBuffer(10);
			wb.setWarningDistance(2);
			wb.setWarningTime(5);
			Bukkit.broadcastMessage("Le worldborder du monde " + world.getName() + " à bien été défini !");
		}
	}
	
}
