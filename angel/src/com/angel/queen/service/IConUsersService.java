package com.angel.queen.service;

import com.angel.queen.model.ConUsers;

public interface IConUsersService {

	public ConUsers login(ConUsers user);
	
	public ConUsers findUserByUserName(String userName);
	
	public int createUser(ConUsers user);
}
