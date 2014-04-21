package models.board;

//TODO [Jorge][Kevin]

import models.board.TileComponentContents.Irrigation;
import models.board.TileComponentContents.Palace;
import models.board.TileComponentContents.Rice;
import models.board.TileComponentContents.Village;

public interface  TileVisitor {

    public void visit(TileComponent tcc);

    public void visit(Palace p);

    public void visit(Village v);

    public void visit(Rice r);

    public void visit(Irrigation i);
}
