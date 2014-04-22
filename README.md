# Java: The Game

Software Implementation of the Board Game Java

- [Installation Instructions](#Instructions)
- [Running the Game](#Running)
- [Keyboard Controls](#Keyboard)
- [*Team Mask*](#Choking Hazard)
- [MIT License](#MIT)

## Installation Instructions<a name = "Instructions"/>
	
	From the folder : 
	Run the following command to compile and run:
	
            ./RunGame.sh

	The following instruction was used for running test files:

	    ./runTest.sh
	

	Due to a known LWJGL bug, this program works best on Linux.  In Mac, the LWJGL display will not be embedded in the window.  This appears to have been fixed by commit 211b14c8d517967af85ce521f5906cb9f63d824d, but the stable builds were too old, Jenkins nightlies were broken, the compilation scripts had unresolved dependencies.
	
## Running the Game<a name="Running"/>

	From the ready-executable:
	
	1) Make sure the .jar file is in the same folder as all the resources. 
	2) Run the following command: 
		java -jar ChokingHazard.jar

## Keyboard Controls<a name="Keyboard"/>

	Menus and Dialog Boxes can navigated by the mouse, or the following keyboard controls:
	
	Create New Game:			Alt-N
	Saving Current Game:		Alt-S
	Loading/Opening Past Game:	Alt-O
	Exiting Java:				Alt-Q
	Dialog Boxes:				TAB
	
	The rest of the game's keyboard controls are located in the Game Help Menu, which can be accessed by:
	
	Game Help Menu:				Alt-H
	
	
## *Team Mask*<a name="Team Mask"/>

This game was created for Dave Small's Object Oriented Programming class at the University of Florida in Spring 2014 by Team Mask:

- [@jorgep19](https://github.com/jorgep19)
- [@kenkron](https://github.com/kenkron)
- [@ktn](https://github.com/ktn)
- [@nwcaldwell](https://github.com/nwcaldwell)
- [@ssyyddnneeyy](https://github.com/ssyyddnneeyy)
- [@wmacfarlane](https://github.com/wmacfarlane)

## MIT License<a name="MIT"/>

    The MIT License (MIT)
    
    Copyright (c) 2014 Team Mask
    
    Permission is hereby granted, free of charge, to any person obtaining a copy of
    this software and associated documentation files (the "Software"), to deal in
    the Software without restriction, including without limitation the rights to
    use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
    the Software, and to permit persons to whom the Software is furnished to do so,
    subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
    FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
    COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
    IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
    CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
