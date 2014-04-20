package gamecontrollers;

public class Message {

    private String messageTemplate;
    private Object[] data;
    private boolean isError;

    public Message(String messageTemplate,Object[] data, boolean isError) {
        this.messageTemplate = messageTemplate;
        this.data = data;
        this.isError = isError;
    }

    public Message(String messageTemplate,boolean isError){
        this.messageTemplate = messageTemplate;
        this.isError = isError;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }

    public Object[] getData() {
        return data;
    }

    public boolean isError() {
        return isError;
    }

}
