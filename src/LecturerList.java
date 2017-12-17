import java.util.ArrayList;

public class LecturerList 
{
	private ArrayList<Lecturer> lecturerList;
	
	public LecturerList()
	{
		lecturerList = new ArrayList<Lecturer>();
	}
	
	public int size()
	{
		return lecturerList.size();
	}
	
	public void addLecturerToList(Lecturer lecturer)
	{
		lecturerList.add(lecturer);
	}
	
	public void replaceLecturerInList(int index, Lecturer lecturer)
	{
		lecturerList.set(index, lecturer);
	}
	
	public Lecturer getLecturer(int index)
	{
		return lecturerList.get(index);
	}
	
	public int getLecturerIndex(Lecturer lecturer)
	{
		return lecturerList.indexOf(lecturer);
	}
	
	public void deleteLecturerFromList(int index)
	{
		lecturerList.remove(index);
	}
	
	public void clearLecturerList()
	{
		lecturerList.clear();
	}
	
	public String toString()
	{
		String s = String.format("%s", lecturerList);
		return s;
	}
}