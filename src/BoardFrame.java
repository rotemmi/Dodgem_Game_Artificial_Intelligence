import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import pics.Img;

/**
 * חלון המשחק
 * @author user
 *
 */
public class BoardFrame extends JFrame implements GameOver //, initPanel
{
	/**
	 	הוספת המטריצה למשטח
	 */
	private BoardPanel _p;
	private GameOverPanel _gameOver;
	public BoardFrame() 
	{
		super("Dodgem");
		_p=new BoardPanel();
		add(_p);
		_p.addListener(this);
		JMenuBar menubar = new JMenuBar();

		JMenu file = new JMenu("File");
		JMenuItem eMenuItem1 = new JMenuItem("Instructions");
		JMenuItem eMenuItem2 = new JMenuItem("Restart");
		JMenuItem eMenuItem3 = new JMenuItem("Easy");
		JMenuItem eMenuItem4 = new JMenuItem("Medium");
		JMenuItem eMenuItem5 = new JMenuItem("Hard");
	
		file.add(eMenuItem1);
		file.add(eMenuItem2);
		file.add(eMenuItem3);
		file.add(eMenuItem4);
		file.add(eMenuItem5);
		menubar.add(file);
		eMenuItem1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent g) 
			{
				JFrame _jf = new JFrame();
				InstructionsPanel _i = new InstructionsPanel();
				_jf.add(_i);
				_jf.setSize(1092,488);
				_jf.setLocation(578, 0);
				_jf.setVisible(true);
			}
		});
		
		eMenuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				{
					remove(_p);
					if (_gameOver != null)
						remove(_gameOver);
					add(_p);
					_p.restart();
					setSize(578,637);
					repaint();
				}
				
			}
		});
	
		eMenuItem3.setEnabled(false);
		eMenuItem3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				{
					Logic.getInstance().setDepth(7);
					eMenuItem3.setEnabled(false);
					eMenuItem4.setEnabled(true);
					eMenuItem5.setEnabled(true);
					
					remove(_p);
					if (_gameOver != null)
						remove(_gameOver);
					add(_p);
					_p.restart();
					setSize(578,637);
					repaint();
				}
				
			}
		});
		eMenuItem4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				{
					Logic.getInstance().setDepth(9);
					eMenuItem3.setEnabled(true);
					eMenuItem4.setEnabled(false);
					eMenuItem5.setEnabled(true);
					
					remove(_p);
					if (_gameOver != null)
						remove(_gameOver);
					add(_p);
					_p.restart();
					setSize(578,637);
					repaint();
				}
				
			}
		});
		eMenuItem5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				{
					Logic.getInstance().setDepth(11);
					eMenuItem3.setEnabled(true);
					eMenuItem4.setEnabled(true);
					eMenuItem5.setEnabled(false);
					
					remove(_p);
					if (_gameOver != null)
						remove(_gameOver);
					add(_p);
					_p.restart();
					setSize(578,637);
					repaint();
				}
				
			}
		});
		setJMenuBar(menubar);
		setTitle("Dodgem");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(578,637);
		setVisible(true);

	}
	
	/**
	 * 
	 * יצירת המשחק
	 */
	public static void main(String [] args)
	{
		BoardFrame boardFrame=new BoardFrame();
	}

	@Override
	public void gameOver(boolean isBlueWin) {
		this.remove(_p);
		_gameOver = new GameOverPanel(isBlueWin);
		add(_gameOver);
		setTitle("Dodgem");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(578,637);
		setVisible(true);
		_gameOver.repaint();
		repaint();
	}
	
	//public initPanel
	// remove p, add _p, _p.restart()
}
