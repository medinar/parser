—- (1) MySQL query for finding IPs that made more than 100 requests starting from 2017-01-01.00:00:00 to 2017-01-01.01:00:00.
select a.ip_address from 
    (select ip_address, count(0) log_count 
    from access_log 
    where log_date 
        between STR_TO_DATE('2017-01-01.00:00:00', '%Y-%m-%d.%H:%i:%s') 
        and STR_TO_DATE('2017-01-01.01:00:00', '%Y-%m-%d.%H:%i:%s') 
    group by ip_address) a
 where log_count > 100;

—- (2) MySQL query for finding requests made by a given IP
select * from access_log where ip_address='192.168.99.166';