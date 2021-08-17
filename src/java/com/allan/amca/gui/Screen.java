package com.allan.amca.gui;

import javax.swing.*;
import java.awt.*;

public abstract class Screen extends JFrame implements Frameable {
//TODO: create abstract class and use template method to allow subclasses to override the steps only

    public JFrame                       frame;
    private JPanel                      cardPane;
    private JMenuBar                    menuBar;
    private JMenu                       fileMenu;
    private JMenu                       editMenu;
    public static final int             SEND_CLIENT_REQUEST = 1;
    public static final int             GET_CLIENT_REQUEST = 1;
    private CardLayout                  cardLayout;
    protected final ScreenResources     resource = new ScreenResources();
    //initializer to grab the values and loads the properties file. Loads once
    {
        resource.getValues();
        frame       = new JFrame("ATM - LOGIN");
        cardPane    = new JPanel();
        cardLayout  = new CardLayout();
        addMenuBar();
    }

    public Screen() {
    }
    // all screens will need to call createUI
    public final void createUI() {
        initComponents();
        updateUI();
        addListeners();
    }

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


