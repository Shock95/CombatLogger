package combat.task;

import cn.nukkit.utils.TextFormat;
import combat.Combat;
import cn.nukkit.scheduler.PluginTask;

public class CombatScheduler extends PluginTask<Combat> {

    public CombatScheduler(Combat plugin) {
        super(plugin);
    }

    public void onRun(int currentTick) {
        this.getOwner().getPlayers().forEach((player, time) -> {
            if(time + this.getOwner().getConfiguration().interval * 1000 < System.currentTimeMillis()) {
                if(player.isOnline()) {
                    player.sendMessage(TextFormat.colorize(this.getOwner().getConfiguration().outCombat));
                }
                this.getOwner().removeCombat(player);
            }
        });
    }
}

