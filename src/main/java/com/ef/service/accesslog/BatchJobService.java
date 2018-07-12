package com.ef.service.accesslog;

import com.ef.exception.BatchJobServiceException;

/**
 *
 * @author Rommel D. Medina
 */
public interface BatchJobService {

    public abstract void launch(String path) throws BatchJobServiceException;

}
