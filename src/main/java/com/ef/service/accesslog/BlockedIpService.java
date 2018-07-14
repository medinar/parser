package com.ef.service.accesslog;

import com.ef.domain.BlockedIp;
import com.ef.exception.BlockedIpServiceException;
import java.util.List;

/**
 *
 * @author Rommel D. Medina
 */
public interface BlockedIpService {

    public void save(BlockedIp blockedIp) throws BlockedIpServiceException;

    public void saveAll(List<BlockedIp> blockedIps) throws BlockedIpServiceException;

}
