jdbc使用c3p0连接池连接数据库：
需要使用的的jar包：
	c3p0-0.9.5-pre8.jar
	mchange-commons-java-0.2.7.jar
	mysql-connector-java-5.1.30.jar
	
参数说明：
initialPoolSize: 连接池初始化时创建的连接数,default : 3  
minPoolSize: 连接池保持的最小连接数,default : 3   
maxPoolSize: 连接池中拥有的最大连接数，如果获得新连接时会使连接总数超过这个值则不会再获取新连接，而是等待其他连接释放，所以这个值有可能会设计地很大,default : 15
acquireIncrement:连接池在无空闲连接可用时一次性创建的新数据库连接数,default : 3   
maxIdleTime:连接的最大空闲时间，如果超过这个时间，某个数据库连接还没有被使用，则会断开掉这个连接。如果为0，则永远不会断开连接,即回收此连接。default : 0 单位 s  


acquireRetryAttempts:连接池在获得新连接失败时重试的次数，如果小于等于0则无限重试直至连接获得成功。default : 30   
acquireRetryDelay:连接池在获得新连接时的间隔时间。default : 1000 单位ms   


preferredTestQuery:与上面的automaticTestTable二者只能选一。自己实现一条SQL检测语句。default : null   
idleConnectionTestPeriod:用来配置测试空闲连接的间隔时间。测试方式还是上面的两种之一，可以用来解决MySQL8小时断开连接的问题。因为它保证连接池会每隔一定时间对空闲连接进行一次测试，从而保证有效的空闲连接能每隔一定时间访问一次数据库，将于MySQL8小时无会话的状态打破。为0则不测试。default : 0   
	
	
backup/c3p0-config-backup.xml，带注释	   	
参考连接：  
	1、http://www.mchange.com/projects/c3p0/（英文）  
	2、http://liu.fm/2015/07/15/c3p0/（中文，上面英文的翻译版本。）   
	 
	
http://josh-persistence.iteye.com/blog/2229929   

http://blog.csdn.net/wangking717/article/details/4491702（c3p0的每个属性的意思）
