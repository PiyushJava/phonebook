package com.developermind.phonebook.controller;

import java.util.List;

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

	public PhonebookRestController(PhonebookServices phonebookServices) {
		super();
		this.phonebookServices = phonebookServices;
	}

	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<PhonebookDto>> addRecord(
			@Validated(value = ValidationSequence.class) @RequestBody PhonebookForm phonebookForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String fieldName = bindingResult.getFieldError().getField();
			String rejectedValue = String.valueOf(bindingResult.getFieldError().getRejectedValue());
			String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
			String errorMessage = Messages.getString("PhonebookRestController.0") + fieldName //$NON-NLS-1$
					+ Messages.getString("PhonebookRestController.1") + rejectedValue //$NON-NLS-1$
					+ Messages.getString("PhonebookRestController.2") //$NON-NLS-1$
					+ defaultMessage;
			return ResponseEntity.badRequest()
					.body(new ResponseDto<PhonebookDto>(errorMessage, false, null, HttpStatus.BAD_REQUEST));
		}
		try {
			PhonebookDto phonebookDto = phonebookServices.addRecord(phonebookForm);
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto<>(
					Messages.getString("PhonebookRestController.3"), true, phonebookDto, HttpStatus.CREATED)); //$NON-NLS-1$
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
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseDto<Void>(Messages.getString("PhonebookRestController.4"), true, null, HttpStatus.OK)); //$NON-NLS-1$
		} catch (PhonebookExceptions phonebookExceptions) {
			return ResponseEntity.internalServerError().body(new ResponseDto<Void>(phonebookExceptions.getMessage(),
					false, null, HttpStatus.valueOf(phonebookExceptions.getStatus())));
		}
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<PhonebookDto>> getRecord(@PathVariable Long id) {
		try {
			PhonebookDto phonebookDto = phonebookServices.getRecord(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
					Messages.getString("PhonebookRestController.5"), true, phonebookDto, HttpStatus.FOUND)); //$NON-NLS-1$
		} catch (PhonebookExceptions phonebookExceptions) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<List<PhonebookDto>>> getRecords() {
		try {
			List<PhonebookDto> phonebookDtoList = phonebookServices.getRecords();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
					Messages.getString("PhonebookRestController.6"), true, phonebookDtoList, HttpStatus.OK)); //$NON-NLS-1$
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
			String errorMessage = Messages.getString("PhonebookRestController.7") + fieldName //$NON-NLS-1$
					+ Messages.getString("PhonebookRestController.8") + rejectedValue //$NON-NLS-1$
					+ Messages.getString("PhonebookRestController.9") //$NON-NLS-1$
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
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
					Messages.getString("PhonebookRestController.10"), true, phonebookDto, HttpStatus.OK)); //$NON-NLS-1$
		} catch (PhonebookExceptions phonebookExceptions) {
			return ResponseEntity.internalServerError()
					.body(new ResponseDto<PhonebookDto>(phonebookExceptions.getMessage(), false, null,
							HttpStatus.valueOf(phonebookExceptions.getStatus())));
		}
	}

}
