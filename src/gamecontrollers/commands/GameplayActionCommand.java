// TODO developer [ Jorge ], test [ Sydney ]
package gamecontrollers.commands;

import gamecontrollers.save.CommandSaveVisitor;

public interface GameplayActionCommand {
    public void execute();
    public void undo();
    public void accept(CommandSaveVisitor visitor);
}
