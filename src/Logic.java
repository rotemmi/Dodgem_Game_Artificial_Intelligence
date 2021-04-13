import java.util.LinkedList;

import javax.swing.text.StyledEditorKit.BoldAction;

/**
 * ����� ����� ������� ����� ������� �� ��� �� ��� �����
 */
public class Logic
{
	public enum STATUS {none,blue,red}; // ��� �����
	private STATUS [] [] _mat ; // ������ �����
	private final static Logic INSTANCE = new Logic(); // ����� ������
	private boolean _isBlueTurn = true; // ���
	private int _maxDepth = 7; // ����
	// Private constructor suppresses generation of a (public) default constructor
	private Logic() 
	{
		initMatLogic();
	}
	/**
	 * ����� ������
	 */
	protected void  initMatLogic()
	{
		_mat=new STATUS [3][3];
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if((j==0 &&i==0) || (j==0 &&i==1))
				{
					_mat[i][j] =STATUS.blue;	
				}
				else if((i==2 &&j==1) || (i==2 &&j==2) )
				{
					_mat[i][j] =STATUS.red;
				}
				else
					_mat[i][j] =STATUS.none;	
			}
		}
	}

	/**
	 * 
	 * ������ �� ���� ����� - �� �� �� ������ ����� ,�����, �� ��� ����
	 * 
	 * 
	 */
	public void setCell(int i,int j,STATUS s)
	{
		_mat[i][j]=s;
	}
	
	public void setDepth(int depth)
	{
		_maxDepth=depth;
	}

	/**
	 * �����  �� �� ��� ������ �� �� �� ������ ����� ,�����, �� ��� ����
	 */
	public STATUS getCell(int i,int j)
	{
		return _mat[i][j];
	}
	/**
	 * ����� �� ������ ���� ������
	 * 
	 */
	public STATUS[][] getMat()
	{
		return _mat;
	}
	/**
	 * ����� �� ���� ������
	 * @return
	 */
	public static Logic getInstance() 
	{
		return INSTANCE;
	}
	/**
	 * ����� �� ������� ������
	 */
	public void printMat()
	{
		for(int i=0;i<3;i++)
			for(int j=0; j<3;j++)
			{
				System.out.println("[ "+i+","+j+" ] = "+_mat[i][j]);
			}
	}
/*** 
 * �������� ����� ��� ������ ���� ������
 * @param isBlueTurn
 * @return
 */
	public boolean isWin(boolean isBlueTurn)
	{
		boolean isWin=true;
		STATUS s = STATUS.red;
		if (isBlueTurn)
			s = STATUS.blue;

		for(int i=0;i<3 && isWin;i++)
			for(int j=0; j<3 && isWin;j++)
			{
				if(_mat[i][j]==s)
				{
					isWin=false;
				}
			}

		return isWin;
	}
 /*** 
  * 
  * �������� ����� ��� ��� ����� 
  * ��� ������ ����� -9999
  * ����� ���� ��� ���� �������� �� ���� ��� �� �����
  * 
  * ��� ������ ����� 9999
  * @param isBlueTurn
  * @param depth
  * @return
  */
	public int calcPoints(boolean isBlueTurn, int depth)
	{
		boolean win = isWin(isBlueTurn);

		if(win && isBlueTurn) //���� ����
		{
			return -999999;
		}
		else if(win && !isBlueTurn) //���� ����
		{
			return 999999;
		}
		if(!win && !isBlueTurn) //����� ���� ����
		{
			int counter = 0;
			for(int i=0;i<3;i++)
				for(int j=0; j<3;j++)
				{
					if(_mat[i][j]==STATUS.red)
					{
						counter++;
					}
				}
			
			boolean isRedBlocks = isRedBlocks();		

			if (isRedBlocks && counter == 1)
			{
				return /*400000*/800000 * counter;
			}
			if (isRedBlocks)
				return /*400000*/500000 * counter;
			
			if (counter == 1 && depth == _maxDepth)
			{
				System.out.println("red out!");
				return 500000;
			}
			else if (counter == 0 && depth == _maxDepth)
			{
				System.out.println("red out!");
				return 500000;
			}

			
		}
		else if(!win && isBlueTurn)// ���� - ����� ���� ����
		{
			int counter = 0;
			for(int i=0;i<3;i++)
				for(int j=0; j<3;j++)
				{
					if(_mat[i][j]==STATUS.blue)
					{
						counter++;
					}
				}
			
			boolean isBlueBlocks = isBlueBlocks();		

			if (isBlueBlocks && counter == 1) // ����� ���� ����� ���� ���� ���
			{
				return -/*400000*/800000 * counter;
			}
			if (isBlueBlocks) // ���� �� �����
				return -/*400000*/500000 * counter;	
			
			if (counter == 1 && depth == _maxDepth)
			{
				System.out.println("blue out!");
				return -500000;
			}
			else if (counter == 0 && depth == _maxDepth)
			{
				System.out.println("blue out!");
				return -500000;
			}
		}

		return 0;
	}
