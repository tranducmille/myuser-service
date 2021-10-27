package com.spire.services.security.impl;

import com.spire.generated.jaxrs.model.User;
import com.spire.services.security.util.SequenceGenerator;

import java.util.ArrayList;
import java.util.List;


public class UserManager {
	
	private static UserManager _INSTANCE = null;
	
	/**
	 * BOokManager singleton instance
	 * @return singleton object
	 */
	public static UserManager getInstance(){
		if(_INSTANCE == null){
			_INSTANCE = new UserManager();
		}		
		return _INSTANCE;
	}
	
	private List<User> users = new ArrayList<User>();
	
	/**
	 * initialize data
	 */
	public UserManager(){
		users.add(new User().id(SequenceGenerator.getInstance().getLongSequence()).accountName("James Butt").address("6649 N Blue Gum St").city("New Orleans").country("US"));
		users.add(new User().id(SequenceGenerator.getInstance().getLongSequence()).accountName("Josephine Darakjy").address("4 B Blue Ridge Blvd").city("Brighton").country("US"));
		users.add(new User().id(SequenceGenerator.getInstance().getLongSequence()).accountName("Art Darakjy").address("8 W Cerritos Ave #54").city("Bridgeport").country("US"));
		users.add(new User().id(SequenceGenerator.getInstance().getLongSequence()).accountName("Lenna Paprocki").address("639 Main St").city("Anchorage").country("US"));
		users.add(new User().id(SequenceGenerator.getInstance().getLongSequence()).accountName("Donette Foller").address("34 Center St").city("Hamilton").country("US"));
		users.add(new User().id(SequenceGenerator.getInstance().getLongSequence()).accountName("Simona Morasca").address("3 Mcauley Dr").city("Ashland").country("US"));
		users.add(new User().id(SequenceGenerator.getInstance().getLongSequence()).accountName("Mitsue Tollner").address("7 Eads St").city("Chicago").country("US"));
		users.add(new User().id(SequenceGenerator.getInstance().getLongSequence()).accountName("Leota Dilliard").address("7 W Jackson Blvd").city("San Jose").country("US"));
		users.add(new User().id(SequenceGenerator.getInstance().getLongSequence()).accountName("Sage Wieser").address("5 Boston Ave #88").city("Sioux Falls").country("US"));
		users.add(new User().id(SequenceGenerator.getInstance().getLongSequence()).accountName("Kris Marrier").address("228 Runamuck Pl #2808").city("Baltimore").country("US"));
		users.add(new User().id(SequenceGenerator.getInstance().getLongSequence()).accountName("Minna Amigon").address("2371 Jerrold Ave").city("Kulpsville").country("US"));
		users.add(new User().id(SequenceGenerator.getInstance().getLongSequence()).accountName("Abel Maclead").address("37275 St  Rt 17m M").city("Middle Island").country("US"));
		users.add(new User().id(SequenceGenerator.getInstance().getLongSequence()).accountName("Kiley Caldarera").address("25 E 75th St #69").city("Los Angeles").country("US"));
		users.add(new User().id(SequenceGenerator.getInstance().getLongSequence()).accountName("Graciela Ruta").address("98 Connecticut Ave Nw").city("Chagrin Falls").country("US"));
		users.add(new User().id(SequenceGenerator.getInstance().getLongSequence()).accountName("Cammy Albares").address("56 E Morehead St").city("Laredo").country("US"));
	}
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public User getUserById(int id){
		User rs = users.stream().filter(user -> user.getId() == id)
								.findFirst().orElse(null);
		return rs;
	}
	public void deleteUserById(int id){
		User user = getUserById(id);
		if(user != null){
			users.remove(user);
		}else{
			System.out.println("Can not find the deleted user id");
		}
	}
	
	public void addUser(User user){
		users.add(user);
	}
	
	public void updateUser(User newUser) {
		User oldUser = getUserById(newUser.getId().intValue());
		int index = users.indexOf(oldUser);
		users.set(index, newUser);
	}
}
	