public class Lecturer {
	private String name;
	private String category;
	private String phoneNumber;
	private String email;

	public Lecturer(String name, String category, String phoneNumber, String email) {
		this.name = name;
		this.category = category;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Lecturer)) {
			return false;
		}

		Lecturer other = (Lecturer) obj;
		return name.equals(other.name) && email.equals(other.email) && phoneNumber.equals(other.phoneNumber)
				&& category.equals(other.category);
	}

	public String toString() {
		String s = String.format("%s,%s,%s,%s", getName(), getCategory(), getPhoneNumber(), getEmail());
		return s;
	}
}