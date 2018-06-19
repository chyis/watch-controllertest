# watch-controllertest

mysql数据库表结构：

CREATE TABLE `watch_commonds` (
  `cid` int(11) NOT NULL,
  `merchantid` varchar(10) NOT NULL,
  `deviceid` varchar(20) NOT NULL,
  `commond` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `createtime` int(11) NOT NULL DEFAULT '0',
  `sendedtime` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 
CREATE TABLE `watch_log` (
  `logid` int(11) NOT NULL,
  `merchantid` varchar(10) COLLATE utf8_bin NOT NULL,
  `deviceid` varchar(20) COLLATE utf8_bin NOT NULL,
  `logdata` text COLLATE utf8_bin NOT NULL,
  `createtime` int(11) NOT NULL DEFAULT '0',
  `updatetime` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
 
 
ALTER TABLE `watch_commonds`
  ADD PRIMARY KEY (`cid`);
 
 
ALTER TABLE `watch_log`
  ADD PRIMARY KEY (`logid`);
 
 
ALTER TABLE `watch_commonds`
  MODIFY `cid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
 
 
ALTER TABLE `watch_log`
  MODIFY `logid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;COMMIT;
