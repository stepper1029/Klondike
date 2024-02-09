# Klondike

A version of the card game Solitaire where players sort a standard 52-card deck by suit. This program includes 
three versions of the game: Basic Klondike, Whitehead Klondike, and Limited Draw Klondike. These versions have
slightly altered rulesets, like whether or not the colors in a stack alternate or how many cards you're allowed
to draw at a time.

# Project Overview

## [Model](src/cs3500/klondike/model)
The model holds the back-end logic of the game, including data structures for cards and decks, rules related to
legal moves, how to deal the cards, etc.

## [View](src/cs3500/klondike/model)
The view communicates the current state of the game to the player, showing them how the cards are laid out and which
ones are face up.

## [Controller](src/cs3500/klondike/view)
The controller is in charge of the flow of the program. When the view receives input from the user, the controller
decides how to appropriately handle it by passing it to the model or outputting an error message. Same for data from
the model, the controller decides how to relay this to the player.

These are the main components of the game, but each of these folders contains much more detail on the interactions
and specific methods of the program.
