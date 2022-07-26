## mybatis 中使用特殊符号：

#### 方案1：

```
<	&lt;
>	&gt;
&	&amp;
"	&quot;
’	&apos;
<=	&lt;=
>=	&gt;=
```

#### 方案2：

<![CDATA[ ]]>是xml语法，在<![CDATA[ ]]>内部的所有内容都会被解析器忽略，不进行转义。所以在xml中这是一种通用方案。

特殊字符	<![CDATA[ ]]>
<	<![CDATA[<]]>

>		<![CDATA[>]]>
>	&	<![CDATA[&]]>
>	"	<![CDATA["]]>
>	’	<![CDATA[']]>
>	<=	<![CDATA[<=]]>
>	>=	<![CDATA[>=]]>
>	!=	<![CDATA[!=]]>

## 一些特殊函数：

#### CONVERT()函数：

CONVERT(data_type,expression[,style])
convert(varchar(10),字段名,转换格式)

此样式一般在时间类型(datetime,smalldatetime)与字符串类型(nchar,nvarchar,char,varchar)
相互转换的时候才用到.

```sql
语句 结果
SELECT CONVERT(varchar(100), GETDATE(), 0) 07 15 2009 4:06PM
SELECT CONVERT(varchar(100), GETDATE(), 1) 07/15/09
SELECT CONVERT(varchar(100), GETDATE(), 2) 09.07.15
SELECT CONVERT(varchar(100), GETDATE(), 3) 15/07/09
SELECT CONVERT(varchar(100), GETDATE(), 4) 15.07.09
SELECT CONVERT(varchar(100), GETDATE(), 5) 15-07-09
SELECT CONVERT(varchar(100), GETDATE(), 6) 15 07 09
SELECT CONVERT(varchar(100), GETDATE(), 7) 07 15, 09
SELECT CONVERT(varchar(100), GETDATE(), 8) 16:06:26
SELECT CONVERT(varchar(100), GETDATE(), 9) 07 15 2009 4:06:26:513PM
SELECT CONVERT(varchar(100), GETDATE(), 10) 07-15-09
SELECT CONVERT(varchar(100), GETDATE(), 11) 09/07/15
SELECT CONVERT(varchar(100), GETDATE(), 12) 090715
SELECT CONVERT(varchar(100), GETDATE(), 13) 15 07 2009 16:06:26:513
SELECT CONVERT(varchar(100), GETDATE(), 14) 16:06:26:513
SELECT CONVERT(varchar(100), GETDATE(), 20) 2009-07-15 16:06:26
SELECT CONVERT(varchar(100), GETDATE(), 21) 2009-07-15 16:06:26.513
SELECT CONVERT(varchar(100), GETDATE(), 22) 07/15/09 4:06:26 PM
SELECT CONVERT(varchar(100), GETDATE(), 23) 2009-07-15
SELECT CONVERT(varchar(100), GETDATE(), 24) 16:06:26
SELECT CONVERT(varchar(100), GETDATE(), 25) 2009-07-15 16:06:26.513
SELECT CONVERT(varchar(100), GETDATE(), 100) 07 15 2009 4:06PM
SELECT CONVERT(varchar(100), GETDATE(), 101) 07/15/2009
SELECT CONVERT(varchar(100), GETDATE(), 102) 2009.07.15
SELECT CONVERT(varchar(100), GETDATE(), 103) 15/07/2009
SELECT CONVERT(varchar(100), GETDATE(), 104) 15.07.2009
SELECT CONVERT(varchar(100), GETDATE(), 105) 15-07-2009
SELECT CONVERT(varchar(100), GETDATE(), 106) 15 07 2009
SELECT CONVERT(varchar(100), GETDATE(), 107) 07 15, 2009
SELECT CONVERT(varchar(100), GETDATE(), 108) 16:06:26
SELECT CONVERT(varchar(100), GETDATE(), 109) 07 15 2009 4:06:26:513PM
SELECT CONVERT(varchar(100), GETDATE(), 110) 07-15-2009
SELECT CONVERT(varchar(100), GETDATE(), 111) 2009/07/15
SELECT CONVERT(varchar(100), GETDATE(), 112) 20090715
SELECT CONVERT(varchar(100), GETDATE(), 113) 15 07 2009 16:06:26:513
SELECT CONVERT(varchar(100), GETDATE(), 114) 16:06:26:513
SELECT CONVERT(varchar(100), GETDATE(), 120) 2009-07-15 16:06:26
SELECT CONVERT(varchar(100), GETDATE(), 121) 2009-07-15 16:06:26.513
SELECT CONVERT(varchar(100), GETDATE(), 126) 2009-07-15T16:06:26.513
SELECT CONVERT(varchar(100), GETDATE(), 130) 23 ??? 1430 4:06:26:513PM
SELECT CONVERT(varchar(100), GETDATE(), 131) 23/07/1430 4:06:26:513PM
```

对于简单类型转换，CONVERT()函数和CAST()函数的功能相同，只是语法不同。CAST()函数一般更容易使用，其功能也更简单。CONVERT()函数的优点是可以格式化日期和数值，它需要两个参数：第1个是目标数据类型，第2个是源数据。以下的两个例子和上一节的例子类似：

GetDate( ) 

返回系统目前的日期与时间

#### COALESCE()

主流数据库系统都支持COALESCE()函数，这个函数主要用来进行空值处理，其参数格式如下： 
COALESCE ( expression,value1,value2……,valuen) 
COALESCE()函数的第一个参数expression为待检测的表达式，而其后的参数个数不定。
COALESCE()函数将会返回包括expression在内的所有参数中的第一个非空表达式。

COALESCE()函数可以用来完成几乎所有的空值处理，不过在很多数据库系统中都提供了它的简化版，这些简化版中只接受两个变量，其参数格式如下： 
**MYSQL:** 
 IFNULL(expression,value) 
**MSSQLServer:** 
 ISNULL(expression,value) 
**Oracle:** 
 NVL(expression,value) 

这几个函数的功能和COALESCE(expression,value)是等价的。

#### DECIMAL数据类型

MySQL `DECIMAL`数据类型用于在数据库中存储精确的数值。我们经常将`DECIMAL`数据类型用于保留准确精确度的列，例如会计系统中的货币数据。

要定义数据类型为`DECIMAL`的列，请使用以下语法：

```
column_name ``DECIMAL``(P,D);
```

在上面的语法中：

- `P`是表示有效数字数的精度。 `P`范围为`1〜65`。
- `D`是表示小数点后的位数。 `D`的范围是`0`~`30`。MySQL要求`D`小于或等于(`<=`)`P`。

`DECIMAL(P，D)`表示列可以存储`D`位小数的`P`位数。十进制列的实际范围取决于精度和刻度。

与INT数据类型一样，`DECIMAL`类型也具有`UNSIGNED`和`ZEROFILL`属性。 如果使用`UNSIGNED`属性，则`DECIMAL UNSIGNED`的列将不接受负值。

如果使用`ZEROFILL`，MySQL将把显示值填充到`0`以显示由列定义指定的宽度。 另外，如果我们对`DECIMAL`列使用`ZERO FILL`，MySQL将自动将`UNSIGNED`属性添加到列。

以下示例使用`DECIMAL`数据类型定义的一个叫作`amount`的列。

```
amount ``DECIMAL``(6,2);
```

在此示例中，`amount`列最多可以存储`6`位数字，小数位数为`2`位; 因此，`amount`列的范围是从`-9999.99`到`9999.99`。

## mysql的日期类函数



### 按照时间查询方法总结

#### MYSQL:

##### **今天**

```mysql
select * from 表名 where to_days(时间字段名) = to_days(now());
```

 

##### **昨天**



```mysql
SELECT * FROM 表名 WHERE TO_DAYS( NOW( ) ) - TO_DAYS( 时间字段名) = 1
```

 

##### **近7天**

```mysql
SELECT * FROM 表名 where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(时间字段名)
```

##### **近30天**

```mysql
SELECT * FROM 表名 where DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(时间字段名)
```

##### **本月**

```mysql
SELECT * FROM 表名 WHERE DATE_FORMAT( 时间字段名, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' )
```

##### **上一月**

```mysql
SELECT * FROM 表名 WHERE PERIOD_DIFF( date_format( now( ) , '%Y%m' ) , date_format( 时间字段名, '%Y%m' ) ) =1
```

 

##### **查询本季度数据**

```mysql
select * from 表名 where QUARTER(create_date)=QUARTER(now());
```

 

##### **查询上季度数据**

```mysql
select * from 表名 where QUARTER(create_date)=QUARTER(DATE_SUB(now(),interval 1 QUARTER));
```

 

##### **查询本年数据**

```mysql
select * from 表名 where YEAR(create_date)=YEAR(NOW());
```

 

##### **查询上年数据**

```mysql
select * from 表名 where year(create_date)=year(date_sub(now(),interval 1 year));
```

 

##### **查询当前这周的数据**

```mysql
SELECT name,submittime FROM 表名 WHERE YEARWEEK(date_format(submittime,'%Y-%m-%d')) = YEARWEEK(now());
```

 

##### **查询上周的数据**

```mysql
SELECT name,submittime FROM 表名 WHERE YEARWEEK(date_format(submittime,'%Y-%m-%d')) = YEARWEEK(now())-1;
```

 

##### 查询上个月的数据

```mysql
select name,submittime from 表名 where date_format(submittime,'%Y-%m')=date_format(DATE_SUB(curdate(), INTERVAL 1 MONTH),'%Y-%m')

select * from 表名 where DATE_FORMAT(pudate,'%Y%m') = DATE_FORMAT(CURDATE(),'%Y%m') ; 

select * from 表名 where WEEKOFYEAR(FROM_UNIXTIME(pudate,'%y-%m-%d')) = WEEKOFYEAR(now()) 

select * from 表名 where MONTH(FROM_UNIXTIME(pudate,'%y-%m-%d')) = MONTH(now()) 

select * from 表名 where YEAR(FROM_UNIXTIME(pudate,'%y-%m-%d')) = YEAR(now()) and MONTH(FROM_UNIXTIME(pudate,'%y-%m-%d')) = MONTH(now()) 

select * from 表名 where pudate between  上月最后一天  and 下月第一天 
```

 

##### **查询当前月份的数据** 

```mysql
select name,submittime from 表名 where date_format(submittime,'%Y-%m')=date_format(now(),'%Y-%m')
```

 

##### **查询距离当前现在6个月的数据**

```mysql
select name,submittime from 表名 where submittime between date_sub(now(),interval 6 month) and now()
```

### **ORACLE:**

**ORACLE没有提供直接获取年、周的函数，提供了一些函数，需要进行计算**

**to_char()和to_date基本等价于JAVA中的SimpleDateFormat函数**

**trunc()函数为指定元素而截去的日期值。**

​    trunc（sysdate,'yyyy'） --返回当年第一天。

　　trunc（sysdate,'mm'） --返回当月第一天。

　　trunc（sysdate,'d'） --返回当前星期的第一天。

　　trunc（sysdate,'dd'）--返回当前年月日

 trunc()也可以对数字精度进行操作，trunc(number,length),number用于完整取精度的数字，length代表精度长度，默认为0；trunc()取精度时不进行四舍五入

   select trunc(123.458,0) from dual --123
   select trunc(123.458,1) from dual --123.4
   select trunc(123.458,-1) from dual --120
   select trunc(123.458,-4) from dual --0
   select trunc(123.458,4) from dual --123.4580

add_months(times,months)表示在time时间之上进行月份操作，months为正整数表示之后，正负数表示之前



##### **1、DAYOFWEEK(date)**

```mysql
SELECT DAYOFWEEK(‘2016-01-16') ``SELECT DAYOFWEEK(‘2016-01-16 00:00:00')
-> 7 (表示返回日期``date``是星期几，记住：星期天=1，星期一=2， ... 星期六=7)
```

##### **2、WEEKDAY(date)**

```ORACLE
SELECT WEEKDAY(‘2016-01-16') ``SELECT WEEKDAY(‘2016-01-16 00:00:00')
```

 

```mysql
-> 5 (表示返回``date``是在一周中的序号，西方日历中通常一周的开始是星期天，并且以0开始计数，所以，记住：0=星期一，1=星期二， ... 5=星期六)
```

##### **3、DAYOFMONTH(date)**

```mysql
SELECT DAYOFMONTH(‘2016-01-16') ``SELECT DAYOFMONTH(‘2016-01-16 00:00:00')
-> 16 (表示返回``date``是当月的第几天，1号就返回1，... ,31号就返回31)
```

##### **4、DAYOFYEAR(date)**

```mysql
SELECT DAYOFYEAR(‘2016-03-31') ``SELECT DAYOFYEAR(‘2016-03-31 00:00:00')
-> 91 (表示返回``date``是当年的第几天，01.01返回1，... ,12.31就返回365)
```

##### **5、MONTH(date)**

```mysql
SELECT MONTH(‘2016-01-16') ``SELECT MONTH(‘2016-01-16 00:00:00')
-> 1 (表示返回``date``是当年的第几月，1月就返回1，... ,12月就返回12)
```

##### **6、DAYNAME(date)**

```mysql
SELECT DAYNAME(‘2016-01-16') ``SELECT DAYNAME(‘2016-01-16 00:00:00')
-> Saturday (表示返回``date``是周几的英文全称名字)
```

##### **7、MONTHNAME(date)**

```mysql
SELECT MONTHNAME(‘2016-01-16') ``SELECT MONTHNAME(‘2016-01-16 00:00:00')
-> January (表示返回``date``的是当年第几月的英文名字)
```

##### **8、QUARTER(date)**

```mysql
SELECT QUARTER(‘2016-01-16') ``SELECT QUARTER(‘2016-01-16 00:00:00')
-> 1 (表示返回``date``的是当年的第几个季度，返回1,2,3,4)
```

##### **9、WEEK(date，index)**

```mysql
SELECT WEEK(‘2016-01-03') ``SELECT WEEK(‘2016-01-03', 0) ``SELECT WEEK(‘2016-01-03', 1)
-> 1 (该函数返回``date``在一年当中的第几周，``date``(01.03)是周日，默认是以为周日作为一周的第一天，函数在此处返回1可以有两种理解：1、第一周返回0，第二周返回1，.... ,2、以当年的完整周开始计数，第一周返回1，第二周返回2，... ，最后一周返回53)``-> 1 (week()默认index就是0. 所以结果同上)``-> 0 (当index为1时，表示一周的第一天是周一，所以，4号周一才是第二周的开始日)
```

##### **10、YEAR(date)**

```mysql
SELECT YEAR(‘70-01-16') ``SELECT YEAR(‘2070-01-16') ``SELECT YEAR(‘69-01-16 00:00:00')
-> 1970 (表示返回``date``的4位数年份)``-> 2070 ``-> 1969
```

要注意的是：如果年份只有两位数，那么自动补全的机制是以默认时间1970.01.01为界限的，>= 70 的补全 19，< 70 的补全 20

##### **11、HOUR(time)**

```mysql
SELECT HOUR(‘11:22:33') ``SELECT HOUR(‘2016-01-16 11:22:33')
-> 11``-> 11
```

返回该date或者time的hour值，值范围（0-23）

##### **12、MINUTE(time)**

```mysql
SELECT MINUTE(‘11:22:33') ``SELECT MINUTE(‘2016-01-16 11:44:33')
-> 22``-> 44
```

返回该time的minute值，值范围（0-59）

##### **13、SECOND(time)**

```mysql
SELECT SECOND(‘11:22:33') ``SELECT SECOND(‘2016-01-16 11:44:22')
-> 33``-> 22
```

返回该time的minute值，值范围（0-59）

##### **14、PERIOD_ADD(month，add)**

```mysql
SELECT PERIOD_ADD(1601,2) ``SELECT PERIOD_ADD(191602,3) ``SELECT PERIOD_ADD(191602,-3)
-> 201603``-> 191605``-> 191511
```

该函数返回对month做增减的操作结果，month的格式为yyMM或者yyyyMM,返回的都是yyyyMM格式的结果，add可以传负值

##### **15、PERIOD_DIFF(monthStart，monthEnd)**

```mysql
SELECT PERIOD_DIFF(1601,1603) ``SELECT PERIOD_DIFF(191602,191607) ``SELECT PERIOD_DIFF(1916-02,1916-07) ``SELECT PERIOD_DIFF(1602,9002)
-> -2``-> -5``-> 5``-> 312
```

该函数返回monthStart - monthEnd的间隔月数

##### **16、DATE_ADD(date，INTERVAL number type)，同 ADDDATE()**

```mysql
SELECT DATE_ADD(“2015-12-31 23:59:59”,INTERVAL 1 SECOND) ``SELECT DATE_ADD(“2015-12-31 23:59:59”,INTERVAL 1 DAY) ``SELECT DATE_ADD(“2015-12-31 23:59:59”,INTERVAL “1:1” MINUTE_SECOND) ``SELECT DATE_ADD(“2016-01-01 00:00:00”,INTERVAL “-1 10” DAY_HOUR)
-> 2016-01-01 00:00:00``-> 2016-01-01 23:59:59``-> 2016-01-01 00:01:00``-> 2015-12-30 14:00:00
```

DATE_ADD()和ADDDATE()返回对date操作的结果

1、date的格式可以是“15-12-31”，可以是“15-12-31 23:59:59”,也可以是“2015-12-31 23:59:59”，如果参数date是date格式，则返回date格式结果，如果参数date是datetime格式，则返回datetime格式结果

2、type格式：
  SECOND 秒 SECONDS
  MINUTE 分钟 MINUTES
  HOUR 时间 HOURS
  DAY 天 DAYS
  MONTH 月 MONTHS
  YEAR 年 YEARS
  MINUTE_SECOND 分钟和秒 "MINUTES:SECONDS"
  HOUR_MINUTE 小时和分钟 "HOURS:MINUTES"
  DAY_HOUR 天和小时 "DAYS HOURS"
  YEAR_MONTH 年和月 "YEARS-MONTHS"
  HOUR_SECOND 小时, 分钟， "HOURS:MINUTES:SECONDS"
  DAY_MINUTE 天, 小时, 分钟 "DAYS HOURS:MINUTES"
  DAY_SECOND 天, 小时, 分钟, 秒 "DAYS HOURS:MINUTES:SECONDS"


3、另外，如果不用函数，也可以考虑用操作符“+”，“-”，例子如下：

```mysql
SELECT “2016-01-01” - INTERVAL 1 SECOND ``SELECT “2016-01-01” - INTERVAL 1 DAY ``SELECT ‘2016-12-31 23:59:59' + INTERVAL 1 SECOND ``SELECT ‘2016-12-31 23:59:59' + INTERVAL “1:1” MINUTE_SECOND
```

返回结果：

```mysql
-> 2015-12-31 23:59:59``-> 2015-12-31``-> 2017-01-01 00:00:00``-> 2017-01-01 00:01:00
```

##### **17、DATE_SUB(date，INTERVAL number type)，同 SUBDATE()**

用法和DATE_ADD()与ADDDATE()类似，一个是加，一个是减，用时参照16点，具体用法请参考DATE_ADD()与ADDDATE()。

##### **18、TO_DAYS(date)**

```mysql
SELECT TO_DAYS(‘2016-01-16') ``SELECT TO_DAYS(‘20160116') ``SELECT TO_DAYS(‘160116')
-> 736344``-> 736344``-> 736344
```

返回西元0年至日期date是总共多少天

##### **19、FROM_DAYS(date)**

```mysql
SELECT FROM_DAYS(367)
-> 0001-01-02
```

返回西元0年至今多少天的DATE值

##### **20、DATE_FORMAT(date，format)：根据参数对date进行格式化。**

```mysql
SELECT DATE_FORMAT(‘2016-01-16 22:23:00``','``%W %M %Y') ``SELECT DATE_FORMAT(‘2016-01-16 22:23:00``','``%D %y %a %d %m %b %j') ``SELECT DATE_FORMAT(‘2016-01-16 22:23:00``','``%H %k %I %r %T %S %w') ``SELECT DATE_FORMAT(‘2016-01-16 22:23:00``','``%Y-%m-%d %H:%i:%s')
-> Saturday January 2016``-> 16th 16 Sat 16 01 Jan 016``-> 22 22 10 10:23:00 PM 22:23:00 00 6``-> 2016-01-16 22:23:00
```

format的格式都列出来：

  %M 月名字(January……December)
  %W 星期名字(Sunday……Saturday)
  %D 有英语前缀的月份的日期(1st, 2nd, 3rd, 等等。）
  %Y 年, 数字, 4 位
  %y 年, 数字, 2 位
  %a 缩写的星期名字(Sun……Sat)
  %d 月份中的天数, 数字(00……31)
  %e 月份中的天数, 数字(0……31)
  %m 月, 数字(01……12)
  %c 月, 数字(1……12)
  %b 缩写的月份名字(Jan……Dec)
  %j 一年中的天数(001……366)
  %H 小时(00……23)
  %k 小时(0……23)
  %h 小时(01……12)
  %I 小时(01……12)
  %l 小时(1……12)
  %i 分钟, 数字(00……59)
  %r 时间,12 小时(hh:mm:ss [AP]M)
  %T 时间,24 小时(hh:mm:ss)
  %S 秒(00……59)
  %s 秒(00……59)
  %p AM或PM
  %w 一个星期中的天数(0=Sunday ……6=Saturday ）
  %U 星期(0……52), 这里星期天是星期的第一天
  %u 星期(0……52), 这里星期一是星期的第一天
  %% 字符% )

**TIME_FORMAT(time,format)：**
具体用法和DATE_FORMAT()类似,但TIME_FORMAT只处理小时、分钟和秒(其余符号产生一个NULL值或0)

##### **21、获取系统当前日期**

```mysql
SELECT CURDATE() ``SELECT CURRENT_DATE()
-> 2016-01-16``-> 2016-01-16
```

##### **22、获取系统当前时间**

```mysql
SELECT CURTIME() ``SELECT CURRENT_TIME()
-> 17:44:22``-> 17:44:22
```

##### **23、NOW()，SYSDATE()，CURRENT_TIMESTAMP()，LOCALTIME()：获取系统当前日期和时间**

```mysql
SELECT NOW() ``SELECT SYSDATE() ``SELECT CURRENT_TIMESTAMP() ``SELECT CURRENT_TIMESTAMP ``SELECT LOCALTIME() ``SELECT LOCALTIME
-> 2016-01-16 17:44:41``-> 2016-01-16 17:44:41``-> 2016-01-16 17:44:41``-> 2016-01-16 17:44:41``-> 2016-01-16 17:44:41``-> 2016-01-16 17:44:41
```

##### **24、UNIX_TIMESTAMP（date）：获取时间戳**

```mysql
SELECT UNIX_TIMESTAMP() ``SELECT UNIX_TIMESTAMP(‘2016-01-16') ``SELECT UNIX_TIMESTAMP(‘2016-01-16 23:59:59')
-> 1452937627``-> 1452873600``-> 1452959999
```

**25、FROM_UNIXTIME(unix_timestamp,format)：把时间戳转化成日期时间**

```mysql
SELECT FROM_UNIXTIME(1452959999) ``SELECT FROM_UNIXTIME(1452959999,``'%Y-%m-%d %H:%i:%s'``)
-> 2016-01-16 23:59:59``-> 2016-01-16 23:59:59
```

##### **26、SEC_TO_TIME(seconds)：把秒数转化成时间**

```mysql
SELECT SEC_TO_TIME(2378)
-> 00:39:38
```

##### **27、TIME_TO_SEC(time)：把时间转化成秒数**

```mysql
SELECT TIME_TO_SEC(‘22:23:00')
-> 2378
```

##### **28、ADDTIME(time，times)：把times加到time上**

```mysql
SELECT ADDTIME(“2015-12-31 23:59:59”,``'01:01:01'``)
-> 2016-01-01 01:01:00
```

##### **29、CONVERT_TZ(date,from_tz ,to_tz )：转换时区**

```mysql
SELECT CONVERT_TZ(‘2004-01-01 12:00:00``','``+00:00``','``+10:00')
-> 2004-01-01 22:00:00
```

##### **30、STR_TO_DATE(date，format )：将字符串转成format格式的日期时间**

```mysql
SELECT STR_TO_DATE(‘2015-01-01``', ‘%Y-%m-%d'``)
-> 2015-01-01
```

##### **31、LAST_DAY(date )：获取date当月最后一天的日期**

```mysql
SELECT LAST_DAY(SYSDATE()) ``SELECT LAST_DAY(‘2015-02-02') ``SELECT LAST_DAY(‘2015-02-02 00:22:33')
-> 2016-01-31``-> 2015-02-28``-> 2015-02-28
```

##### **32、MAKEDATE(year ,dayofyear )：根据参数（年份，第多少天）获取日期**

```mysql
SELECT MAKEDATE(2015 ,32)
-> 2015-02-01
```

##### **33、 MAKETIME(hour ,minute ,second )：根据参数（时，分，秒）获取时间**

```mysql
SELECT MAKETIME(12 ,23 ,34 )
-> 12:23:34
```

##### **34、YEARWEEK(date)：获取日期的年和周**

```mysql
SELECT YEARWEEK(SYSDATE()) ``SELECT YEARWEEK(‘2015-01-10') ``SELECT YEARWEEK(‘2015-01-10',1)
-> 201602``-> 201501``-> 201502
```

##### **35、WEEKOFYEAR(date)：获取当日是当年的第几周**

```mysql
SELECT WEEKOFYEAR(SYSDATE()) ``SELECT WEEKOFYEAR(‘2015-01-10')
-> 2``-> 2
```

-> 2
-> 2

**mysql中常用的几种时间格式转换函数整理如下**

**1，from_unixtime(timestamp, format)：**

timestamp为int型时间，如14290450779；format为转换的格式，包含格式如下：

%M 月名字(January……December) 
%W 星期名字(Sunday……Saturday) 
%D 有英语前缀的月份的日期(1st, 2nd, 3rd, 等等。） 
%Y 年, 数字, 4 位 
%y 年, 数字, 2 位 
%a 缩写的星期名字(Sun……Sat) 
%d 月份中的天数, 数字(00……31) 
%e 月份中的天数, 数字(0……31) 
%m 月, 数字(01……12) 
%c 月, 数字(1……12) 
%b 缩写的月份名字(Jan……Dec) 
%j 一年中的天数(001……366) 
%H 小时(00……23) 
%k 小时(0……23) 
%h 小时(01……12) 
%I 小时(01……12) 
%l 小时(1……12) 
%i 分钟, 数字(00……59) 
%r 时间,12 小时(hh:mm:ss [AP]M) 
%T 时间,24 小时(hh:mm:ss) 
%S 秒(00……59) 
%s 秒(00……59) 
%p AM或PM 
%w 一个星期中的天数(0=Sunday ……6=Saturday ） 
%U 星期(0……52), 这里星期天是星期的第一天 
%u 星期(0……52), 这里星期一是星期的第一 

**2，unix_timestamp(date)：**

作用与from_unixtime()刚好相反，前者是把unix时间戳转换为可读的时间，而unix_timestamp()是把可读的时间转换为unix时间戳，这在对datetime存储的时间进行排序时会用到。如unix_timestamp('2009-08-06 10:10:40')，得到1249524739。

如果unix_timestamp()不传参数，则调用now()函数自动取当前时间。

**3，date_format(date, format)：**

date_format()是将date或datetime类型值转换为任意的时间格式。比如常见的应用场景，某表有一个字段是更新时间，存储的是datetime类型，但前台展示时只需要显示年月日（xxxx-xx-xx），这个时候就可以用date_format(date,'%Y-%m-%d ')处理，而不需要在结果集中用程序循环处理。



### SQL中字段保留两位小数：

使用 Round() 函数，如 **Round(number,2)** ，其中参数2表示保留两位有效数字，四舍五入到两位小数
例如 **ROUND**(3.141592653, 2) 结果为3.14；
使用 **cast(number as decimal(10,2))** 实现转换，其中参数2表示保留两位有效数字 例如**cast(3.1415 as decimal(10,2))** 结果为3.14；
备注：CAST与CONVERT都可以执行数据类型转换，且都默认实现了四舍五入
如果目标表的字段是decimal(10,4)型的，从源表查数据：select round(field，2) from sourcetable;sql查询的结果字段的小数位可能是2位，但是该数据插入目标表，字段的小数位数就是4位后面2位以0补充；
select decimal(field,10,2) from sourcetable;该数据插入目标表，字段的小数位就是2位

SQL中两个字段相除并加上%

    select decimal(field1/field2*100,10,2) || '%' from tablename;
    select round(field1/field2*100,2) || '%' from tablename;
    select concat(round(field1/field2*100,2) ,'%') from tablename;
