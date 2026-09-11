# [TODO] Left Forgotten - 1.2.0 Release

***All changes apply for both 1.20.1 and 1.21.1 unless specified otherwise***

### ***GENERAL***
- [API WIP] Mod no longer depends on Architectury API and now includes Millie's Core Libraries within itself

### ***ADDED***
- [TODO] Config now has a UI. Accessible on Fabric via Mod Menu or through the mod list screen on Forge/Neo
- Items now render as billboards while on-ground


### ***BUG FIXES/TECHNICAL CHANGES***
- [TODO] Fixed Chest remodels breaking with block entity optimization mods
- Fixed Redstone Ore not generating due to a typo ([#5](https://github.com/JustMili0/LeftForgotten/issues/5))
- Going back and forth between `left_forgotten:alpha_minecraft` and the Overworld no longer resets player's hunger
- [TODO] Fully accounted for every block and item tag for every and item block added
- Fixed.. something in `PreHungerHealing` class (previously `AlphaFoodSystem`)

### ***DEV STUFF***
- Renamed mixin classes to match what they're actually mixin into
- Moved all core pakcages and classes to `net.justmili.leftforgotten.core`
- Replaced CommonClient's `inAlpha` and `notInAlpha` boolean functions with checks via new class `Versions` for future updates
- [TODO] Moved most block classes to just sit in `BlockRegistry`

<hr>

## Upcoming - 1.3 Release
- *"More Below"*

## Upcoming - 1.4 Release
- *"Who's there?"*

<hr>

***Dev notes***<br>
Sorry for the wait TvT
