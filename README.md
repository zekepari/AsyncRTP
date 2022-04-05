# AsyncRTP
AsyncRTP is a Minecraft plugin designed for PaperMC servers which enables asynchronous random teleporting around the world with various config options.

```yml
Messages: #Formatting guide: https://docs.adventure.kyori.net/minimessage/
  Random: "<green>Sending you to <blue><i><x>, <y>, <z>"
  Bed: "<green>Sending you to your bed's spawn"

CommandRTP: #/wild
  Enabled: true
  Cooldown: 30 #Seconds (Set to 0 to disable)
  DisabledWorlds: #The command won't work in these worlds.
    - "world_nether"
    - "world_the_end"
  Messages:
    World: "<red>This command cannot be used in this world"
    Cooldown: "<red>Please wait <second>s before using this command again"

RespawnRTP:
  Enabled: true
  CheckForBed: true #Should AsyncRTP respawn the player at their bed if they have one?
  RespawnWorld: "world" #Put "" to RTP players in the world they died in.

NewPlayerRTP:
  Enabled: true
  World: "world" #The world new players will be randomly teleported in

Range: #Random teleport will occur within these coordinates
  X: 100 #West to east from (0,0)
  Z: 100 #North to south from (0,0)

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

TeleportSound:
  Enabled: true
  Sound: "ENTITY_EXPERIENCE_ORB_PICKUP" #Sounds list: https://www.digminecraft.com/lists/sound_list_pc.php
  Volume: 1 #0-10
  Pitch: 1 #0.5-2```
