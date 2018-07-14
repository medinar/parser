package com.ef.service.accesslog;

import com.ef.domain.Blacklist;
import com.ef.exception.BlacklistServiceException;
import java.util.List;

/**
 *
 * @author Rommel D. Medina
 */
public interface BlacklistService {

    public void save(Blacklist blacklist) throws BlacklistServiceException;

    public void saveAll(List<Blacklist> blacklistedIps) throws BlacklistServiceException;

}
