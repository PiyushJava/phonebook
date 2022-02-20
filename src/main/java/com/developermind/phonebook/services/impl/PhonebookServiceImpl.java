package com.developermind.phonebook.services.impl;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	public PhonebookServiceImpl(PhonebookRepository phonebookRepository) {
		super();
		this.phonebookRepository = phonebookRepository;
	}

	@Override
	public PhonebookDto addRecord(@NotNull PhonebookForm phonebookForm) throws PhonebookExceptions {
		try {
			return phonebookRepository.addRecord(phonebookForm);
		} catch (DuplicateKeyException duplicateKeyException) {
			logger.error(Messages.getString("PhonebookServiceImpl.0"), duplicateKeyException); //$NON-NLS-1$
			throw new PhonebookExceptions(phonebookForm, Messages.getString("PhonebookServiceImpl.1"), //$NON-NLS-1$
					HttpStatus.CONFLICT.name());
		} catch (Exception exception) {
			logger.error(Messages.getString("PhonebookServiceImpl.0"), exception); //$NON-NLS-1$
			throw new PhonebookExceptions(phonebookForm, Messages.getString("PhonebookServiceImpl.2"), //$NON-NLS-1$
					HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}

	@Override
	public void deleteRecord(@NotNull Long id) throws PhonebookExceptions {
		try {
			phonebookRepository.deleteRecord(id);
		} catch (Exception exception) {
			logger.error(Messages.getString("PhonebookServiceImpl.3"), id, exception); //$NON-NLS-1$
			throw new PhonebookExceptions(id, Messages.getString("PhonebookServiceImpl.4"), //$NON-NLS-1$
					HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}

	@Override
	public PhonebookDto getRecord(@NotNull Long id) throws PhonebookExceptions {
		try {
			return phonebookRepository.getRecord(id);
		} catch (EmptyResultDataAccessException emptyResultDataAccessException) {
			logger.error(Messages.getString("PhonebookServiceImpl.5"), id, emptyResultDataAccessException); //$NON-NLS-1$
			throw new PhonebookExceptions(id, Messages.getString("PhonebookServiceImpl.6"), //$NON-NLS-1$
					HttpStatus.NOT_FOUND.name());
		} catch (Exception exception) {
			throw new PhonebookExceptions(id, Messages.getString("PhonebookServiceImpl.7"), //$NON-NLS-1$
					HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}

	@Override
	public List<PhonebookDto> getRecords() throws PhonebookExceptions {
		try {
			return phonebookRepository.getRecords();
		} catch (Exception exception) {
			logger.error(Messages.getString("PhonebookServiceImpl.8"), exception); //$NON-NLS-1$
			throw new PhonebookExceptions(Messages.getString("PhonebookServiceImpl.9"), //$NON-NLS-1$
					HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}

	@Override
	public PhonebookDto updateRecord(@NotNull Long id, @NotNull PhonebookForm phonebookForm)
			throws PhonebookExceptions {
		try {
			return phonebookRepository.updateRecord(id, phonebookForm);
		} catch (DuplicateKeyException duplicateKeyException) {
			logger.error(Messages.getString("PhonebookServiceImpl.10"), id, duplicateKeyException); //$NON-NLS-1$
			throw new PhonebookExceptions(phonebookForm, Messages.getString("PhonebookServiceImpl.11"), //$NON-NLS-1$
					HttpStatus.CONFLICT.name());
		} catch (Exception exception) {
			logger.error(Messages.getString("PhonebookServiceImpl.10"), id, exception); //$NON-NLS-1$
			throw new PhonebookExceptions(id, Messages.getString("PhonebookServiceImpl.12"), //$NON-NLS-1$
					HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}

}
