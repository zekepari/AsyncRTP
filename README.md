# AsyncRTP 1.18.2
AsyncRTP is a Minecraft plugin designed for PaperMC servers which enables fast random teleporting around the world with various config options, such as asynchronous safe location finding.

If you find bugs, have suggestions, or need support, contact Zeke#5958 on discord.

## Planned Features
Towny, Factions and WorldGuard integration.

## Default configuration
Default plugin messages. You can find formatting [here](https://docs.adventure.kyori.net/minimessage/).
```yml
Messages:
  Random: "<green>Sending you to <blue><i><x>, <y>, <z>"
  Bed: "<green>Sending you to your bed's spawn"
```
CommandRTP allows for usage of the /wild command with optional cooldown or disabled worlds.
```yml
CommandRTP:
  Enabled: true
  Cooldown: 30 #Seconds (Set to 0 to disable)
  DisabledWorlds: #The command won't work in these worlds.
    - "world_nether"
    - "world_the_end"
  Messages:
    World: "<red>This command cannot be used in this world"
    Cooldown: "<red>Please wait <second>s before using this command again"
```
This config will RTP players after they click the respawn button.
```yml
RespawnRTP:
  Enabled: true
  CheckForBed: true #Should AsyncRTP respawn the player at their bed if they have one?
  RespawnWorld: "world" #Put "" to RTP players in the world they died in.
```
This option will teleport new players right after joining.
```yml
NewPlayerRTP:
  Enabled: true
  World: "world" #The world new players will be randomly teleported in
```
The block range in which AsyncRTP will attempt to find a random spawn location. In the preset example, AsyncRTP will find a location 100 blocks in all directions from (0,0)
```yml
Range: #Random teleport will occur within these coordinates
  X: 100 #West to east from (0,0)
  Z: 100 #North to south from (0,0)
```
Disabling SafeBlocks is not recommended as players may spawn in caves, lava, or the ocean.
```yml
SafeBlocks:
  Enabled: true
  MaxAttempts: 100 #Attempts AsyncRTP will take at finding a safe teleport location before cancelling.
  Blocks:
    - "GRASS"
    - "DIRT"
    - "GRASS_BLOCK"
    - "RED_SAND"
    - "SAND"
    - "CLAY"
    - "TERRACOTTA"
    - "RED_TERRACOTTA"
    - "ORANGE_TERRACOTTA"
    - "COBBLESTONE"
    - "COARSE_DIRT"
    - "PODZOL"
```
Sound the player and surrounding players will hear after teleporting to a new location (Does not occur on bed respawn). A complete sound list can be found [here](https://www.digminecraft.com/lists/sound_list_pc.php).
```yml
TeleportSound:
  Enabled: true
  Sound: "ENTITY_EXPERIENCE_ORB_PICKUP"
  Volume: 1 #0-10
  Pitch: 1 #0.5-2
```
