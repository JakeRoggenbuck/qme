QME
===

This is the GitHub repository for our game. Here are some descriptions of the various folders:<br/>
**res**: the folder for audio clips, sound files, etc.<br/>
**data**: world saves, preference settings, etc. This folder will be specific to each user.<br/>
**src**: source code for the game.

If you need to add something to this page but aren't sure how, please contact a programmer.

The code can only be built with Eclipse for now. If you need a new release, contact a programmer
to build it, or check the "Releases" tab.

The Javadoc folder has been removed, because every tiny change in the code requires a massive amount
of new git adds. To generate it, go into Eclipse, select Project > Generate Javadoc, and select all
folders in `src` with a check mark. Or, ask a programmer.

Changelog for pre0:
  - Made the game
  - Added scrolling
  - Added buttons
  
Changelog for pre1:
  - Fixed mouse clicks
  - Added game selection menu and next player button
  - Added a game state system and improved world gen
  - Added tile tooltips
  - Added button and tile highlights upon hovering
  
Notes for pre1:
  - 2072 lines of code! Yay!
  - There is currently no way to see the list of players or the turn count. Coming soon ...
