import java.io.Serializable;

public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	private int index;
	private String name;
	private String email;

	public Member(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Member)) {
			return false;
		}

		Member other = (Member) obj;
		return name.equals(other.name) && email.equals(other.email);
	}

	public String toString() {
		String s = String.format("%s,%s", getName(), getEmail());
		return s;
	}
}
