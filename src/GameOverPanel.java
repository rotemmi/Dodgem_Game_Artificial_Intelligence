import java.awt.Graphics;

import javax.swing.JPanel;

import pics.Img;


public class GameOverPanel extends JPanel
{
	public Img _win; //����� ������
	/**
	 * ����� ���� ������� ����� ������
	 * @param isblueWin
	 */
	public GameOverPanel(boolean isblueWin) 
	{
	
		if (isblueWin)
			_win = new Img("\\pics\\BlueWin.png", 0, 0, 578,637);
		else
			_win = new Img("\\pics\\RedWin.png", 0, 0, 578,637);
	}
	/**
	 * ����� ������� ���� �����
	 */
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		_win.drawImg(g);
	}
}
