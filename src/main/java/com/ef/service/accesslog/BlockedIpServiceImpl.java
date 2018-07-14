package com.ef.service.accesslog;

import com.ef.dao.BlockedIpDao;
import com.ef.domain.BlockedIp;
import com.ef.exception.BlockedIpServiceException;
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
public class BlockedIpServiceImpl implements BlockedIpService {

    private final Logger logger
            = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    BlockedIpDao blockedIpDao;

    @Override
    public void save(BlockedIp blockedIp) throws BlockedIpServiceException {
        try {
            blockedIpDao.save(blockedIp);
        }
        catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new BlockedIpServiceException(ex);
        }
    }

    @Override
    public void saveAll(List<BlockedIp> blockedIps)
            throws BlockedIpServiceException {
        try {
            blockedIpDao.saveAll(blockedIps);
        }
        catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new BlockedIpServiceException(ex);
        }
    }

}
