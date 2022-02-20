package com.developermind.phonebook.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.developermind.phonebook.annotations.group.NotBlankGroup;
import com.developermind.phonebook.annotations.group.NotEmptyGroup;
import com.developermind.phonebook.annotations.group.NotNullGroup;
import com.developermind.phonebook.annotations.group.SizeGroup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhonebookForm {

	@NotNull(message = "Address cannot be null.", groups = NotNullGroup.class)
	@NotEmpty(message = "Address cannot be empty.", groups = NotEmptyGroup.class)
	@NotBlank(message = "Address cannot be blank.", groups = NotBlankGroup.class)
	private final String address;

	@NotNull(message = "Email cannot be null.", groups = NotNullGroup.class)
	@NotEmpty(message = "Email cannot be empty.", groups = NotEmptyGroup.class)
	@NotBlank(message = "Email cannot be blank.", groups = NotBlankGroup.class)
	private final String email;

	@NotNull(message = "First name cannot be null.", groups = NotNullGroup.class)
	@NotEmpty(message = "First name cannot be empty.", groups = NotEmptyGroup.class)
	@NotBlank(message = "First name cannot be blank.", groups = NotBlankGroup.class)
	private final String firstName;

	@NotNull(message = "Last name cannot be null.", groups = NotNullGroup.class)
	@NotEmpty(message = "Last name cannot be empty.", groups = NotEmptyGroup.class)
	@NotBlank(message = "Last name cannot be blank.", groups = NotBlankGroup.class)
	private final String lastName;

	@NotNull(message = "Mobile number cannot be null.", groups = NotNullGroup.class)
	@NotEmpty(message = "Mobile number cannot be empty.", groups = NotEmptyGroup.class)
	@NotBlank(message = "Mobile number cannot be blank.", groups = NotBlankGroup.class)
	@Length(max = 10, min = 10, message = "Mobile number cannot be lass then and more then 10 digits.", groups = SizeGroup.class)
	private final String mobile;

	@NotNull(message = "Nick name cannot be null.", groups = NotNullGroup.class)
	@NotEmpty(message = "Nick name cannot be empty.", groups = NotEmptyGroup.class)
	@NotBlank(message = "Nick name cannot be blank.", groups = NotBlankGroup.class)
	private final String nickName;

	@NotNull(message = "Photo cannot be null.", groups = NotNullGroup.class)
	@NotEmpty(message = "Photo cannot be empty.", groups = NotEmptyGroup.class)
	@NotBlank(message = "Photo cannot be blank.", groups = NotBlankGroup.class)
	private final String photo;

	@NotNull(message = "Relation cannot be null.", groups = NotNullGroup.class)
	@NotEmpty(message = "Relation cannot be empty.", groups = NotEmptyGroup.class)
	@NotBlank(message = "Relation cannot be blank.", groups = NotBlankGroup.class)
	private final String relation;
	
	public PhonebookForm(
			@NotNull(message = "Address cannot be null.", groups = NotNullGroup.class) @NotEmpty(message = "Address cannot be empty.", groups = NotEmptyGroup.class) @NotBlank(message = "Address cannot be blank.", groups = NotBlankGroup.class) String address,
			@NotNull(message = "Email cannot be null.", groups = NotNullGroup.class) @NotEmpty(message = "Email cannot be empty.", groups = NotEmptyGroup.class) @NotBlank(message = "Email cannot be blank.", groups = NotBlankGroup.class) String email,
			@NotNull(message = "First name cannot be null.", groups = NotNullGroup.class) @NotEmpty(message = "First name cannot be empty.", groups = NotEmptyGroup.class) @NotBlank(message = "First name cannot be blank.", groups = NotBlankGroup.class) String firstName,
			@NotNull(message = "Last name cannot be null.", groups = NotNullGroup.class) @NotEmpty(message = "Last name cannot be empty.", groups = NotEmptyGroup.class) @NotBlank(message = "Last name cannot be blank.", groups = NotBlankGroup.class) String lastName,
			@NotNull(message = "Mobile number cannot be null.", groups = NotNullGroup.class) @NotEmpty(message = "Mobile number cannot be empty.", groups = NotEmptyGroup.class) @NotBlank(message = "Mobile number cannot be blank.", groups = NotBlankGroup.class) String mobile,
			@NotNull(message = "Nick name cannot be null.", groups = NotNullGroup.class) @NotEmpty(message = "Nick name cannot be empty.", groups = NotEmptyGroup.class) @NotBlank(message = "Nick name cannot be blank.", groups = NotBlankGroup.class) String nickName,
			@NotNull(message = "Photo cannot be null.", groups = NotNullGroup.class) @NotEmpty(message = "Photo cannot be empty.", groups = NotEmptyGroup.class) @NotBlank(message = "Photo cannot be blank.", groups = NotBlankGroup.class) String photo,
			@NotNull(message = "Relation cannot be null.", groups = NotNullGroup.class) @NotEmpty(message = "Relation cannot be empty.", groups = NotEmptyGroup.class) @NotBlank(message = "Relation cannot be blank.", groups = NotBlankGroup.class) String relation) {
		super();
		this.address = address;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.nickName = nickName;
		this.photo = photo;
		this.relation = relation;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public String getNickName() {
		return nickName;
	}

	public String getPhoto() {
		return photo;
	}

	public String getRelation() {
		return relation;
	}

}
