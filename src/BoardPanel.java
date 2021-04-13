import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Timer;

import pics.Img;
/**
 * ���� �����
 */
public class BoardPanel extends JPanel implements CarClickedInterface
{
	public static  int _n=3;  // ���� ����
	private TilePanel [][] _tilePanels; //  ������ ������� ����� ���� ����� ������
	private int _row,_col; // ������� ���� ������
	private boolean _isblue,_isCar; // ������� ��� ������ ,���� ����� ������� �� ����� ���
	private Img _background; // ����� ����
	private boolean _isBlueTurn = true;  // ��� ����
	protected LinkedList<GameOver> _listeners;
	private Timer _timer = new Timer(1000, new ActionListener() { // ���� ���

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			((Timer)arg0.getSource()).stop();

			if (!_isBlueTurn)
				 compPlayersTurn();
		}

	});

	/**
	 	����� ������ ������ ���� ���� �������
	 */
	public BoardPanel() 
	{
		setLayout(null);
		_listeners = new LinkedList<GameOver>();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				tileClickedWithoutCar(-1, -1);
			}
		});

		_tilePanels=new TilePanel[_n][_n];


		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if((i==0 &&j==0) || (i==1 &&j==0))
				{
					//blue
					_tilePanels[i][j]=new TilePanel(i, j,true,true);	
				}
				else if((i==2 &&j==1) || (i==2 &&j==2) )
				{
					//red
					_tilePanels[i][j]=new TilePanel(i, j,false,true);
				}
				else
					_tilePanels[i][j]=new TilePanel(i, j,false,false);


			}
		}

		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				_tilePanels[j][i].setBounds(65+i*150, 82+j*150, 150, 150);
				add(_tilePanels[i][j]);
			}
		}

		for(int i=0;i<_n*_n;i++)
		{
			_tilePanels[i/_n][i%_n].addListener(this);
		}
		_background=new Img("\\pics\\board.png", 0, 0, 578,598);
	}
	/*** 
	 * �������� ���� ���� ���� ����� ���� ����� ����� ������ ����
	 */

	protected void compPlayersTurn() 
	{
		Points p = Logic.getInstance().calcBestMove();

		tileClickedWithCar(p.get_i(), p.get_j(), false);
		tileClickedWithoutCar(p.get_iNew(), p.get_jNew());
	}

	@Override
	/**
	 *   ������ ���� ��� ������
	 *   ��� ������ ���� ����� ������ 
	 * �� ����� ����� ��� ���� ����� ����� ����� �� ����
	 * �� ��, �� ���� ����
	 * 
	 */
	public void tileClickedWithoutCar(int row,int col)
	{
		if(_isCar)
		{
			if(_isblue)
			{
				//System.out.println("row: " + _row + "col: " + _col);
				if(((row != -1 && col != -1)) && ((col == _col+1 && row==_row) || (row==_row-1 && col==_col) || (row==_row+1 && col == _col))) // ������ ������� ������� �����
				{
				//	System.out.println("I'm here 1");
					Logic.getInstance().setCell(_row, _col, Logic.STATUS.none);
					Logic.getInstance().setCell(row, col, Logic.STATUS.blue);
					_tilePanels[_row][_col].putInPlace(_isblue,false);
					_tilePanels[row][col].putInPlace(_isblue,true);
					_row=row;
					_col=col;
					if(checkWin()==0)
					{
					_isBlueTurn = false;
					_timer.start();
					}
					else
						if(checkWin()==1)
							gameOver(true);
						else
							gameOver(false);
				}
				else if ((row == -1 && col == -1) && _col == 2)// row ����� 
				{
					//System.out.println("I'm here 2");
					Logic.getInstance().setCell(_row, _col, Logic.STATUS.none);
					_tilePanels[_row][_col].putInPlace(_isblue,false);
					if(checkWin()==0)
					{
					_isBlueTurn = false;
					_timer.start();
					}
					else
						if(checkWin()==1)
							gameOver(true);
						else
							gameOver(false);
				}
			}
			else
			{
				if(((row != -1 && col != -1)) && (col== _col-1 && row==_row) ||(col== _col+1 && row==_row) || (row==_row-1 && _col==col) ) // ������ ������� ������� �����
				{
					//System.out.println("wowowww");
					Logic.getInstance().setCell(_row, _col, Logic.STATUS.none);
					Logic.getInstance().setCell(row, col, Logic.STATUS.red);
					_tilePanels[_row][_col].putInPlace(_isblue,false);
					_tilePanels[row][col].putInPlace(_isblue,true);
					_row=row;
					_col=col;
					if(checkWin()==0)
					{
					_isBlueTurn = true;
					_timer.start();
					}
					else
						if(checkWin()==1)
							gameOver(true);
						else
							gameOver(false);
				}
				else if ((row == -1 && col == -1) && _row == 0)
				{
					Logic.getInstance().setCell(_row, _col, Logic.STATUS.none);
					_tilePanels[_row][_col].putInPlace(_isblue,false);
					if(checkWin()==0)
					{
					_isBlueTurn = true;
					_timer.start();
					}
					else
						if(checkWin()==1)
							gameOver(true);
						else
							gameOver(false);
				}
			}
			_isCar=false;
		}
		
	}
	public int checkWin()
	{

		int checkWinMode=0;
		int k=0,m=0;  
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			{
				if(Logic.getInstance().getMat()[i][j]==Logic.STATUS.blue)
					k++;
				else
					if(Logic.getInstance().getMat()[i][j]==Logic.STATUS.red)
						m++;
			}	
		if(k==0)
		{
			checkWinMode=1;
			//JOptionPane.showMessageDialog(null, "Blue");
			//gameOver(true);
		}
		else if(m==0)
		{
			checkWinMode=-1;
			//JOptionPane.showMessageDialog(null, "RED");
			//gameOver(false);
		}


		if(_isBlueTurn==false)
		{
			Logic.STATUS [][] mat = Logic.getInstance().getMat();
			boolean check=false;
			for(int i=0;i<3 && check==false;i++)
				for(int j=0;j<3 && check==false;j++)
				{
					if (mat[i][j] == Logic.STATUS.blue)
					{
						if (j==2)
							check = true;
						if (j+1<=2 && mat[i][j+1] == Logic.STATUS.none)
							check = true;
						if (i-1>=0 && mat[i-1][j] == Logic.STATUS.none)
							check = true;
						if (i+1<=2 && mat[i+1][j] == Logic.STATUS.none)
							check = true;
					}
				}
			if(check==false)
			{
				checkWinMode=-1;
				//JOptionPane.showMessageDialog(null, "RED");
				//gameOver(false);
			}
		}
		else if(_isBlueTurn==true)
		{		
			Logic.STATUS [] [] mat = Logic.getInstance().getMat();
			boolean check=false;
			for(int i=0;i<3 && check==false;i++)
				for(int j=0;j<3 && check==false;j++)
				{
					if (mat[i][j] == Logic.STATUS.red)
					{
						if (i==0)
							check = true;
						if (j+1<=2 && mat[i][j+1] == Logic.STATUS.none)
							check = true;
						if (i-1>=0 && mat[i-1][j] == Logic.STATUS.none)
							check = true;
						if (j-1>=0 && mat[i][j-1] == Logic.STATUS.none)
							check = true;
					}

				}
			if(check==false)
			{
				checkWinMode=1;
				//JOptionPane.showMessageDialog(null, "BLUE");
				//gameOver(true);
			}
			
		}
		return checkWinMode;	
		
	}
	

	/**
	 * 
	 * ������ ���� �� ������
	 */
	public void tileClickedWithCar(int row,int col,boolean isblue) 
	{
		_row=row;
		_col=col;
		_isblue=isblue;
		_isCar=true;
	}
	public void restart()
	{
		Logic.getInstance().initMatLogic();

		for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					if((i==0 &&j==0) || (i==1 &&j==0))
					{
						//blue
						_tilePanels[i][j].putInPlace(true,true);	
					}
					else if((i==2 &&j==1) || (i==2 &&j==2) )
					{
						//red
						_tilePanels[i][j].putInPlace(false,true);
					}
					else
						_tilePanels[i][j].putInPlace(false,false);
				}
			}
	}
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		_background.drawImg(g);
	}
	
	public void gameOver(boolean isBlueWin)
	{
		for (int i=0; i<_listeners.size();i++)
				_listeners.get(i).gameOver(isBlueWin);
	}
	
	public void addListener(GameOver toAdd) 
	{
		_listeners.add(toAdd);
	}
}
