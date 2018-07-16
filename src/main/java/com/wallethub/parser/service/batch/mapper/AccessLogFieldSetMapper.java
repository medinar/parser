package com.wallethub.parser.service.batch.mapper;

import com.wallethub.parser.domain.AccessLog;
import java.sql.Timestamp;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

/**
 *
 * @author Rommel Medina
 */
@Component
public class AccessLogFieldSetMapper implements FieldSetMapper<AccessLog> {

    @Override
    public AccessLog mapFieldSet(FieldSet fs) throws BindException {
        return new AccessLog(
                new Timestamp(fs.readDate("logDate").getTime()),
                fs.readString("ipAddress"),
                fs.readString("request"),
                fs.readString("status"),
                fs.readString("userAgent")
        );
    }

}
