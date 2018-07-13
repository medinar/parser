package com.ef.service.accesslog;

import com.ef.domain.Blacklist;
import com.ef.exception.BlacklistServiceException;

/**
 *
 * @author Rommel D. Medina
 */
public interface BlacklistService {

    public void save(Blacklist blacklist) throws BlacklistServiceException;

}
