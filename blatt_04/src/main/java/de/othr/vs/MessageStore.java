package de.othr.vs;

import java.util.ArrayList;
import java.util.HashMap;

class MessageStore {
    private HashMap<String, ArrayList<String>> messages;

    MessageStore() {
        this.messages = new HashMap<>();
    }

    public synchronized boolean addUser(String user) {
        if (this.messages.containsKey(user))
            return false;

        this.messages.put(user, new ArrayList<String>());
        return true;
    }

    public synchronized boolean putMessage(String sender, String receiver, String msg) {
        if (!this.messages.containsKey(sender) || !messages.containsKey(receiver))
            return false;

        this.messages.get(receiver).add(sender + ": " + msg);
        return true;
    }

    public synchronized String[] getMessages(String user) {
        if (!this.messages.containsKey(user))
            return null;

        String[] ret =  this.messages.get(user).toArray(new String[0]);
        this.messages.get(user).clear();
        return ret;
    }
}
