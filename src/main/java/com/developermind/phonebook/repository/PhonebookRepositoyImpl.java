package com.developermind.phonebook.repository;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import org.json.JSONObject;
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

	PhonebookRepositoyImpl(HikariDataSource hikariDataSource) {
		this.jdbcTemplate = new JdbcTemplate(hikariDataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(hikariDataSource);
		this.simpleJdbcInsert = new SimpleJdbcInsert(hikariDataSource);
	}

	@Override
	public PhonebookDto addRecord(@NotNull PhonebookForm phonebookForm) {
		simpleJdbcInsert.setTableName(Messages.getString("PhonebookRepositoyImpl.0")); //$NON-NLS-1$
		simpleJdbcInsert.setSchemaName(Messages.getString("PhonebookRepositoyImpl.1")); //$NON-NLS-1$
		simpleJdbcInsert.setGeneratedKeyName(Messages.getString("PhonebookRepositoyImpl.2")); //$NON-NLS-1$
		simpleJdbcInsert.setColumnNames(columnNames);
		simpleJdbcInsert.compile();
		Map<String, Object> args= new JSONObject(phonebookForm).toMap();
		Long id = simpleJdbcInsert.executeAndReturnKey(args).longValue();
		return new PhonebookDto(id, phonebookForm);
	}

	@Override
	public void deleteRecord(@NotNull Long id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		namedParameterJdbcTemplate.execute(Messages.getString("PhonebookRepositoyImpl.3"), mapSqlParameterSource, new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				return ps.execute();
			}
		});
	}

	@Override
	public PhonebookDto getRecord(@NotNull Long id) {
		return jdbcTemplate.queryForObject(Messages.getString("PhonebookRepositoyImpl.4"), new Long[] { id }, //$NON-NLS-1$
				new int[] { Types.BIGINT },new PhonebookRowMapper());
	}

	@Override
	public List<PhonebookDto> getRecords() {
		return jdbcTemplate.query(Messages.getString("PhonebookRepositoyImpl.5"), new PhonebookRowMapper()); //$NON-NLS-1$
	}

	@PostConstruct
	void init() {
		columnNames.addAll(Arrays.asList(PhonebookDto.class.getFields()).stream().map(Field::getName)
				.collect(Collectors.toList()));
	}

	@Override
	public PhonebookDto updateRecord(@NotNull Long id, @NotNull PhonebookForm phonebookForm) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		Map<String, Object> args= new JSONObject(phonebookForm).toMap();
		args.forEach(mapSqlParameterSource::addValue);
		mapSqlParameterSource.addValue("id", id);
		namedParameterJdbcTemplate.update(
				Messages.getString("PhonebookRepositoyImpl.6"), //$NON-NLS-1$
				mapSqlParameterSource);
		return null;
	}

}
