package com.ef.service.accesslog;

import com.ef.dao.BlacklistDao;
import com.ef.domain.Blacklist;
import com.ef.exception.BlacklistServiceException;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rommel Medina
 */
@Service
public class BlacklistServiceImpl implements BlacklistService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    BlacklistDao blacklistDao;

    @Override
    public void save(Blacklist blacklist) throws BlacklistServiceException {
        try {
            blacklistDao.save(blacklist);
        }
        catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new BlacklistServiceException(ex);
        }
    }

    @Override
    public void saveAll(List<Blacklist> blacklistedIps) throws BlacklistServiceException {
        try {
            blacklistDao.saveAll(blacklistedIps);
        }
        catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new BlacklistServiceException(ex);
        }
    }

}
