package com.developermind.phonebook.services;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.developermind.phonebook.dto.PhonebookDto;
import com.developermind.phonebook.exceptions.PhonebookExceptions;
import com.developermind.phonebook.form.PhonebookForm;

public interface PhonebookServices {

	PhonebookDto addRecord(@NotNull PhonebookForm phonebookForm) throws PhonebookExceptions;

	void deleteRecord(@NotNull Long id) throws PhonebookExceptions;

	PhonebookDto getRecord(@NotNull Long id) throws PhonebookExceptions;

	List<PhonebookDto> getRecords() throws PhonebookExceptions;

	PhonebookDto updateRecord(@NotNull Long id, @NotNull PhonebookForm phonebookForm) throws PhonebookExceptions;

}
