
public class Points {

	protected int _i; // ����
	protected int _j; // �����
	protected int _iNew; // ���� ����
	protected int _jNew; // ����� ����
	protected int _grade; // �����
	/**
	 * ����� ���� ������� ���� ������
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
 *  ����� ������� ����
 * @return
 */
	public int get_i() {
		return _i;
	}
	/**
	 *  ����� ����� ����
	 * @return
	 */
	public void set_i(int _i) {
		this._i = _i;
	}
	/**
	 *  ����� ������� �����
	 * @return
	 */
	public int get_j() {
		return _j;
	}
	/**
	 *  ����� ����� �����
	 * @return
	 */
	public void set_j(int _j) {
		this._j = _j;
	}
	/**
	 *  ����� ������� ���� ����
	 * @return
	 */
	public int get_iNew() {
		return _iNew;
	}
	/**
	 *  ����� ������ ���� ����
	 * @return
	 */
	public void set_iNew(int _iNew) {
		this._iNew = _iNew;
	}
	/**
	 *  ����� ������� ����� ����
	 * @return
	 */
	public int get_jNew() {
		return _jNew;
	}
	/**
	 *  ����� ������ ����� ����
	 * @return
	 */
	public void set_jNew(int _jNew) {
		this._jNew = _jNew;
	}
	/**
	 *  ����� ������� ����� ����
	 * @return
	 */
	public int get_grade() {
		return _grade;
	}
	/**
	 *  ����� ������� ����� ����
	 * @return
	 */
	public void set_grade(int _grade) {
		this._grade = _grade;
	}

}
