import java.io.Serializable;

/** This class represents a lecturer.
 * 
 * @author alin
 * 
 * @param name
 *          the name
 * @param email
 *          the email
 * @param category
 *          the category
 * @param phoneNumber
 *           the phone number as an integer
 */
public class Lecturer implements Serializable
{
 /**
    * 
    */
   private static final long serialVersionUID = 1L;
private String name;
 private String email;
 private String category;
 private int phoneNumber;
 
public Lecturer()
{
   name="";
   email="";
   category="";
   phoneNumber=0;
}
public Lecturer(String name,String category ,int phoneNumber,String email)
{
   this.name=name;
   this.email=email;
   this.category=category;
   this.phoneNumber=phoneNumber;
}
public String getName()
{
   return name;
}
public void setName(String name)
{
   this.name = name;
}
public String getEmail()
{
   return email;
}
public void setEmail(String email)
{
   this.email = email;
}
public String getCategory()
{
   return category;
}
public void setCategory(String category)
{
   this.category = category;
}
public int getPhoneNumber()
{
   return phoneNumber;
}
public void setPhoneNumber(int phoneNumber)
{
   this.phoneNumber = phoneNumber;
}
@Override
public String toString()
{
   return  "Name: "+name.toUpperCase() +", PhoneNumber: "+phoneNumber+", Category: "+ category+", E-mail: "+email;
}



}
