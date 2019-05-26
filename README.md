# Stock Trading System - Server

该项目为B组股票交易系统的子模块，作为管理员客户端、用户客户端的服务器。

架构：Spring Boot 2.0.0

数据库：MySQL

项目管理：Maven 

缓存数据库：Redis（Session）

数据包格式：Gson 2.8.5 **（客户端需要安装并导入相应JAR包）**



## 客户端的使用

见`client-example`

将其中的工具类`Utils`放到项目内，使用其中的`doHttp`函数，具体使用见其中的`Main`函数。



## 请求格式

`url地址` 功能

* 传入（Json）

* 传出（Json -> CustomResponse）

  * 结果（Result（Boolean status, String cause））
  * 内容（Object）

*[注意] 传入和接收倒要进行JSON的转换。* 

*[注意] 暂时不可使用的功能将持续更新，请关注更新并及时测试。*



### 股票数据

`/stock/all` 获取所有股票

- 传入：null
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：List<Stock>

`stock/one` 获取单只股票信息

- 传入：stock_code
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：Stock

`/stock/update`更新股票数据

- 传入：Stock 新的股票数据（stock_code不能变）
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null

`stock/update_list`更新一系列股票数据

- 传入：List<Stock> 新的股票数据（stock_code不能变）
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null

### 指数信息

`index/all`  取所有指数 

- 传入：null
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：List<Index>

`index/one` 取单只指数

- 传入：index_code
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：Index

`index/update`   

- 传入：Index 新的指数数据（index_code不能变）
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null

`index/update_list`   

- 传入：List<Index> 新的指数数据（index_code不能变）
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null


### 管理员客户端

`/admin/login` 登陆 

- 传入：AdminAccount
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：AdminAccount

#### 证券账户

`/securities/new/personal` 

- 传入：PersonalAccount
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null

`/securities/new/corporate` 

- 传入：CorporateAccount
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null

#### 资金账户

`/fund/new` 创建新账户 **【暂时不可使用】**

`/fund/balance/update` 更新利息 **【暂时不可使用】**

`/fund/password/update` 更新密码 **【暂时不可使用】**

`/fund/state/check` 检查状态 **【暂时不可使用】**

#### 内部管理

`/admin/update/password` 修改密码

- 传入：String newPassword

- 传出：

  - 结果：
    - true
    - false + 原因
  - 内容：null


### 用户客户端

`/client/login` 登陆 

- 传入：FundAccount
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：FundAccount



## TODO

数据池：Druid（加密+监控管理）

增强鲁棒性。