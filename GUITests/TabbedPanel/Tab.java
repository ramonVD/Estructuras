package GUIExperiments.TabbedPanel;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*Class that represents a tab on TabbedPanel. It is basically a JLabel 
 * that has a linked JPanel, and lets you click on it to change
 * the shown JPanel in TabbedPanel.
 */
@SuppressWarnings("serial")
public class Tab extends JLabel implements MouseListener{

	private static TabbedPanel parent;
	private JPanel linkedPanel;
	private static Tab currentActiveTab; //From all the tabs the one currently being shown
	
	public Tab(String tabText){
		this(tabText, null);
	}

	public Tab(String tabText, JPanel linkedPanel){
		super(tabText);
		this.addMouseListener(this);
        this.setBorder(BorderFactory.createMatteBorder(1,3,0,1,Color.BLACK));
        if (currentActiveTab == null) {currentActiveTab = this;} //if there are no shown tabs, the first one created will be the one active
        this.linkedPanel = linkedPanel;
	}
	
	public static void setParent(TabbedPanel newP){
		parent = newP;
	}
	
	public void displayMyPanel(){
		if (linkedPanel == null) { return; }
		if (linkedPanel != parent.getLinkedPanel()) {
			currentActiveTab = this;
			parent.displayActiveTabPanel(linkedPanel);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) { }

	@Override
	public void mouseEntered(MouseEvent arg0) {	}

	@Override
	public void mouseExited(MouseEvent arg0) {	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		displayMyPanel();
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) { }

	public JPanel getCurrentPanel(){
		return this.linkedPanel;
	}
	
	public void setCurrentPanel(JPanel newP){
		this.linkedPanel = newP;
	}

	public static Tab getCurrentActiveTab(){
		return currentActiveTab;
	}
	
}
