package com.developermind.phonebook.dto;

import javax.validation.constraints.NotNull;

import com.developermind.phonebook.form.PhonebookForm;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class PhonebookDto extends PhonebookForm {

	private final Long id;

	public PhonebookDto(Long id, @NotNull PhonebookForm phonebookForm) {
		this(phonebookForm.getFirstName(), phonebookForm.getLastName(), phonebookForm.getEmail(),
				phonebookForm.getMobile(), phonebookForm.getAddress(), phonebookForm.getNickName(),
				phonebookForm.getRelation(), phonebookForm.getPhoto(), id);
	}

	protected PhonebookDto(String firstName, String lastName, String email, String mobile, String address,
			String nickName, String relation, String photo, Long id) {
		super(address, email, firstName, lastName, mobile, nickName, photo, relation);  
		this.id = id;
	}

	public Long getId() {
		return id;
	}

}
