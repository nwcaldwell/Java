package models.board;

//TODO [Jorge][Kevin]

public interface  TileVisitor {
    public void visit(Village v);
    public void visit(Rice r);
    public void visit(Palace p);
    public void visit(Irrigation i);
    public void visit(TileComponent tcc);
}
