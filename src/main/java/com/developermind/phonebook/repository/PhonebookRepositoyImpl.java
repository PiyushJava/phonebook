package com.developermind.phonebook.repository;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import org.json.JSONObject;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.developermind.phonebook.dto.PhonebookDto;
import com.developermind.phonebook.dto.PhonebookRowMapper;
import com.developermind.phonebook.form.PhonebookForm;
import com.zaxxer.hikari.HikariDataSource;

@Repository
class PhonebookRepositoyImpl implements PhonebookRepository {

	private final List<String> columnNames = new ArrayList<>(9);

	private final JdbcTemplate jdbcTemplate;

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private final SimpleJdbcInsert simpleJdbcInsert;

	private final MessageSource messageSource;

	PhonebookRepositoyImpl(HikariDataSource hikariDataSource, MessageSource messageSource) {
		this.messageSource = messageSource;
		this.jdbcTemplate = new JdbcTemplate(hikariDataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(hikariDataSource);
		this.simpleJdbcInsert = new SimpleJdbcInsert(hikariDataSource);
	}

	@Override
	public PhonebookDto addRecord(@NotNull PhonebookForm phonebookForm) {
		simpleJdbcInsert.setTableName(messageSource.getMessage("PhonebookRepositoyImpl.0", null, Locale.ENGLISH)); //$NON-NLS-1$
		simpleJdbcInsert.setSchemaName(messageSource.getMessage("PhonebookRepositoyImpl.1", null, Locale.ENGLISH)); //$NON-NLS-1$
		simpleJdbcInsert
				.setGeneratedKeyName(messageSource.getMessage("PhonebookRepositoyImpl.2", null, Locale.ENGLISH)); //$NON-NLS-1$
		simpleJdbcInsert.setColumnNames(columnNames);
		simpleJdbcInsert.compile();
		Map<String, Object> args = new JSONObject(phonebookForm).toMap();
		Long id = simpleJdbcInsert.executeAndReturnKey(args).longValue();
		return new PhonebookDto(id, phonebookForm);
	}

	@Override
	public void deleteRecord(@NotNull Long id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		namedParameterJdbcTemplate.execute(messageSource.getMessage("PhonebookRepositoyImpl.3", null, Locale.ENGLISH),
				mapSqlParameterSource, new PreparedStatementCallback<Boolean>() {
					@Override
					public Boolean doInPreparedStatement(PreparedStatement ps)
							throws SQLException, DataAccessException {
						return ps.execute();
					}
				});
	}

	@Override
	public PhonebookDto getRecord(@NotNull Long id) {
		return jdbcTemplate.queryForObject(messageSource.getMessage("PhonebookRepositoyImpl.4", null, Locale.ENGLISH), //$NON-NLS-1$
				new Long[] { id }, new int[] { Types.BIGINT }, new PhonebookRowMapper());
	}

	@Override
	public List<PhonebookDto> getRecords() {
		return jdbcTemplate.query(messageSource.getMessage("PhonebookRepositoyImpl.5", null, Locale.ENGLISH), //$NON-NLS-1$
				new PhonebookRowMapper());
	}

	@PostConstruct
	void init() {
		columnNames.addAll(Arrays.asList(PhonebookDto.class.getFields()).stream().map(Field::getName)
				.collect(Collectors.toList()));
	}

	@Override
	public PhonebookDto updateRecord(@NotNull Long id, @NotNull PhonebookForm phonebookForm) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		Map<String, Object> args = new JSONObject(phonebookForm).toMap();
		args.forEach(mapSqlParameterSource::addValue);
		mapSqlParameterSource.addValue("id", id);
		namedParameterJdbcTemplate.update(messageSource.getMessage("PhonebookRepositoyImpl.6", null, Locale.ENGLISH), //$NON-NLS-1$
				mapSqlParameterSource);
		return null;
	}

}
