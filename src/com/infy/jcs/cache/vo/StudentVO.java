package com.infy.jcs.cache.vo;

public class StudentVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String studentName;
	private int studentAge;
	private String studentAddress;
	private int studentYear;

	/**
     * Constructor.
     */
	public StudentVO(final int id, final String studentName,
			final int studentAge, final String studentAddress,
			final int studentYear) {
		this.id = id;
		this.studentName = studentName;
		this.studentAge = studentAge;
		this.studentAddress = studentAddress;
		this.studentYear = studentYear;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return the studentAddress
	 */
	public String getStudentAddress() {
		return this.studentAddress;
	}

	/**
	 * @return the studentAge
	 */
	public int getStudentAge() {
		return this.studentAge;
	}

	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return this.studentName;
	}

	/**
	 * @return the studentYear
	 */
	public int getStudentYear() {
		return this.studentYear;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @param studentAddress
	 *            the studentAddress to set
	 */
	public void setStudentAddress(final String studentAddress) {
		this.studentAddress = studentAddress;
	}

	/**
	 * @param studentAge
	 *            the studentAge to set
	 */
	public void setStudentAge(final int studentAge) {
		this.studentAge = studentAge;
	}

	/**
	 * @param studentName
	 *            the studentName to set
	 */
	public void setStudentName(final String studentName) {
		this.studentName = studentName;
	}

	/**
	 * @param studentYear
	 *            the studentYear to set
	 */
	public void setStudentYear(final int studentYear) {
		this.studentYear = studentYear;
	}

}