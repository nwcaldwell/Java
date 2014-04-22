package gamecontrollers.save;

import gamecontrollers.commands.gameplaycommands.*;

public interface CommandVisitor {

    public void visit(DevMoveCommand c);

    public void visit(DrawCardFromDeckCommand c);

    public void visit(DrawFestivalCardCommand c);

    public void visit(EndFestivalCommand c);

    public void visit(EndFestivalTurnCommand c);

    public void visit(EndFinalTurnCommand c);

    public void visit(PlaceIrrigationTileCommand c);

    public void visit(PlacePalaceCommand c);

    public void visit(PlaceRiceTileCommand c);

    public void visit(PlaceThreeTileCommand c);

    public void visit(PlaceTileCommand c);

    public void visit(PlaceTwoTileCommand c);

    public void visit(PlaceVillageTileCommand c);

    public void visit(PlayPalaceCardCommand c);

    public void visit(ShuffleDeckCommand c);

    public void visit(StartFestivalCommand c);

    public void visit(UseExtraActionTokenCommand c);
}
