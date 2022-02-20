package com.developermind.phonebook.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.developermind.phonebook.dto.PhonebookDto;
import com.developermind.phonebook.form.PhonebookForm;

public interface PhonebookRepository {

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	PhonebookDto addRecord(@NotNull PhonebookForm phonebookForm);

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	void deleteRecord(@NotNull Long id);

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	PhonebookDto getRecord(@NotNull Long id);

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	List<PhonebookDto> getRecords();

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	PhonebookDto updateRecord(@NotNull Long id, @NotNull PhonebookForm phonebookForm);
}
