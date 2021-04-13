import java.awt.Graphics;

import javax.swing.JPanel;

import pics.Img;


public class InstructionsPanel extends JPanel
{
	public Img _instructions; // תמונת הוראות
	// פעולה בונה של הוראות
	public InstructionsPanel() 
	{
			_instructions = new Img("\\pics\\instructionsPic.png", 0, 0, 1092,488);
	}
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		_instructions.drawImg(g);
	}
}
