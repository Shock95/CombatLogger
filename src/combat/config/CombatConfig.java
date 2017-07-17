package combat.config;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.SimpleConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombatConfig extends SimpleConfig {

    public CombatConfig(PluginBase plugin) {
        super(plugin);
    }

    @Path (value = "interval")
    public int interval = 10;

    @Path (value = "blocked-commands")
    public List<String> blocked = new ArrayList<>(Arrays.asList("/spawn", "/tp", "/tpa", "/warp"));

    @Path (value = "in-combat")
    public String inCombat = "&eYou have entered combat, logging out now will cause your death. Please wait {TIME} seconds.";

    @Path (value = "out-of-combat")
    public String outCombat = "&cYou are no longer in combat, you may now logout and run commands.";

    @Path (value = "blocked-cmd")
    public String blockedCmd = "&cYou cannot use this command during combat";
}