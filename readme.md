## 打包部署
`mvn常用命令`
`mvn clean`
`mvn install 安装，并打到本地仓库`
`mvn package 打包`
`mvn package -DskipTests 打包跳过单元测试` 
`mvn compile 编译`
`java -jar auth-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev --name=zsq --age=2 启动springboot 并添加自定义的参数` 

##spring security
### httpBasic() 开启httpBasic认证模式，默认会有一个用户名-admin,密码由spring启动生成：Using generated security password: f56f3a79-91b2-4670-9ab2-5a87216c460a。
也可以自定义:在配置文件中添加：
#### spring.security.user.name=admin
#### spring.security.user.password=admin
这种认知方式不安全，最终可以被破解；请求头中有密文(Authorization: Basic YWRtaW46YWRtaW4=)，这种密码可以通过base64解密出来。
### http.csrf().disable() 不设置，访问会报403
### PasswordEncoder
明文：123456
密文：$2a$10$9nTdB7BCjdt8XFZpFa6yPONwhKs3yZd3.v1ADqxuz4eyx52y9Iq3C
密码组成部分：
$2a   BCrypt 算法的版本
$10   算法的强度
$9nTdB7BCjdt8XFZpFa6yPONwhKs3yZd3 随机生成的盐
v1ADqxuz4eyx52y9Iq3C              哈希值
对于同一原始密码，每次加密以后的hash值都是不一样的
### 自定义登录接口和返回
源码： UsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter
ProviderManager AuthenticationManager DaoAuthenticationProvider <-- AbstractUserDetailsAuthenticationProvider <-- AuthenticationProvider
登录成功以后的处理：
private AuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
登录失败以后的处理：
private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

### formLogin 登录模式的相关配置
http.csrf().disable()
                .formLogin()              // 开启表单登录模式
                .loginPage("/login.html")// 一旦用户的请求没有权限就跳转到该登录页面
                .loginProcessingUrl("/login") // 登录表单中action的地址，即处理认证请求的路径
                .passwordParameter("password") // form表单密码输入框的参数名
                .usernameParameter("username") // form表单用户名输入框的参数名
                .defaultSuccessUrl("/")// 登录成功后默认跳转的路径
                .failureUrl("/login.html") // 登录失败以后的跳转路径
### Spring Security session 创建策略
* always: 如果当前请求没有对应的session存在，创建一个session。
* ifRequired（默认）：在需要使用session时才创建session。
* never：Spring Security将永远不会主动创建session，但是如果session在当前应用中已经存在，它将使用该session。
* stateless：Spring Security不会创建或是session。适合于接口型的无状态应用(前后端分离)，该方式节省内存资源。

.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  session创建的策略
会话超时配置：
server.servlet.session.timeout=15m
spring.session.timeout=15m

Spring Security的配置：
.and().sessionManagement().invalidSessionUrl("xxx") 无效session的跳转路径



### session的保护机制
* migrationSession保护方式（默认）。即对于同一个SESSIONID用户，每次登陆验证将创建一个新的
HTTP session，旧的HTTP session将无效，将旧的session的属性复制到新的session上面。
* 设置为"none"时，原始会话不会无效。
* 设置为"newSession"后,将创建一个干净的会话，而不会复制旧会话中的任何属性。
.and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().migrateSession()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(customExpiredSessionStrategy); //sesscion超时的处理策略



### cookie 的安全
httpOnly:如果为true，则浏览器脚本将无法访问cookie。
secure：如果为true，则仅通过https连接发送cookie，http无法携带cookie。
server.servlet.session.cookie.http-only=true
server.servlet.session.cookie.secure=true
### 限制最大用户登录数量
 http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(false);
 * true 表示已经登录就不允许再次登录
 * false 表示允许再次登录但是之前的登录账户会被踢下线
### rememberme 功能
* 保存在内存中
* 持久化到数据库中
 PersistentTokenRepository、JdbcTokenRepositoryImpl
### logout的默认四种行为
* 当前session失效，即logout的核心需求，session失效就是访问权限的回收
* 删除当前用户的remember-me"记住我"功能信息 
* clear 清除当前的SecurityContext
* 重定义到登录页面，loginPage配置项指定的页面

* LogoutSuccessHandler 个性化退出功能
  (1)编码实现个性化退出功能。
  (2)注意logoutSuccessUrl不要与LogoutSuccessHandler一起使用，否则LogoutSuccessHandler将失效。
* 个性化配置  
(1)http.logout().logoutUrl("/signout").logoutSuccessUrl("/aftersignout.html").deleteCookies("JSESSIONID");
通过指定logoutUrl配置改变退出请求的默认路径，当然html退出按钮的请求URL也要修改。
(2)通过logoutSuccessUrl配置，来显示指定退出之后的跳转页面。
(3)还可以使用deleteCookies删除指定的cookie,参数为cookie的名称。

### 验证码
* 单体应用自己存储在session
* 共享session存储验证码：负载均衡多个应用实例

### JWT token


## @RestController和@Controller
@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
1) 如果只是使用@RestController，则Controller中的方法无法返回jsp页面，或者html，配置的视图解析器InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。
2) 如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
   如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
1.使用@Controller 注解，在对应的方法上，视图解析器可以解析return 的jsp,html页面，并且跳转到相应页面
若返回json等内容到页面，则需要加@ResponseBody注解