/**
 * �������� ����� ��� ����� ���� ���� ������ ����
 * @param i
 * @param j
 * @param iNew
 * @param jNew
 * @param isBlueTurn
 * @return
 */

	public boolean checkLegalMove(int i, int j, int iNew, int jNew, boolean isBlueTurn)
	{
		if(isBlueTurn && _mat[i][j]==STATUS.blue/* || !isBlueTurn && _mat[i][j]==STATUS.red*/ )
		{
			if (i == iNew && j == jNew)
				return false;

			if(jNew==-1 && iNew==-1 &&j==2)
				return true;
			else if(jNew==-1 && iNew==-1)
				return false;

			if(_mat[iNew][jNew]==STATUS.none &&
					((jNew==j+1 && iNew==i) ||(jNew==j && iNew==i+1)||(jNew==j && iNew==i-1) ))
				return true;
			return false;
		}
		else if(!isBlueTurn && _mat[i][j]==STATUS.red)
		{
			if (i == iNew && j == jNew)
				return false;

			if(jNew==-1 && iNew==-1 &&i==0)
				return true;
			else if(jNew==-1 && iNew==-1)
				return false;

			if(_mat[iNew][jNew]==STATUS.none &&
					((iNew==i-1 && j==jNew) ||(iNew==i && j-1==jNew)||(iNew==i && j+1==jNew)))
				return true;
			return false;
		}
		return false;
	}
/**
 * ����� ���� ������� ���� ���� ����� ����� ������� ���� ���� �������
 * @param gradeslist
 * @param currTor
 * @return
 */
	public int findMinOrMax(LinkedList<Points> gradeslist, boolean currTor)
	{

		if(gradeslist.size()==0)
		{
			return 0;
		}
		int minormax = gradeslist.get(0).get_grade();
		if(currTor)
		{
			for(int i=1;i<gradeslist.size();i++)
			{
				if(minormax > gradeslist.get(i).get_grade())
				{
					minormax= gradeslist.get(i).get_grade();
				}
			}

		}
		else
		{
			for(int i=1;i<gradeslist.size();i++)
			{
				if(minormax < gradeslist.get(i).get_grade())
				{
					minormax= gradeslist.get(i).get_grade();
				}
			}
		}
		return minormax;
	}

