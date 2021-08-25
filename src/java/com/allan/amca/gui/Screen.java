package com.allan.amca.gui;

import com.allan.amca.gui.login.LoginResources;

import javax.swing.*;

/**
 * Screen base class
 * @author allanaranzaso
 */
public abstract class Screen extends JFrame implements Frameable {

    public JFrame                       frame;
    public static final int             GET_CLIENT_REQUEST = 1;
    protected final LoginResources     resource = new LoginResources();
    //initializer to grab the values and loads the properties file.
    {
        resource.getPropertyValues();
        frame       = new JFrame(resource.LOGIN_FRAME_TXT());
        addMenuBar();
    }

    public Screen() {
    }

    /**
     * Create the UI for the appropriate screen.
     * Interface methods initComponents() and addListeners() will create the appropriate components
     * and listeners based on the subclass.
     * Abstract method updateUI() will be overridden in each subclass to determine the type of UI to build
     */
    public final void createUI() {
        initComponents();
        updateUI();
        addListeners();
    }

    /**
     * Updates the application's UI with the appropriate screen depending on where the user is
     */
    protected abstract void updateUI();

    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        frame.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
    }
}


