package com.allan.amca.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Screen base class
 * @author allanaranzaso
 */
public abstract class Screen extends JFrame implements Frameable {

    public JFrame                       frame;
    private JPanel                      cardPane;
    private JMenuBar                    menuBar;
    private JMenu                       fileMenu;
    private JMenu                       editMenu;
    public static final int             SEND_CLIENT_REQUEST = 1;
    public static final int             GET_CLIENT_REQUEST = 1;
    private CardLayout                  cardLayout;
    protected final ScreenResources     resource = new ScreenResources();
    //initializer to grab the values and loads the properties file.
    {
        resource.getValues();
        frame       = new JFrame("ATM - Login");
        cardPane    = new JPanel();
        cardLayout  = new CardLayout();
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

    public JPanel getCardPane() {
        return cardPane;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    private void addMenuBar() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");

        frame.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
    }
}


