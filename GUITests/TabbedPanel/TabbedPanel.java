package GUIExperiments.TabbedPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 *  Panel with a row of tabs on top of it and a panel under them, 
 *  if the user clicks on a tab itll change the shown Panel to the 
 *  one the tab is linked to. It doesnt reorder the tabs after
 *  clicking on them.
 */
@SuppressWarnings("serial")
public class TabbedPanel extends JPanel{
	
	private JPanel mutablePanel;
	private JPanel tabStrip;
	//The testing class InsidePanelTest is basically a simple JPanel with a fixed size and a background of the chosen color in the parameter, any JPanel subclass will work here
	private Tab[] panelTabs =  { new Tab("Menu principal", new InsidePanelTest(Color.RED) ), 
								new Tab("Cosas extras",new InsidePanelTest(Color.BLUE)),
								new Tab("Otros colores", new InsidePanelTest(Color.GREEN)),
								new Tab("Mas cosas", new InsidePanelTest(Color.CYAN))};	
	private static final boolean COMPRESSEDTABS = false;  //Change this in the future, use another type of layout instead of a 1/2 column gridLayout depending on the boolean
	
	public TabbedPanel(){
		initcomponents();
	}
	
	/*This function will create a panel with all the existent tabs, putting the chosen one
	 * in the main position and the rest adjacent. Could change the tab order here f.example */
	private JPanel initTabPanel(Tab... tabs){
		int tabAmount = tabs.length-1;
		Tab principalTab = Tab.getCurrentActiveTab();

		JPanel tabPanel = new JPanel();
		JPanel unActiveTabPanel = new JPanel();
		
		tabPanel.setLayout(new BorderLayout(3,3));
		unActiveTabPanel.setLayout(new GridLayout(0,tabAmount,3,3));

		tabPanel.add(principalTab, BorderLayout.CENTER);
		for (Tab t : tabs) {
			if (Tab.getCurrentActiveTab() == t) { continue; }
			unActiveTabPanel.add(t);
		}	
		tabPanel.add(unActiveTabPanel, BorderLayout.EAST);
		
		JPanel positioningPanel = new JPanel(); 
		positioningPanel.setLayout(new GridLayout(0, (COMPRESSEDTABS) ? 2:1));

		positioningPanel.add(tabPanel);

		return positioningPanel;
	}
	
	private void initcomponents(){
		this.setLayout(new BorderLayout());
		Tab.setParent(this);

		tabStrip = initTabPanel(panelTabs);

		this.add(tabStrip, BorderLayout.NORTH);
		displayActiveTabPanel(Tab.getCurrentActiveTab().getCurrentPanel());
	}
	
	/*Displays the panel that the current chosen tab is linked to */
	public void displayActiveTabPanel(JPanel newPanel){
		this.remove(tabStrip);
		this.tabStrip = initTabPanel(panelTabs);
		this.add(tabStrip, BorderLayout.NORTH);
		
		if (mutablePanel != null) {
			this.remove(mutablePanel);
		}
		
		if (newPanel != null) {
			this.mutablePanel = newPanel; 
		} else { 
			this.mutablePanel = new JPanel(); //show a dummy panel if the one that should be shown is null
		} 
		
        this.mutablePanel.setBorder(BorderFactory.createMatteBorder(2,0,0,0,Color.BLACK)); //Change here the border and other stuff of the shown JPanel
		this.add(mutablePanel,BorderLayout.CENTER);
		this.validate();
		this.repaint();
	}
	
	public JPanel getLinkedPanel(){
		return mutablePanel;
	}
	
	public Tab[] getTabList(){
		return this.panelTabs;
	}
	
	public void setTabList(Tab[] newList) {
		this.panelTabs = newList;
	}
	
	/*simple testing...*/
	public static void main(String[] args){
		JFrame window = new JFrame("Tab testing");
		TabbedPanel content = new TabbedPanel();
		window.setContentPane(content);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocation(50, 25);
		window.pack();
	}
}
