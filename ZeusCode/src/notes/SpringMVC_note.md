# SpringMVC

## 1.简介

### 1.SpringMVC是什么？

SpringMVC是spring的子项目，是spring为表述层开发提供的一整套完备的解决方案；

三层架构分为：表述层，业务逻辑层，数据访问层，表述层表示前台页面和后台的servlet

### 2.SpringMVC特点

1.与IOC容器等基础设施无缝对接

2.给予原生的Servlet，通过了功能强大的前端控置器DispatcherServlet，对请求和响应进行统一处理

3.表述层各细分领域需要解决的问题全方位覆盖，提供全面解决方案

4.代码清新简洁，大幅度提升开发效率

5.内部组件化程度高，可插拔式组件即插即用，想要什么功能配置相应组件即可

6.性能卓越，尤其适合现代大型、超大型互联网项目要求

## 2.HelloWorld

配置web.xml

注册springmvc的前端控制器DispatchServlet

## springMVC的处理请求方式

浏览器发送请求，若请求地址符合前端控制器的url-pattern，该请求就会被前端控制器DispatchServlet处理。前端控制器会读取SpringMVC的核心配置文件，通过扫描组件找到控制器，将请求地址和控制器中@RequestMapping注解的value属性值进行匹配，若匹配成功，该注解锁标识的控制器方法就是处理请求的方法，处理请求的方法需要返回一个字符串类型的视图名称，该视图名称会被视图解析器解析，加上前缀和后缀组成视图的路径，通过Thymeleaf对视图进行渲染，最终转发到该视图所对应的页面。
