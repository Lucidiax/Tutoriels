package fr.lucidiax.episode4;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin implements Listener {
	
	public Inventory i;
	
	public void onEnable(){
		i = Bukkit.createInventory(null, 54, "§aMon inventaire");
		i.setItem(2, makeItem(Material.APPLE, 45, "§aUne pomme géniale !", "§5Voici ma", "§6Super description", "§4De la mort !"));
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	private ItemStack makeItem(Material material, int number, String name, String... lore){
		ItemStack stack = new ItemStack(material, number);
		ItemMeta meta = stack.getItemMeta();
		if(name != null)
			meta.setDisplayName(name);
		if(lore != null){
			List<String> desc = new ArrayList<String>();
			for(String s : lore)
				desc.add(s);
			meta.setLore(desc);
		}
		stack.setItemMeta(meta);
		return stack;
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		if(event.getMessage().toLowerCase().contains("test")){
			event.setCancelled(true);
			event.getPlayer().openInventory(i);
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		Inventory inventory = event.getInventory();
		if(inventory.getName().equals(i.getName())){
			event.setCancelled(true);
			Player player = (Player) event.getWhoClicked();
			ItemStack stack = event.getCurrentItem();
			if(stack == null || !stack.hasItemMeta())
				return;
			if(stack.getItemMeta().getDisplayName().equals("§aUne pomme géniale !")){
				player.closeInventory();
				player.sendMessage("§eBravo, tu viens de cliquer sur " + stack.getItemMeta().getDisplayName() + " §e!");
				player.addPotionEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(Integer.MAX_VALUE, 2));
			}
		}
	}
	
}
