package application.dao;

import application.dao.mappers.FileDescriptionMapper;
import application.dao.mappers.PathMapper;
import application.models.FileDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DaoFile {

    private final JdbcTemplate jdbcTemplate;

    public void save(FileDescription fileDescription) {
        String sqlInsertFileDescription = "INSERT INTO image (owner_id, name, path," +
                " url, format, bytes, type, time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlInsertFileDescription, fileDescription.getOwnerId(), fileDescription.getFileName(),
                fileDescription.getRelativeFilePath(), fileDescription.getRawFileURL(), fileDescription.getFileFormat(),
                fileDescription.getBytes(), fileDescription.getFileType(), fileDescription.getCreatedAt());
    }

    public String getPath(int id) {

        String sqlGetPathById = "SELECT path FROM image WHERE id = ?";
        return jdbcTemplate.query(sqlGetPathById, new Object[]{id}, new PathMapper()).stream().findAny()
                .orElse(null);
    }

    public FileDescription saveAndReturn(FileDescription fileDescription) {

        SimpleJdbcInsert sji = new SimpleJdbcInsert(jdbcTemplate).withTableName("image").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("owner_id", fileDescription.getOwnerId());
        parameters.put("name", fileDescription.getFileName());
        parameters.put("path", fileDescription.getRelativeFilePath());
        parameters.put("url", fileDescription.getRawFileURL());
        parameters.put("format", fileDescription.getFileFormat());
        parameters.put("bytes", fileDescription.getBytes());
        parameters.put("type", fileDescription.getFileType());
        parameters.put("time", System.currentTimeMillis());
        return getById(sji.executeAndReturnKey(parameters).intValue());
    }

    public FileDescription getById(int id) {

        String query = "SELECT * FROM image WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new FileDescriptionMapper());
    }
}


