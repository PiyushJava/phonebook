package com.developermind.phonebook.services.impl;

import java.util.List;
import java.util.Locale;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.developermind.phonebook.dto.PhonebookDto;
import com.developermind.phonebook.exceptions.PhonebookExceptions;
import com.developermind.phonebook.form.PhonebookForm;
import com.developermind.phonebook.repository.PhonebookRepository;
import com.developermind.phonebook.services.PhonebookServices;

@Service
class PhonebookServiceImpl implements PhonebookServices {

	final static Logger logger = LoggerFactory.getLogger(PhonebookServiceImpl.class);

	private final PhonebookRepository phonebookRepository;

	private final MessageSource messageSource;

	public PhonebookServiceImpl(PhonebookRepository phonebookRepository, MessageSource messageSource) {
		super();
		this.phonebookRepository = phonebookRepository;
		this.messageSource = messageSource;
	}

	@Override
	public PhonebookDto addRecord(@NotNull PhonebookForm phonebookForm) throws PhonebookExceptions {
		try {
			return phonebookRepository.addRecord(phonebookForm);
		} catch (DuplicateKeyException duplicateKeyException) {
			logger.error(messageSource.getMessage("PhonebookServiceImpl.0", null, Locale.ENGLISH), //$NON-NLS-1$
					duplicateKeyException);
			throw new PhonebookExceptions(phonebookForm,
					messageSource.getMessage("PhonebookServiceImpl.1", null, Locale.ENGLISH), //$NON-NLS-1$
					HttpStatus.CONFLICT.name());
		} catch (Exception exception) {
			logger.error(messageSource.getMessage("PhonebookServiceImpl.0", null, Locale.ENGLISH), exception); //$NON-NLS-1$
			throw new PhonebookExceptions(phonebookForm,
					messageSource.getMessage("PhonebookServiceImpl.2", null, Locale.ENGLISH), //$NON-NLS-1$
					HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}

	@Override
	public void deleteRecord(@NotNull Long id) throws PhonebookExceptions {
		try {
			phonebookRepository.deleteRecord(id);
		} catch (Exception exception) {
			logger.error(messageSource.getMessage("PhonebookServiceImpl.3", new Long[] { id }, Locale.ENGLISH), //$NON-NLS-1$
					exception);
			throw new PhonebookExceptions(id, messageSource.getMessage("PhonebookServiceImpl.4", null, Locale.ENGLISH), //$NON-NLS-1$
					HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}

	@Override
	public PhonebookDto getRecord(@NotNull Long id) throws PhonebookExceptions {
		try {
			return phonebookRepository.getRecord(id);
		} catch (EmptyResultDataAccessException emptyResultDataAccessException) {
			logger.error(messageSource.getMessage("PhonebookServiceImpl.5", new Long[] { id }, Locale.ENGLISH), //$NON-NLS-1$
					emptyResultDataAccessException);
			throw new PhonebookExceptions(id, messageSource.getMessage("PhonebookServiceImpl.6", null, Locale.ENGLISH), //$NON-NLS-1$
					HttpStatus.NOT_FOUND.name());
		} catch (Exception exception) {
			throw new PhonebookExceptions(id, messageSource.getMessage("PhonebookServiceImpl.7", null, Locale.ENGLISH), //$NON-NLS-1$
					HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}

	@Override
	public List<PhonebookDto> getRecords() throws PhonebookExceptions {
		try {
			return phonebookRepository.getRecords();
		} catch (Exception exception) {
			logger.error(messageSource.getMessage("PhonebookServiceImpl.8", null, Locale.ENGLISH), exception); //$NON-NLS-1$
			throw new PhonebookExceptions(messageSource.getMessage("PhonebookServiceImpl.9", null, Locale.ENGLISH), //$NON-NLS-1$
					HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}

	@Override
	public PhonebookDto updateRecord(@NotNull Long id, @NotNull PhonebookForm phonebookForm)
			throws PhonebookExceptions {
		try {
			return phonebookRepository.updateRecord(id, phonebookForm);
		} catch (DuplicateKeyException duplicateKeyException) {
			logger.error(messageSource.getMessage("PhonebookServiceImpl.10",new Long[] {id},Locale.ENGLISH),duplicateKeyException); //$NON-NLS-1$
			throw new PhonebookExceptions(phonebookForm, messageSource.getMessage("PhonebookServiceImpl.11",null,Locale.ENGLISH), //$NON-NLS-1$
					HttpStatus.CONFLICT.name());
		} catch (Exception exception) {
			logger.error(messageSource.getMessage("PhonebookServiceImpl.10",new Long[] {id},Locale.ENGLISH),exception); //$NON-NLS-1$
			throw new PhonebookExceptions(id, messageSource.getMessage("PhonebookServiceImpl.12",null,Locale.ENGLISH), //$NON-NLS-1$
					HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}

}
