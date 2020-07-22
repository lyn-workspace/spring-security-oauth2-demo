# spring-security-oauth2-demo

#### 介绍
使用Spring-Security-Oauth2 完成认证授权的demo 


## 我们采用不同的分支来表示不同的认证授权方式

### session分支
> 基于session 进行认证
基于Session认证方式的流程是，用户认证成功后，在服务端生成用户相关的数据保存在session(当前会话)，而发 给客户端的 sesssion_id 存放到 cookie 中，这样用客户端请求时带上 session_id 就可以验证服务器端是否存在 session 数据，以此完成用户的合法校验。当用户退出系统或session过期销毁时,客户端的session_id也就无效了。 

