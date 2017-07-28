package GUIExperiments.TabbedPanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/*Panel que se cargara con la tab...*/
@SuppressWarnings("serial")
public class InsidePanelTest extends JPanel{
	
	public InsidePanelTest(Color bkgColor){
		super();
		this.setBackground(bkgColor);
		this.setPreferredSize(new Dimension(500,500));
	}

}
