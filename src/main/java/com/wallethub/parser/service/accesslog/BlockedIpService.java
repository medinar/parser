package com.wallethub.parser.service.accesslog;

import com.wallethub.parser.domain.BlockedIp;
import com.wallethub.parser.exception.BlockedIpServiceException;
import java.util.List;

/**
 *
 * @author Rommel D. Medina
 */
public interface BlockedIpService {

    public void save(BlockedIp blockedIp) throws BlockedIpServiceException;

    public void saveAll(List<BlockedIp> blockedIps) throws BlockedIpServiceException;

}
