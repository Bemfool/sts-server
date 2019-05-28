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

**[注意] 更新了如何处理收到的数据为List类型** 



## 请求格式

`url地址` 功能【方法：POST/GET】

* 传入（Json）

* 传出（Json -> CustomResponse）

  * 结果（Result（Boolean status, String cause））
  * 内容（Object）

**`url`中若有`{输入参数}`表示将参数输入在地址当中。 **

[注意] 传入和接收倒要进行JSON的转换。* 

*[注意] 暂时不可使用的功能将持续更新，请关注更新并及时测试。*

### 股票数据

`/stock/all` 获取所有股票【GET】

- 传入：null
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：List<Stock>

`stock/one` 获取单只股票信息【GET】

- 传入：stock_code
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：Stock

`/stock/update`更新股票数据【POST】

- 传入：Stock 新的股票数据（stock_code不能变）
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null

`stock/update_list`更新一系列股票数据【POST】

- 传入：List<Stock> 新的股票数据（stock_code不能变）
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null

`stock/update_list/state/{newState` 更新状态【POST】

- 传入：List<Stock> 待修改的股票数据
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null

`stock/update_list/limit/{newLimit}`更新涨跌幅【POST】

- 传入：List<Stock> 待修改的股票数据
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null

### 指数信息

`index/all`  取所有指数 【GET】

- 传入：null
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：List<Index>

`index/one` 取单只指数【GET】

- 传入：index_code
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：Index

`index/update`   【POST】

- 传入：Index 新的指数数据（index_code不能变）
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null

`index/update_list`   【POST】

- 传入：List<Index> 新的指数数据（index_code不能变）
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null


### 管理员客户端

`/admin/login` 登陆 【POST】

- 传入：AdminAccount
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：AdminAccount

`admin/logout` 登出【POST】

- 传入：null
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null

#### 证券账户

`/securities/new/personal` 添加个人账户【POST】

- 传入：PersonalAccount
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null

`/securities/new/corporate` 添加法人账户【POST】

- 传入：CorporateAccount
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null

`/securities/personal` 获取个人账户【GET】

- 传入：String id_no
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：PersonalAccount

`/securities/corporate` 获取法人账户【GET】

- 传入：String register_no
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：CorporateAccount

`/securities/fund_connected/{securitiesId}` 获取相关联的资金账户【GET】

- 传入：null
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：List< Integer>

`/securities/update/personal/state/{idNo}/{newState}` 修改个人账户状态【POST】

- 传入：null
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null

`/securities/update/corporate/state/{registerNo}/{newState}` 修改法人账户状态【POST】

- 传入：null
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null

`/securities/alter/personal/{oldSecuritiesId}/{newSecuritiesId}` 替换个人账户的ID，并将旧的放入已删除表【POST】

- 传入：null
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null

`/securities/delete/person/{idNo}` 删除个人账户，并将旧的账户放入已删除表【POST】

- 传入：null
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null

`/securities/delete/corporate/{registerNo}` 删除法人账户，并将旧的账户放入已删除表【POST】

- 传入：null
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：null
  - 

#### 资金账户

`/fund/new` 创建新账户 【GET】

- 传入：FundAccount
- 传出：
  - 结果：
    - true
    - false+原因
  - 内容：int FundId

`/fund/update/balance` 更新利息【POST】**【暂时无法使用】**

- 传入：无
- 传出：
  - 结果：
    - true
    - false+原因

`/fund/update/password/{fundId}/{newPassword}` 更新密码 【POST】

- 传入：null
- 传出：
  - 结果：
    - true
    - false+原因

`/fund/check/state/{fundId}` 检查状态【GET】

- 传入：null
- 传出：
  - 结果：
    - true：可用
    - false：不可用
  - 内容：
    - Boolean 状态 

`/fund/change/state/{fundId}` 账户状态状态改变【POST】

- 传入：null
- 传出：
  - 结果：
    - true：完成修改
    - false+原因

`/fund/{fundId}` 账户信息查询【GET】

- 传入：null
- 传出：
  - 结果：
    - true
    - false+原因
  - 内容：FundAccount

`/fund/delete/{fundId}`删除账户【POST】

- 传入：null
- 传出：
  - 结果：
    - true
    - false+原因

`/fund/transfer/`新建交易记录并更新余额【POST】

- 传入：TransactionLog （不用设置action_id）
- 传出：
  - 结果：
    - true
    - false+原因

#### 内部管理

`/admin/update/password` 修改密码【POST】

- 传入：String newPassword

- 传出：

  - 结果：
    - true
    - false + 原因
  - 内容：null




### 用户客户端

`/client/login` 登陆 【POST】

- 传入：FundAccount
- 传出：
  - 结果：
    - true
    - false + 原因
  - 内容：FundAccount



## TODO

数据池：Druid（加密+监控管理）

增强鲁棒性。

日志记录。