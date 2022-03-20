package com.developermind.phonebook.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developermind.phonebook.annotations.sequence.ValidationSequence;
import com.developermind.phonebook.dto.PhonebookDto;
import com.developermind.phonebook.dto.ResponseDto;
import com.developermind.phonebook.exceptions.PhonebookExceptions;
import com.developermind.phonebook.form.PhonebookForm;
import com.developermind.phonebook.services.PhonebookServices;

@RestController
@RequestMapping(value = "/api/v1/phonebook")
public class PhonebookRestController {

	private final PhonebookServices phonebookServices;

	private final MessageSource messageSource;

	public PhonebookRestController(PhonebookServices phonebookServices, MessageSource messageSource) {
		super();
		this.phonebookServices = phonebookServices;
		this.messageSource = messageSource;
	}

	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<PhonebookDto>> addRecord(
			@Validated(value = ValidationSequence.class) @RequestBody PhonebookForm phonebookForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String fieldName = bindingResult.getFieldError().getField();
			String rejectedValue = String.valueOf(bindingResult.getFieldError().getRejectedValue());
			String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
			String errorMessage = messageSource.getMessage("PhonebookRestController.0", null, Locale.ENGLISH) //$NON-NLS-1$
					+ fieldName + messageSource.getMessage("PhonebookRestController.1", null, Locale.ENGLISH) //$NON-NLS-1$
					+ rejectedValue + messageSource.getMessage("PhonebookRestController.2", null, Locale.ENGLISH) //$NON-NLS-1$
					+ defaultMessage;
			return ResponseEntity.badRequest()
					.body(new ResponseDto<PhonebookDto>(errorMessage, false, null, HttpStatus.BAD_REQUEST));
		}
		try {
			PhonebookDto phonebookDto = phonebookServices.addRecord(phonebookForm);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseDto<>(messageSource.getMessage("PhonebookRestController.3", null, Locale.ENGLISH), //$NON-NLS-1$
							true, phonebookDto, HttpStatus.CREATED));
		} catch (PhonebookExceptions phonebookExceptions) {
			return ResponseEntity.internalServerError()
					.body(new ResponseDto<PhonebookDto>(phonebookExceptions.getMessage(), false, null,
							HttpStatus.valueOf(phonebookExceptions.getStatus())));
		}
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<Void>> deleteRecord(@PathVariable Long id) {
		try {
			phonebookServices.getRecord(id);
		} catch (PhonebookExceptions phonebookExceptions) {
			return ResponseEntity.notFound().build();
		}
		try {
			phonebookServices.deleteRecord(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto<Void>(
							messageSource.getMessage("PhonebookRestController.4", null, Locale.ENGLISH), true, null, //$NON-NLS-1$
							HttpStatus.OK));
		} catch (PhonebookExceptions phonebookExceptions) {
			return ResponseEntity.internalServerError().body(new ResponseDto<Void>(phonebookExceptions.getMessage(),
					false, null, HttpStatus.valueOf(phonebookExceptions.getStatus())));
		}
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<PhonebookDto>> getRecord(@PathVariable Long id) {
		try {
			PhonebookDto phonebookDto = phonebookServices.getRecord(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto<>(messageSource.getMessage("PhonebookRestController.5", null, Locale.ENGLISH), //$NON-NLS-1$
							true, phonebookDto, HttpStatus.FOUND));
		} catch (PhonebookExceptions phonebookExceptions) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<List<PhonebookDto>>> getRecords() {
		try {
			List<PhonebookDto> phonebookDtoList = phonebookServices.getRecords();
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto<>(messageSource.getMessage("PhonebookRestController.6", null, Locale.ENGLISH), //$NON-NLS-1$
							true, phonebookDtoList, HttpStatus.OK));
		} catch (PhonebookExceptions phonebookExceptions) {
			return ResponseEntity.internalServerError()
					.body(new ResponseDto<List<PhonebookDto>>(phonebookExceptions.getMessage(), false, null,
							HttpStatus.valueOf(phonebookExceptions.getStatus())));
		}
	}

	@PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<PhonebookDto>> updateRecord(@PathVariable Long id,
			@Validated(value = ValidationSequence.class) @RequestBody PhonebookForm phonebookForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String fieldName = bindingResult.getFieldError().getField();
			String rejectedValue = bindingResult.getFieldError().getRejectedValue().toString();
			String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
			String errorMessage = messageSource.getMessage("PhonebookRestController.7", null, Locale.ENGLISH) //$NON-NLS-1$
					+ fieldName + messageSource.getMessage("PhonebookRestController.8", null, Locale.ENGLISH) //$NON-NLS-1$
					+ rejectedValue + messageSource.getMessage("PhonebookRestController.9", null, Locale.ENGLISH) //$NON-NLS-1$
					+ defaultMessage;
			return ResponseEntity.badRequest()
					.body(new ResponseDto<PhonebookDto>(errorMessage, false, null, HttpStatus.BAD_REQUEST));
		}
		try {
			phonebookServices.getRecord(id);
		} catch (PhonebookExceptions phonebookExceptions) {
			return ResponseEntity.notFound().build();
		}
		try {
			PhonebookDto phonebookDto = phonebookServices.updateRecord(id, phonebookForm);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto<>(
							messageSource.getMessage("PhonebookRestController.10", null, Locale.ENGLISH), true, //$NON-NLS-1$
							phonebookDto, HttpStatus.OK));
		} catch (PhonebookExceptions phonebookExceptions) {
			return ResponseEntity.internalServerError()
					.body(new ResponseDto<PhonebookDto>(phonebookExceptions.getMessage(), false, null,
							HttpStatus.valueOf(phonebookExceptions.getStatus())));
		}
	}

}
