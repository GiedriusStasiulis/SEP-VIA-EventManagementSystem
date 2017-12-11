/**
 *The Sponsor class represents an object, which holds two String values: name, email. 
 *Example:
 * @author Group#2 *
 */

public class Sponsor
{
   private static final long serialVersionUID = 1L;
   private int index;
   private String name;
   private String email;
   private String phone;

   public Sponsor(String name, String email, String phone) 
   {
      this.name = name;
      this.email = email;
      this.phone = phone;
   }
   
   public String getPhone() 
   {
      return phone;
   }
   
   public void setPhone(String phone)
   {
      this.phone = phone;
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

   public boolean equals(Object obj) 
   {
      if (!(obj instanceof Sponsor)) {
         return false;
      }

      Sponsor other = (Sponsor) obj;
      return name.equals(other.name) && email.equals(other.email) && phone.equals(other.phone);
   }

   public String toString() 
   {
      String s = String.format("%s,%s,%s", getName(), getEmail(), getPhone());
      return s;
   }
}