package com.wallethub.parser.dao;

import com.wallethub.parser.domain.BlockedIp;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Rommel D. Medina
 */
public interface BlockedIpDao {

    public abstract int save(BlockedIp blockedIp) throws SQLException;

    public abstract int[] saveAll(List<BlockedIp> blockedIps) throws SQLException;

}
