package cs3500.klondike.view;

import java.io.IOException;

/**
 * An interface that represents the textual view of a game. Allows the player to see and
 * understand the game state.
 */
public interface TextualView {
  /**
   * Renders a model in some manner (e.g. as text, or as graphics, etc.).
   * @throws IOException if the rendering fails for some reason
   */
  void render() throws IOException;
}





