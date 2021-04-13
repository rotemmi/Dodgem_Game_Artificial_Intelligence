import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import pics.Img;
/**
	מחלקה המגדירה תא בלוח
 */
public class TilePanel extends JPanel
{
	private int _row, _col; // שורה עמודה
	private Img _img = null; // תמונה - מה יש במשבצת
	private boolean _isBlue; // צבע
	private boolean _isCar; // האם מכונית או ריק
	protected LinkedList<CarClickedInterface> _listeners;

	/**
	 * אתחול ערכים
	 * @param row השורה
	 * @param col 
	 * העמודה
	 */
	public TilePanel(int row, int col,boolean isBlue,boolean isCar) 
	{
		// setBorder(BorderFactory.createLineBorder(Color.black,2));
		_row=row;
		_col=col;
		_isBlue=isBlue;
		_isCar=isCar;
		_listeners = new LinkedList<CarClickedInterface>();
		setOpaque(false);

		addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e) 
			{
				super.mousePressed(e);

				if (_isBlue || !_isCar)
				{
					for (int i=0; i<_listeners.size();i++)
						if(_isCar)
							_listeners.get(i).tileClickedWithCar(_row,_col,_isBlue);
						else
							_listeners.get(i).tileClickedWithoutCar(_row,_col);
				}
			}
		});

		if(_isCar && _isBlue==true)
		{
			_img =new Img("\\pics\\blueCar.png", 35, 40, 84,48);
		}
		else
		{
			if(_isCar && _isBlue==false)
			{
				_img =new Img("\\pics\\redCar.png", 35, 40, 48,84);
			}
		}
	}

	/**
	 * 
	 * מציבה מכונית אדומה וכחולה על הלוח
	 *
	 */
	public void putInPlace(boolean isBlue,boolean isCar)
	{
		System.out.println("row: " + _row +" " + "col " + _col + " "+
				isBlue + " " + isCar);
		_isBlue=isBlue;
		_isCar=isCar;

		if(_isCar && _isBlue==true)
		{
			_img =new Img("\\pics\\blueCar.png", 35, 40, 84,48);
		}
		else if(_isCar && _isBlue==false)
		{
			_img =new Img("\\pics\\redCar.png", 35, 40, 48,84);
		}
		else
		{
			System.out.println("THIS IS ELSE row: " + _row +" " + "col " + _col + " "+
					isBlue + " " + isCar);
			_img = null;
		}

		repaint();
	}
	/**
	 *   הוספת מאזין ל tile
	 * @param toAdd
	 */
	public void addListener(CarClickedInterface toAdd) 
	{
		_listeners.add(toAdd);
	}

	@Override
	/**
	 	ציור תמונה
	 */
	protected void paintComponent(Graphics p) 
	{
		super.paintComponent(p);
		if(_img!=null)
		{
			_img.drawImg(p);
		}
	}
}
