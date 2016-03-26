package user;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadUserTest 
{
	public static void main(String args[]) 
	{
		try {
			User u = User.loadUser();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
