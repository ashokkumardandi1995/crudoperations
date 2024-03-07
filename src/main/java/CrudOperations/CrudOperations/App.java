package CrudOperations.CrudOperations;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;

import com.crud.dao.UserDao;
import com.crud.dao.UserDaoImpl;
import com.crud.model.User;

public class App 
{
    public static void main( String[] args )
    {
    	UserDao dao = new UserDaoImpl();
    	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
        	try {
                System.out.println("==================MENU=================");
                System.out.println("1. Create a new user");
                System.out.println("2. See a user");
                System.out.println("3. See all the users");
                System.out.println("4. Update a user information");
                System.out.println("5. Delete a user");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = Integer.valueOf(input.readLine());

                switch(choice) {
        	        case 1:
        	        {
        	        	System.out.print("Enter the firstname: ");
        	        	String firstname = input.readLine().trim();
        	        	System.out.print("Enter the lastname: ");
        	        	String lastname = input.readLine().trim();
        	        	System.out.println();
        	        	System.out.println("Dob format will be yyyy-mm-dd");
        	        	System.out.print("Enter the dob: ");
        	        	String date = input.readLine().trim();
        	        	User user = new User(firstname, lastname, Date.valueOf(date));
        	        	System.out.println("\nAdding the user.........");
        	        	dao.saveUser(user);
        	        	System.out.println("User added successfully!");
        	        	break;
        	        }
        	        case 2:
        	        {
        	        	System.out.print("Enter the userId: ");
        	        	long id = Long.valueOf(input.readLine());
        	        	System.out.println(dao.getUserById(id));
        	        	break;
        	        }
        	        case 3:
        	        {
        	        	dao.getAllUsers().forEach(u -> System.out.println(u));
        	        	break;
        	        }
        	        case 4:
        	        {
        	        	System.out.println("Enter the userId: ");
        	        	long id = Long.valueOf(input.readLine());
        	        	User user = dao.getUserById(id);
        	        	if(user == null) {
        	        		System.out.println("Sorry! The user does not exit.");
        	        		break;
        	        	}
        	        	System.out.println("Leave blank if don't want to change.");
        	        	System.out.print("Enter the firstname: ");
        	        	String firstname = input.readLine().trim();
        	        	if(firstname != "")
        	        		user.setFirstname(firstname);
        	        	System.out.print("Enter the lastname: ");
        	        	String lastname = input.readLine().trim();
        	        	if(lastname != "")
        	        		user.setLastname(lastname);
        	        	System.out.println();
        	        	System.out.println("Dob format will be yyyy-mm-dd");
        	        	System.out.print("Enter the dob: ");
        	        	String date = input.readLine().trim();
        	        	if(date != "")
        	        		user.setDob(Date.valueOf(date));
        	        	System.out.println("\nUpdating the user.........");
        	        	dao.updateUser(user);
        	        	System.out.println("User updated successfully!");
        	        	break;
        	        }
        	        case 5:
        	        {
        	        	System.out.println("Enter the userId: ");
        	        	long id = Long.valueOf(input.readLine());
        	        	System.out.println("Deleting the user.......");
        	        	dao.deleteUserById(id);
        	        	System.out.println("User deleted successfully!");
        	        	break;
        	        }
        	        case 6:
        	        	System.exit(0);
                }
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
    }
}
