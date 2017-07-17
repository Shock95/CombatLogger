package combat;

import combat.config.CombatConfig;
import combat.task.CombatScheduler;
import cn.nukkit.Player;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class Combat extends PluginBase {

    private CombatConfig config;
    private static ConcurrentHashMap<Player, Long> inCombat = new ConcurrentHashMap<>();

    public void onEnable() {
        this.initConfig();
        this.registerTask();
        this.getServer().getPluginManager().registerEvents(new EventListener(this), this);
        this.getLogger().notice("CombatLogger by XShockinFireX has been successfully enabled.");
    }

    private void initConfig() {
        this.config = new CombatConfig(this);
        this.config.load();
        this.config.save();
    }

    private void registerTask() {
        this.getServer().getScheduler().scheduleRepeatingTask(new CombatScheduler(this), 20);
    }

    public CombatConfig getConfiguration() {
        return this.config;
    }

    /// API ///

    public ConcurrentHashMap<Player, Long> getPlayers() {
        return inCombat;
    }

    public boolean inCombat(Player player) {
        return inCombat.containsKey(player);
    }

    public void removeCombat(Player player) {
        for(Iterator iterator = inCombat.keySet().iterator(); iterator.hasNext();) {
            Player next = (Player) iterator.next();
            if(!next.equals(player)) continue;
            iterator.remove();
        }
    }

    public void setCombat(Player player) {
        String message = TextFormat.colorize(this.getConfiguration().inCombat.replace("{TIME}", String.valueOf(this.getConfiguration().interval)));
        if(this.inCombat(player)) {
            if (System.currentTimeMillis() * 1000 - inCombat.get(player) < 60) {
                player.sendMessage(message);
            }
        } else {
            player.sendMessage(message);
        }
        inCombat.put(player, System.currentTimeMillis());
    }
}
