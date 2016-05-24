package ee.kaarelkivistik.webarchitecture.dao;

import ee.kaarelkivistik.webarchitecture.models.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by kaarel on 23.05.16.
 */

@Component
public class DeviceTypeDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DeviceTypeRowMapper rowMapper;

    public void insert(DeviceType deviceType) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("device_type")
                .usingGeneratedKeyColumns("device_type");

        HashMap<String, Object> map = new HashMap<>();

        map.put("super_type_id", deviceType.getSuperTypeId());
        map.put("level", deviceType.getLevel());
        map.put("type_name", deviceType.getName());

        Number number = simpleJdbcInsert.executeAndReturnKey(map);

        deviceType.setId(number.intValue());
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM device_type WHERE device_type = ?", id);
    }

    public void delete(DeviceType deviceType) {
        delete(deviceType.getId());
    }

    public DeviceType findOne(Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM device_type WHERE device_type = ?", rowMapper, id);
        } catch(DataAccessException e) {
            return null;
        }
    }

}

@Component
class DeviceTypeRowMapper implements RowMapper<DeviceType> {

    @Override
    public DeviceType mapRow(ResultSet resultSet, int i) throws SQLException {
        DeviceType deviceType = new DeviceType(resultSet.getShort("level"), resultSet.getString("type_name"));

        deviceType.setId(resultSet.getInt("device_type"));

        return deviceType;
    }

}