package com.wallethub.parser.service.accesslog;

import com.wallethub.parser.dao.BlockedIpDao;
import com.wallethub.parser.domain.BlockedIp;
import com.wallethub.parser.exception.BlockedIpServiceException;
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
