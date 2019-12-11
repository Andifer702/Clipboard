import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CopiedText {
	private String s;
	private JButton copyButton;
	private JPanel storedTextPanel;
	private JPanel button;
	private JTextArea storedText;
	private JScrollPane scrollPane;
	
	private JLabel testLabel= new JLabel("Hello World");
	
	
	public CopiedText(String text) {
		s = text;
		storedText = new JTextArea(1, 25);
		scrollPane = new JScrollPane(storedText);
		storedText.setEditable(false);
		copyButton = new JButton("Copy");
		copyButton.setMargin(new Insets(10, 0, 0, 0));
	}

	public void storeText(JPanel panel1, JPanel panel2) {
		storedTextPanel = panel1;
		panel1.add(scrollPane);
		storedText.setText(s);
		
		button = panel2;
		panel2.add(copyButton);
		
		copyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = storedText.getText();
				StringSelection stringSelection = new StringSelection(text);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection,null);
			}
		});
	}
	
	
	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}
	
	
}
