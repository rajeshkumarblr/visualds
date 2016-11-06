package org.dsl.application;

import org.dsl.bst.gui.BstDiag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public MainWindow(){
        prepareGUI();
    }

    public static void main(String[] args){
        MainWindow  swingMenuDemo = new MainWindow();
        swingMenuDemo.showMenuDemo();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Visual Data Structures & Algorithms");
        mainFrame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        mainFrame.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("",JLabel.CENTER );
        statusLabel = new JLabel("",JLabel.CENTER);

        statusLabel.setSize(350,100);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void showMenuDemo(){
        //create a menu bar
        final JMenuBar menuBar = new JMenuBar();

        //create menus
        JMenu treeMenu = new JMenu("BinaryTree");
        JMenu listMenu = new JMenu("LinkedList");
        final JMenu arrayMenu = new JMenu("Arrays");
        final JMenu problemsmenu = new JMenu("Problems");

        //create menu items
        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.setMnemonic(KeyEvent.VK_N);
        newMenuItem.setActionCommand("New");

        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.setActionCommand("Open");

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setActionCommand("Save");

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setActionCommand("Exit");

        MenuItemListener menuItemListener = new MenuItemListener();
        MenuDiagListener menuDiagListener = new MenuDiagListener(mainFrame);
        newMenuItem.addActionListener(menuDiagListener);
        openMenuItem.addActionListener(menuItemListener);
        saveMenuItem.addActionListener(menuItemListener);
        exitMenuItem.addActionListener(menuItemListener);

        //add menu items to menus
        treeMenu.add(newMenuItem);
        treeMenu.add(openMenuItem);
        treeMenu.add(saveMenuItem);
        treeMenu.add(exitMenuItem);

        //add menu to menubar
        menuBar.add(treeMenu);
        menuBar.add(listMenu);
        menuBar.add(arrayMenu);
        menuBar.add(problemsmenu);

        //add menubar to the frame
        mainFrame.setJMenuBar(menuBar);
        mainFrame.setVisible(true);
    }

    class MenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            statusLabel.setText(e.getActionCommand()
                    + " JMenuItem clicked.");
        }
    }
    class MenuDiagListener implements ActionListener {

        JFrame frame;

        MenuDiagListener(JFrame frame) {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            BstDiag dialog = new BstDiag();
            dialog.pack();
            dialog.setSize(800,600);
            dialog.setLocationRelativeTo(frame);
            dialog.setVisible(true);
        }
    }
}
