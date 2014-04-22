package gamecontrollers;

import java.util.ArrayList;
import java.util.List;

/**
    //TODO [Jorge][Kevin]
 */
public class Response {


    private List<Message> messages;
    private boolean hasErrors;

    public Response(){
        hasErrors = false;
        messages = new ArrayList<Message>();
    }

    public Response(List<Message> messages){
        hasErrors = true;
        this.messages = new ArrayList<Message>();
        addMessages(messages);
    }

    public Response(final Message message){
        messages = new ArrayList<Message>();
        addMessage(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public boolean hasErrors(){
        return hasErrors;
    }

    public void addMessages(List<Message> messages){
        for(Message message: messages) {
            addMessage(message);
        }
    }



    public void addMessage(Message message){
        if(!hasErrors) {
            hasErrors = message.isError();
            messages.add( message );
        } else if (message.isError()) {
            messages.add( message );
        }
    }
}
