/**
 * 
 * ממשק ללחיצה עם מכונית ובלי מכונית
 *
 */
public interface CarClickedInterface
{
	public void tileClickedWithoutCar(int row,int col);
	public void tileClickedWithCar(int row,int col,boolean isblue);
}
