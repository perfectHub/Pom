###Spring配置多数据源
 > 核心思想：Spring在每次操作数据库的时候都会通过AbstractRoutingDataSource类中的determineTargetDataSource()方法获取当前数据源，我们就可以通过切面技术，在不同的切面，切入不同的数据源名称，使Spring获取的时候拿到的是不同的数据源。

 ![image](http://www.hifreud.com/images/blog/spring/07-spring-datasource/01_source_determinate.png)
 
 而determineCurrentLookupKey()方法是抽象的，所以，我们可以实现这个类，重写determineCurrentLookupKey方法，通过切面技术实现多数据源之间切换  
 
```java
/**
 * Determine the current lookup key. This will typically be
 * implemented to check a thread-bound transaction context.
 * <p>Allows for arbitrary keys. The returned key needs
 * to match the stored lookup key type, as resolved by the
 * {@link #resolveSpecifiedLookupKey} method.
 */
protected abstract Object determineCurrentLookupKey();
```  

参考地址：http://www.hifreud.com/2015/02/25/07-spring-datasources/  
http://www.cnblogs.com/liujiduo/p/5004691.html  

