# CombatLogger
Stops players from logging out during combat.
## Description:
Players tend to log out while they're fighting, and that can be annoying. Once a player starts fighting a player, there will be a period of seconds created, and if one of the players log out/disconnect in that period, they will be killed. The time and messages are configurable, and you can also configure commands to be blocked during combat too.
## Config:
```yaml
# Seconds in combat.
interval: 10
# Blocked commands during combat.
blocked-commands:
- /spawn
- /tp
- /tpa
- /warp
# Messages.
in-combat: '&eYou have entered combat, logging out now will cause your death. Please
  wait {TIME} seconds.'
out-of-combat: You are no longer in combat, you may now logout and run commands.
blocked-cmd: '&cYou cannot use this command during combat'
```
## Download: 
[Click here](https://github.com/XShockinFireX/CombatLogger/releases)