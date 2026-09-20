# [TODO] Left Forgotten - 1.2.0 Release

***All changes apply for both 1.20.1 and 1.21.1 unless specified otherwise***

### ***GENERAL***
- [API WIP] Mod no longer depends on Architectury API and now includes Millie's Core Libraries within itself temporarely until full release of it
- New changelog format (But no more dev stuff listed)

### ***CONFIGURATION***
- [TODO] Config now has a UI. Accessible on Fabric via Mod Menu or through the mod list screen on Forge/Ne
- Removed config key `overrideOldPackId`
- Merged config keys `remodelCraftingTable` and `remodelFurnace` into `enableWorkstationRemodel`
- Renamed config key `remodelChests` to `enableChestRemodel`
- Renamed config key `forceSteveSkin` to `enableSteveSkins`
- Renamed config key `generateFarlands` to `enableFarlandsGeneration`
- Renamed config key `forceBlockyLighting` to `enableBlockyLighting`
- Renamed config key `forceOldPack` to `enableOldResourcepacks`

### ***NEW CONTENT***
- Items now render as billboards while on-ground
- [WIP] Made the Chest, Crafting Table and Furnace also remodel in GUI
- Updated textures for Brittle Bedrock and Feature Void

### ***CHANGES***
- Now going back between `left_forgotten:alpha_minecraft` and any other dimension doesn't reset player's hunger, it just pauses as it is and resumes when you're out
- Players using Better Combat are no longer granted a attack speed boost in `left_forgotten:alpha_minecraft`
- Mount health is no longer hidden when player is in creative, matching vanilla
- [WIP] Feature Void now renders similarly to the Barrier Block
- [TODO] All blocks and items added by the mod now have matching tags with their vanilla counterparts (Including vanilla, `c` or "common" tags, neo/forge-specific and fabric-specific tags)

### ***BUG FIXES/TECHNICAL CHANGES***
- Fixed effects not applying from eaten Suspicious Stews
- Fixed possible crashes on dedicated servers when attempting to eat something in `left_forgotten:alpha_minecraft`
- Fixed possible crashes on dedicated servers, ghostblocks etc. coming from the Feature Void dev block
- Fixed Redstone Ore not generating due to a typo ([#5](https://github.com/JustMili0/LeftForgotten/issues/5))
- [TODO] Fixed Chest remodels breaking with block entity optimization mods
- Fixed and reworked the Music Player, now any issues it could've had should be gone

<hr>

## Upcoming - 1.3 Release
- *"More Below"*

## Upcoming - 1.4 Release
- *"Who's there?"*

<hr>

***Dev notes***<br>
Sorry for the wait TvT
