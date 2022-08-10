package com.bbenslimane.app.ws.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.bbenslimane.app.ws.responses.ErrorMessages;
import com.bbenslimane.app.ws.responses.UserResponse;
import com.bbenslimane.app.ws.services.UserService;
import com.bbenslimane.app.ws.services.imp.UserServiceImp;
import com.bbenslimane.app.ws.shard.dto.UserDto;
import com.bbenslimane.app.ws.exception.UserException;
import com.bbenslimane.app.ws.requests.UserRequest;



@CrossOrigin(origins = "*")  //apply corss for all functions in this controller
@RestController
@RequestMapping("/users")  //localhost:8080/users

public class UserController {
	
	
	
	@Autowired
	UserService userService;
	 
	
	
	@GetMapping(path="/{id}", produces={MediaType.APPLICATION_JSON_VALUE,  MediaType.APPLICATION_XML_VALUE}) // produces=MediaType.APPLICATION_XML_VALUE to receive XML response 
																		//consumes = MediaType.APPLICATION_XML_VALUE to send XML content via request
																		//produces={MediaType.APPLICATION_JSON_VALUE,  MediaType.APPLICATION_XML_VALUE} to receive XML or JSON response 
	public ResponseEntity<UserResponse>   getUser(@PathVariable String id) {
		
		UserDto userDto = userService.getUserByUserId(id);
		System.out.println("userDto");
		System.out.println(userDto);
//		UserResponse userResponse = new UserResponse();
//		BeanUtils.copyProperties(userDto, userResponse);

		ModelMapper modelMapper = new ModelMapper();
		UserResponse userResponse = modelMapper.map(userDto, UserResponse.class);


		return new ResponseEntity<>(userResponse, HttpStatus.OK) ;
		
		
	}



//	@CrossOrigin(origins = {"http://localhost:4200", "http://mydomaim.com"})  // apply cross only for this fucntion
//	@CrossOrigin(origins = "*")
	@GetMapping(produces={MediaType.APPLICATION_JSON_VALUE,  MediaType.APPLICATION_XML_VALUE})
	public List<UserResponse> getUsers(@RequestParam(value="page", defaultValue="1") int page ,
									   @RequestParam(value="limit", defaultValue="15") int limit,
									   @RequestParam(value="search", defaultValue="") String search,
									   @RequestParam(value="status", defaultValue="1") int status
									   ){
		
		List<UserResponse> usersResponse = new ArrayList<UserResponse>();


		List<UserDto> users = userService.getUsers(page, limit, search, status);


		ModelMapper modelMapper = new ModelMapper();
		for(UserDto userDto : users) {

//			UserResponse user = new UserResponse();
//			BeanUtils.copyProperties(userDto, user);

			UserResponse user = modelMapper.map(userDto, UserResponse.class);
			
			usersResponse.add(user);			
		}
		
		return usersResponse;
		
		
		
	}
	
	
	//@PostMapping("/users")
	@PostMapping(
			produces={MediaType.APPLICATION_JSON_VALUE,  MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE,  MediaType.APPLICATION_XML_VALUE}
			)
	public ResponseEntity<UserResponse>  createUser(@RequestBody @Validated UserRequest userRequest) throws Exception {
		
		if(userRequest.getFirstName().isEmpty()) throw new UserException(ErrorMessages.Missing_REQUIRED_FIELD.getErrorMessage());
		
//		UserDto userDto = new UserDto();
//		BeanUtils.copyProperties(userRequest, userDto);

		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);

		
		UserDto  createUser = userService.createUser(userDto);
		
//		UserResponse userResponse = new UserResponse();
//		BeanUtils.copyProperties(createUser, userResponse);
		
		UserResponse userResponse = modelMapper.map(createUser, UserResponse.class);
		
		return  new ResponseEntity<>(userResponse, HttpStatus.CREATED) ;

	}

	
	@PutMapping(
				path="/{id}",
				produces={MediaType.APPLICATION_JSON_VALUE,  MediaType.APPLICATION_XML_VALUE},
				consumes = {MediaType.APPLICATION_JSON_VALUE,  MediaType.APPLICATION_XML_VALUE}
				)
	public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest, @PathVariable String id) {

		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(userRequest, userDto);
		
		UserDto  updateUser = userService.updateUser(id, userDto);

		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(updateUser, userResponse);
		
		return new ResponseEntity<>(userResponse, HttpStatus.ACCEPTED) ;
	}

	@DeleteMapping(path="/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {

		userService.deleteUser(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	
	}


}
