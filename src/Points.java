
public class Points {

	protected int _i; // שורה
	protected int _j; // עמודה
	protected int _iNew; // שורה חדשה
	protected int _jNew; // עמודה חדשה
	protected int _grade; // ניקוד
	/**
	 * פעולה בונה המאתחלת מהלך וניקוד
	 * @param i
	 * @param j
	 * @param iNew
	 * @param jNew
	 * @param grade
	 */
	public Points(int i, int j, int iNew,int jNew, int grade)
	{
		this._i=i;
		this._j=j;
		this._iNew=iNew;
		this._jNew=jNew;
		this._grade=grade;
	}
/**
 *  פעולה המחזירה שורה
 * @return
 */
	public int get_i() {
		return _i;
	}
	/**
	 *  פעולה המשנה שורה
	 * @return
	 */
	public void set_i(int _i) {
		this._i = _i;
	}
	/**
	 *  פעולה המחזירה עמודה
	 * @return
	 */
	public int get_j() {
		return _j;
	}
	/**
	 *  פעולה המשנה עמודה
	 * @return
	 */
	public void set_j(int _j) {
		this._j = _j;
	}
	/**
	 *  פעולה המחזירה שורה חדשה
	 * @return
	 */
	public int get_iNew() {
		return _iNew;
	}
	/**
	 *  פעולה הקובעת שורה חדשה
	 * @return
	 */
	public void set_iNew(int _iNew) {
		this._iNew = _iNew;
	}
	/**
	 *  פעולה המחזירה עמודה חדשה
	 * @return
	 */
	public int get_jNew() {
		return _jNew;
	}
	/**
	 *  פעולה הקובעת עמודה חדשה
	 * @return
	 */
	public void set_jNew(int _jNew) {
		this._jNew = _jNew;
	}
	/**
	 *  פעולה המחזירה ניקוד מהלך
	 * @return
	 */
	public int get_grade() {
		return _grade;
	}
	/**
	 *  פעולה המגדירה ניקוד מהלך
	 * @return
	 */
	public void set_grade(int _grade) {
		this._grade = _grade;
	}

}
