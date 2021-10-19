package main.com.allan.amca.listeners;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public interface InputListener extends DocumentListener {

    void update(DocumentEvent evt);

    @Override
    default void insertUpdate(DocumentEvent e) {
        update(e);
    }

    @Override
    default void removeUpdate(DocumentEvent e) {
        update(e);
    }

    @Override
    default void changedUpdate(DocumentEvent e) {
        update(e);
    }
}