/**
 * �������� ����� ���
 * @param depth
 * @param isBlueTurn
 * @return
 */
	public LinkedList<Points> buildList(int depth ,boolean isBlueTurn)

	{
		LinkedList<Points> grades = new LinkedList<Points>();

		for(int i=0;i<3;i++)
		{
			for(int j=0; j<3;j++)
			{
				for(int m=0;m<3;m++)
				{
					for(int n=0; n<3;n++)
					{
						if(checkLegalMove(i, j, m, n, isBlueTurn))
						{
							_mat[i][j]=STATUS.none;
							_mat[m][n]=(isBlueTurn)?STATUS.blue:STATUS.red;


							if(depth==0)
							{
								grades.add(new Points(i,j,m,n,calcPoints(isBlueTurn,depth)));
							}
							else
							{
								boolean win=false;
								if(isBlueTurn)
								{ 
									win=isWin(isBlueTurn);
									if(win)
										grades.add(new Points(i,j,m,n,-999999));
								}
								else
								{
									win=isWin(isBlueTurn);
									if(win)
										grades.add(new Points(i,j,m,n,999999));
								}

								if(!win)
								{
									LinkedList<Points> tmpGrades = buildList(depth-1, !isBlueTurn);
									grades.add(new Points(i,j,m,n,findMinOrMax(tmpGrades,isBlueTurn)+calcPoints(isBlueTurn,depth)));
								}
							}

							_mat[i][j]=(isBlueTurn)?STATUS.blue:STATUS.red;
							_mat[m][n]=STATUS.none;
						}
					}
				}

				int m = -1;
				int n = -1;

				if(checkLegalMove(i, j, m, n, isBlueTurn))
				{
					_mat[i][j]=STATUS.none;

					if(depth==0)
					{
						grades.add(new Points(i,j,m,n,calcPoints(isBlueTurn,depth)));
					}
					else
					{
						boolean win=false;
						if(isBlueTurn)
						{ 
							win=isWin(isBlueTurn);
							if(win)
								grades.add(new Points(i,j,m,n,-999999));
						}
						else
						{
							win=isWin(isBlueTurn);
							if(win)
								grades.add(new Points(i,j,m,n,999999));
						}

						if(!win)
						{
							LinkedList<Points> tmpGrades = buildList(depth-1, !isBlueTurn);
							grades.add(new Points(i,j,m,n,findMinOrMax(tmpGrades,isBlueTurn)+calcPoints(isBlueTurn,depth)));
						}
					}

					_mat[i][j]=(isBlueTurn)?STATUS.blue:STATUS.red;
				}
			}
		}
		return grades;
	}
/**
 *  ����� �� ����� ��� ��� ���� ����� �����
 * @param depth
 * @return
 */
	public Points calcBestMove()
	{
		LinkedList<Points> listPoints=buildList(_maxDepth,false);
		if (listPoints.size() ==  0)
		{
			return null;
		}

		Points p=listPoints.get(0);
		System.out.println("best move " + p._i + " " + p._j + " " + 
				p._iNew + " " + p._jNew + p.get_grade());
		for(int k=0; k<listPoints.size();k++)
		{
			System.out.println("best move " + listPoints.get(k)._i + " " + listPoints.get(k)._j + " " + 
					listPoints.get(k)._iNew + " " + listPoints.get(k)._jNew
					+ " " + listPoints.get(k).get_grade());
			if(listPoints.get(k).get_grade()>p.get_grade())
			{
				System.out.println("best move here " + listPoints.get(k)._i + " " + listPoints.get(k)._j + " " + 
						listPoints.get(k)._iNew + " " + listPoints.get(k)._jNew
						+ " " + listPoints.get(k).get_grade());
				p=listPoints.get(k);
			}
		}
		return p;
	}

/**
 * ����� ��� ����� ����� ���� ����� �����
 * @return
 */
	public boolean isRedBlocks()
	{
		boolean blocks = false;
		for(int i=0;i<3;i++)
			for(int j=0; j<3;j++)
			{
				if(_mat[i][j]==STATUS.red)
				{
					if (j-1>=0 && _mat[i][j-1] == STATUS.blue)
						blocks = true;
				}
			}
		return blocks;
	}

	
	/**
	 * ����� ��� ����� ����� ���� ����� �����
	 * @return
	 */
	public boolean isBlueBlocks()
	{
		boolean blocks = false;
		for(int i=0;i<3;i++)
			for(int j=0; j<3;j++)
			{
				if(_mat[i][j]==STATUS.blue)
				{
					if (i+1<=2 && _mat[i+1][j] == STATUS.red)
						blocks = true;
				}
			}
		return blocks;
	}

	
}



