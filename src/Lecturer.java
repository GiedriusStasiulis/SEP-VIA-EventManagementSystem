public class Lecturer {
	private String lecturerName;
	private String lecturerCategory;
	private String lecturerEmail;
	private String lecturerPhoneNumber;

	public Lecturer(String lecturerName, String lecturerCategory, String lecturerEmail, String lecturerPhoneNumber) 
	{
		this.lecturerName = lecturerName;
		this.lecturerCategory = lecturerCategory;
		this.lecturerEmail = lecturerEmail;
		this.lecturerPhoneNumber = lecturerPhoneNumber;
	}

	public String getLecturerName() 
	{
		return lecturerName;
	}

	public void setLecturerName(String lecturerName) 
	{
		this.lecturerName = lecturerName;
	}

	public String getLecturerCategory() 
	{
		return lecturerCategory;
	}

	public void setLecturerCategory(String lecturerCategory) 
	{
		this.lecturerCategory = lecturerCategory;
	}

	public String getLecturerEmail() 
	{
		return lecturerEmail;
	}

	public void setLecturerEmail(String lecturerEmail) 
	{
		this.lecturerEmail = lecturerEmail;
	}

	public String getLecturerPhoneNumber() 
	{
		return lecturerPhoneNumber;
	}

	public void setLecturerPhoneNumber(String lecturerPhoneNumber) 
	{
		this.lecturerPhoneNumber = lecturerPhoneNumber;
	}

	public boolean equals(Object obj) 
	{
		if (!(obj instanceof Lecturer)) 
		{
			return false;
		}

		Lecturer other = (Lecturer) obj;
		return lecturerName.equals(other.lecturerName) && lecturerEmail.equals(other.lecturerEmail) && lecturerPhoneNumber.equals(other.lecturerPhoneNumber)
				&& lecturerCategory.equals(other.lecturerCategory);
	}

	public String toString() 
	{
		String s = String.format("%s,%s,%s,%s", getLecturerName(), getLecturerCategory(), getLecturerEmail(), getLecturerPhoneNumber());
		return s;
	}
}