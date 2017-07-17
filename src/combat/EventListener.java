package combat;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.utils.TextFormat;

public class EventListener implements Listener {

    private Combat plugin;

    public EventListener(Combat plugin) {
        this.plugin = plugin;
    }

    private Combat getPlugin() {
        return this.plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(this.getPlugin().inCombat(player)) {
            player.kill();
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        this.getPlugin().removeCombat(event.getEntity());
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        EntityDamageByEntityEvent ev;
        Entity entity = event.getEntity();
        if(event instanceof EntityDamageByEntityEvent && (ev = (EntityDamageByEntityEvent)event).getDamager() instanceof Player && entity instanceof Player) {
            Player player = (Player) entity;
            Player damager = (Player) ev.getDamager();
            for(Player pl : new Player[]{player, damager}) {
                this.getPlugin().setCombat(pl);
            }
        }
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage().split(" ")[0].toLowerCase();
        if(this.getPlugin().getConfiguration().blocked.contains(command)) {
            event.setCancelled();
            player.sendMessage(TextFormat.colorize(this.getPlugin().getConfiguration().blockedCmd));
        }
    }
}