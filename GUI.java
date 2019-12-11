import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUI {
    //variables declaration
    private JFrame mainFrame;
    
    private JPanel textPanel;
    private JPanel buttonPanel;
    
    private JPanel bottomPanel;
    private JButton store;
    private JButton clear;
    
    private Border raisedbevel = BorderFactory.createRaisedBevelBorder();

    public GUI() {
    	//setting the mainframe
    	mainFrame = new JFrame("Clipboard");
    	mainFrame.setSize(400,400);
    	mainFrame.setLayout(new BorderLayout());
    	
    	mainFrame.addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent windowEvent) {
    			System.exit(0);
    		}
    	});
    	
    	
    	//creating text panel for adding copied text items
    	textPanel = new JPanel();
    	textPanel.setLayout(new GridLayout(0, 1, 0, 5));
    	textPanel.setPreferredSize(new Dimension(320,400));
    	
    	//adding button panel for copying the text to the system clipboard for uses
    	buttonPanel = new JPanel();
    	buttonPanel.setLayout(new GridLayout(0, 1, 0, 5));
    	buttonPanel.setPreferredSize(new Dimension(80,400));
    	
    	//set borders for the panels
    	textPanel.setBorder(raisedbevel);
    	buttonPanel.setBorder(raisedbevel);
    	
    	mainFrame.add(textPanel, BorderLayout.CENTER);
    	mainFrame.add(buttonPanel, BorderLayout.LINE_END);
    	
    	//creating bottom panel to add the buttons
    	bottomPanel = new JPanel();
    	bottomPanel.setLayout(new FlowLayout());
    	
    	store = new JButton("Store");
    	bottomPanel.add(store);
    	
    	clear = new JButton("Clear");
    	bottomPanel.add(clear);
    	
    	//button function for clearing the GUI
    	clear.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			textPanel.removeAll();
    			buttonPanel.removeAll();
    			
    			textPanel.revalidate();
    			buttonPanel.revalidate();
    			textPanel.repaint();
    			buttonPanel.repaint();
    		}
    	});
    	
    	store.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			try {
					String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
					CopiedText text = new CopiedText(data);
					text.storeText(textPanel, buttonPanel);
					textPanel.revalidate();
					buttonPanel.revalidate();
					
					
				} catch (HeadlessException | UnsupportedFlavorException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		}
    	});
    	
    	mainFrame.add(bottomPanel, BorderLayout.PAGE_END);
    	mainFrame.setVisible(true);
    	
    }
    
    public static void main(String[] args) {
    	GUI main = new GUI();
    }
    
}
