package application.dao.mappers;

import application.models.FileDescription;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FileDescriptionMapper implements RowMapper<FileDescription> {
    @Override
    public FileDescription mapRow(ResultSet rs, int rowNum) throws SQLException {
        FileDescription fileDescription = new FileDescription();
        fileDescription.setId(rs.getInt("id"));
        fileDescription.setOwnerId(rs.getInt("owner_id"));
        fileDescription.setName(rs.getString("name"));
        fileDescription.setRelativeFilePath(rs.getString("path"));
        fileDescription.setRawFileURL(rs.getString("url"));
        fileDescription.setFormat(rs.getString("format"));
        fileDescription.setBytes(rs.getInt("bytes"));
        fileDescription.setType(rs.getString("type"));
        fileDescription.setTime(rs.getLong("time"));
        return fileDescription;
    }
}
