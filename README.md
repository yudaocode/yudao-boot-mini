# 芋道快速开发平台（yudao-boot-mini 精简版）

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.18-brightgreen.svg)
![Vue](https://img.shields.io/badge/Vue-3.2-brightgreen.svg)
![License](https://img.shields.io/github/license/YunaiV/ruoyi-vue-pro)
![GitHub stars](https://img.shields.io/github/stars/yudaocode/yudao-boot-mini?style=social)

**🚀 基于Spring Boot + Vue的快速开发脚手架，专注于企业级应用开发**

**严肃声明：现在、未来都不会有商业版本，所有代码全部开源！**

</div>

---

## 📋 目录

- [🐶 新手必读](#-新手必读)
- [🐰 版本说明](#-版本说明) 
- [🐯 平台简介](#-平台简介)
- [🐳 项目关系](#-项目关系)
- [😎 开源协议](#-开源协议)
- [🐼 内置功能](#-内置功能)
- [🐨 技术栈](#-技术栈)
- [🐷 演示图](#-演示图)
- [🤝 项目外包](#-项目外包)

---

**💡 如果这个项目让你有所收获，请给个 ⭐ Star 关注，这是对我们最好的鼓励与支持！**

## 🐶 新手必读

### 🌐 在线演示

| 版本 | 地址 | 账号密码 |
|------|------|----------|
| Vue3 + Element Plus | [http://dashboard-vue3.yudao.iocoder.cn](http://dashboard-vue3.yudao.iocoder.cn) | admin/admin123 |
| Vue3 + Vben(Ant Design Vue) | [http://dashboard-vben.yudao.iocoder.cn](http://dashboard-vben.yudao.iocoder.cn) | admin/admin123 |
| Vue2 + Element UI | [http://dashboard.yudao.iocoder.cn](http://dashboard.yudao.iocoder.cn) | admin/admin123 |

### 📚 文档资源

- 📖 [快速启动文档](https://doc.iocoder.cn/quick-start/)
- 🎥 [视频教程](https://doc.iocoder.cn/video/)
- 📋 [迁移文档](https://doc.iocoder.cn/migrate-module/) - 5-10分钟完成完整版到精简版迁移

## 🐰 版本说明

| 版本类型 | JDK 8 + Spring Boot 2.7 | JDK 17/21 + Spring Boot 3.2 |
|----------|---------------------------|------------------------------|
| **完整版** [ruoyi-vue-pro](https://gitee.com/zhijiantianya/ruoyi-vue-pro) | [`master`](https://gitee.com/zhijiantianya/ruoyi-vue-pro/tree/master/) 分支 | [`master-jdk17`](https://gitee.com/zhijiantianya/ruoyi-vue-pro/tree/master-jdk17/) 分支 |
| **精简版** [yudao-boot-mini](https://gitee.com/yudaocode/yudao-boot-mini) | [`master`](https://gitee.com/yudaocode/yudao-boot-mini/tree/master/) 分支 | [`master-jdk17`](https://gitee.com/yudaocode/yudao-boot-mini/tree/master-jdk17/) 分支 |

### 版本区别

- **🔥 完整版**：包含系统功能、基础设施、会员中心、数据报表、工作流程、商城系统、微信公众号、CRM、ERP 等功能
- **⚡ 精简版**：只包含系统功能、基础设施功能，适合快速上手和定制开发

> 💡 **迁移提示**：可参考 [《迁移文档》](https://doc.iocoder.cn/migrate-module/)，只需 5-10 分钟即可将完整版按需迁移到精简版

## 🐯 平台简介

**芋道**，以开发者为中心，打造中国第一流的快速开发平台，全部开源，个人与企业可 100% 免费使用。

> 📝 **反馈渠道**：如有任何问题或功能需求，欢迎在 [Issues](https://github.com/yudaocode/yudao-boot-mini/issues) 中提给我们  
> 🌟 **支持我们**：给项目点个 Star 吧，这对我们真的很重要！

### 🏗️ 系统架构

<div align="center">

![架构图](/.image/common/ruoyi-vue-pro-architecture.png)

</div>

### 💻 技术选型

| 分类 | 技术栈 | 说明 |
|------|--------|------|
| **后端架构** | Spring Boot 多模块 + MySQL + MyBatis Plus + Redis + Redisson | JDK 8/17/21 多版本支持 |
| **前端架构** | Vue2/Vue3 + Element UI/Plus + Vben(Ant Design Vue) | 多种UI框架可选 |
| **移动端** | uni-app | 一份代码，多端适配（APP、小程序、H5） |
| **数据库** | MySQL、Oracle、PostgreSQL、SQL Server、MariaDB、达梦、TiDB | 多数据库支持 |
| **消息队列** | Event、Redis、RabbitMQ、Kafka、RocketMQ | 多种MQ可选 |
| **权限认证** | Spring Security + Token + Redis | 多终端、多用户认证，支持SSO |

### ✨ 核心特性

- 🚀 **高效开发**：代码生成器一键生成前后端代码、SQL脚本、接口文档
- 🔐 **权限管理**：动态权限菜单，按钮级权限控制，Redis缓存提升性能  
- 🏢 **多租户**：SaaS多租户支持，可自定义每个租户权限
- 📊 **工作流**：基于Flowable，支持动态表单、在线设计、会签/或签
- 💬 **实时通信**：Spring WebSocket + Token身份校验，支持集群
- 💰 **支付集成**：支付宝、微信支付与退款，多种支付方式
- 📱 **三方登录**：微信小程序、公众号、企业微信、钉钉等
- ☁️ **云服务**：阿里云、腾讯云短信/存储服务集成
- 📈 **报表大屏**：拖拽式报表设计器、数据大屏设计器

## 🐳 项目关系

<div align="center">

![架构演进](/.image/common/yudao-roadmap.png)

</div>

> 🔗 **对比参考**：查看 [国产开源项目对比](https://www.yuque.com/xiatian-bsgny/lm0ec1/wqf8mn) 了解更多项目功能对比

### 🖥️ 后端项目

| 项目 | 描述 | Stars |
|------|------|-------|
| [ruoyi-vue-pro](https://gitee.com/zhijiantianya/ruoyi-vue-pro) | 基于 Spring Boot 多模块架构 | [![Gitee star](https://gitee.com/zhijiantianya/ruoyi-vue-pro/badge/star.svg?theme=white)](https://gitee.com/zhijiantianya/ruoyi-vue-pro) [![GitHub stars](https://img.shields.io/github/stars/YunaiV/ruoyi-vue-pro.svg?style=social&label=Stars)](https://github.com/YunaiV/ruoyi-vue-pro) |
| [yudao-cloud](https://gitee.com/zhijiantianya/yudao-cloud) | 基于 Spring Cloud 微服务架构 | [![Gitee star](https://gitee.com/zhijiantianya/yudao-cloud/badge/star.svg?theme=white)](https://gitee.com/zhijiantianya/yudao-cloud) [![GitHub stars](https://img.shields.io/github/stars/YunaiV/yudao-cloud.svg?style=social&label=Stars)](https://github.com/YunaiV/yudao-cloud) |
| [SpringBoot-Labs](https://gitee.com/yudaocode/SpringBoot-Labs) | Spring Boot & Cloud 学习专栏 | [![Gitee star](https://gitee.com/yudaocode/SpringBoot-Labs/badge/star.svg?theme=white)](https://gitee.com/zhijiantianya/yudao-cloud) [![GitHub stars](https://img.shields.io/github/stars/yudaocode/SpringBoot-Labs.svg?style=social&label=Stars)](https://github.com/yudaocode/SpringBoot-Labs) |

### 🎨 前端项目

| 项目 | 描述 | Stars |
|------|------|-------|
| [yudao-ui-admin-vue3](https://gitee.com/yudaocode/yudao-ui-admin-vue3) | Vue3 + Element Plus 管理后台 | [![Gitee star](https://gitee.com/yudaocode/yudao-ui-admin-vue3/badge/star.svg?theme=white)](https://gitee.com/yudaocode/yudao-ui-admin-vue3) [![GitHub stars](https://img.shields.io/github/stars/yudaocode/yudao-ui-admin-vue3.svg?style=social&label=Stars)](https://github.com/yudaocode/yudao-ui-admin-vue3) |
| [yudao-ui-admin-vben](https://gitee.com/yudaocode/yudao-ui-admin-vben) | Vue3 + Vben(Ant Design Vue) 管理后台 | [![Gitee star](https://gitee.com/yudaocode/yudao-ui-admin-vben/badge/star.svg?theme=white)](https://gitee.com/yudaocode/yudao-ui-admin-vben) [![GitHub stars](https://img.shields.io/github/stars/yudaocode/yudao-ui-admin-vben.svg?style=social&label=Stars)](https://github.com/yudaocode/yudao-ui-admin-vben) |
| [yudao-ui-admin-vue2](https://gitee.com/yudaocode/yudao-ui-admin-vue2) | Vue2 + Element UI 管理后台 | [![Gitee star](https://gitee.com/yudaocode/yudao-ui-admin-vue2/badge/star.svg?theme=white)](https://gitee.com/yudaocode/yudao-ui-admin-vue2) [![GitHub stars](https://img.shields.io/github/stars/yudaocode/yudao-ui-admin-vue2.svg?style=social&label=Stars)](https://github.com/yudaocode/yudao-ui-admin-vue2) |
| [yudao-mall-uniapp](https://gitee.com/yudaocode/yudao-mall-uniapp) | uni-app 商城小程序 | [![Gitee star](https://gitee.com/yudaocode/yudao-mall-uniapp/badge/star.svg?theme=white)](https://gitee.com/yudaocode/yudao-mall-uniapp) [![GitHub stars](https://img.shields.io/github/stars/yudaocode/yudao-mall-uniapp.svg?style=social&label=Stars)](https://github.com/yudaocode/yudao-mall-uniapp) |
| [yudao-ui-admin-uniapp](https://gitee.com/yudaocode/yudao-ui-admin-uniapp) | uni-app 管理后台移动端 | [![Gitee star](https://gitee.com/yudaocode/yudao-ui-admin-uniapp/badge/star.svg?theme=white)](https://gitee.com/yudaocode/yudao-ui-admin-uniapp) [![GitHub stars](https://img.shields.io/github/stars/yudaocode/yudao-ui-admin-uniapp.svg?style=social&label=Stars)](https://github.com/yudaocode/yudao-ui-admin-uniapp) |
| [yudao-ui-go-view](https://gitee.com/yudaocode/yudao-ui-go-view) | Vue3 + Naive UI 大屏报表 | [![Gitee star](https://gitee.com/yudaocode/yudao-ui-go-view/badge/star.svg?theme=white)](https://gitee.com/yudaocode/yudao-ui-go-view) [![GitHub stars](https://img.shields.io/github/stars/yudaocode/yudao-ui-go-view.svg?style=social&label=Stars)](https://github.com/yudaocode/yudao-ui-go-view) |

## 😎 开源协议

### 🆓 为什么选择我们？

<div align="center">

![开源项目对比](/.image/common/project-vs.png)

</div>

✅ **更宽松的协议**：采用 [MIT License](https://gitee.com/zhijiantianya/ruoyi-vue-pro/blob/master/LICENSE) 开源协议，比 Apache 2.0 更宽松，个人与企业可 100% 免费使用，无需保留作者、Copyright 信息

✅ **完全开源**：代码全部开源，不像其他项目只开源部分代码，让你完全了解整个项目架构设计。[查看对比 →](https://www.yuque.com/xiatian-bsgny/lm0ec1/wqf8mn)

✅ **代码质量**：代码整洁、架构清晰，遵循《阿里巴巴 Java 开发手册》规范，113,770 行 Java 代码，42,462 行详细注释

## 🤝 项目外包

我们也是接外包滴，如果你有项目想要外包，可以微信联系【**Aix9975**】。

团队包含专业的项目经理、架构师、前端工程师、后端工程师、测试工程师、运维工程师，可以提供全流程的外包服务。

项目可以是商城、SCRM 系统、OA 系统、物流系统、ERP 系统、CMS 系统、HIS 系统、支付系统、IM 聊天、微信公众号、微信小程序等等。

## 🐼 内置功能

<div align="center">

![功能分层](/.image/common/ruoyi-vue-pro-biz.png)

</div>

### 📦 功能模块

| 模块类型 | 模块说明 | 状态 |
|----------|----------|------|
| **🔧 通用模块（必选）** | 系统功能、基础设施 | ✅ 已集成 |
| **⚙️ 通用模块（可选）** | 工作流程、支付系统、数据报表、会员中心 | 🔄 按需集成 |
| **💼 业务系统（按需）** | ERP 系统、CRM 系统、商城系统、微信公众号、AI 大模型 | 📋 可选安装 |

> 💡 **基于 RuoYi-Vue 重构优化**：重构后端代码，美化前端界面  
> 🚀 **标记说明**：新增功能标记 🚀，重新实现功能标记 ⭐️  
> 🧪 **质量保证**：所有功能都通过**单元测试**保证高质量

### 🏛️ 系统功能

<details>
<summary>点击展开系统功能详情</summary>

| 功能模块 | 功能描述 | 特色标记 |
|----------|----------|----------|
| **用户管理** | 系统用户配置，支持组织架构 | - |
| **在线用户** | 活跃用户监控，支持手动踢下线 | ⭐️ |
| **角色管理** | 角色菜单权限分配，数据范围权限 | - |
| **菜单管理** | 系统菜单、操作权限、按钮权限配置 | - |
| **部门管理** | 组织机构管理，树结构数据权限 | - |
| **岗位管理** | 用户职务配置 | - |
| **租户管理** | SaaS 多租户功能 | 🚀 |
| **租户套餐** | 自定义租户权限套餐 | 🚀 |
| **字典管理** | 系统固定数据维护 | - |
| **短信管理** | 短信渠道、模板、日志管理 | 🚀 |
| **邮件管理** | 邮箱账号、模版、发送日志 | 🚀 |
| **站内信** | 系统消息通知 | 🚀 |
| **操作日志** | 系统操作记录，集成 Swagger | 🚀 |
| **登录日志** | 登录记录查询，包含异常日志 | ⭐️ |
| **错误码管理** | 在线修改错误提示 | 🚀 |
| **通知公告** | 系统公告信息发布 | - |
| **敏感词** | 系统敏感词配置，标签分组 | 🚀 |
| **应用管理** | SSO 单点登录应用管理 | 🚀 |
| **地区管理** | 城市信息，IP 地址定位 | 🚀 |

![系统功能](/.image/common/system-feature.png)

</details>

### 🔄 工作流程

<details>
<summary>点击展开工作流程详情</summary>

![工作流程](/.image/common/bpm-feature.png)

**基于 Flowable 构建，支持信创数据库，满足中国特色流程操作**

| BPMN 设计器 | 钉钉/飞书设计器 |
|-------------|----------------|
| ![BPMN设计器](/.image/工作流设计器-bpmn.jpg) | ![Simple设计器](/.image/工作流设计器-simple.jpg) |

> 💡 **双设计器优势**：轻量配置简单流程 + 复杂场景深度编排

**核心功能清单**

| 功能 | 描述 | 状态 |
|------|------|------|
| **SIMPLE 设计器** | 仿钉钉/飞书设计器，10分钟快速配置审批流程 | ✅ |
| **BPMN 设计器** | 基于BPMN标准，适配复杂业务场景 | ✅ |
| **会签/或签** | 多人审批，支持全部同意/任意一人通过 | ✅ |
| **依次审批** | 顺序审批，按顺序依次处理 | ✅ |
| **抄送机制** | 审批结果通知，智能排重 | ✅ |
| **驳回转办** | 退回任意节点，支持转办委派 | ✅ |
| **加签减签** | 动态增减审批人 | ✅ |
| **撤销终止** | 流程发起人撤销，管理员终止 | ✅ |
| **表单权限** | 拖拽配置表单，节点权限控制 | ✅ |
| **超时提醒** | 自动审批，多渠道提醒 | ✅ |
| **父子流程** | 同步/异步子流程支持 | ✅ |
| **条件分支** | 多种分支类型：排它、并行、包容、路由 | ✅ |
| **触发节点** | HTTP请求、数据操作触发 | ✅ |
| **延迟节点** | 定时等待处理 | ✅ |

</details>

### 💰 支付系统

| 功能 | 描述 |
|------|------|
| **应用信息** | 多渠道支付配置（支付宝、微信等） |
| **支付订单** | 支付订单查看管理 |
| **退款订单** | 退款订单处理 |
| **回调通知** | 支付回调业务通知 |
| **接入示例** | 支付功能实战示例 |

### 🛠️ 基础设施

<details>
<summary>点击展开基础设施详情</summary>

| 功能 | 描述 | 特色 |
|------|------|------|
| **代码生成** | 前后端代码生成，支持CRUD下载 | 🚀 |
| **系统接口** | Swagger自动生成API文档 | 🚀 |
| **数据库文档** | Screw自动生成数据库文档 | 🚀 |
| **表单构建** | 拖拽生成HTML代码 | - |
| **配置管理** | 动态参数配置 | 🚀 |
| **定时任务** | 在线任务调度 | ⭐️ |
| **文件服务** | 多存储支持（S3、本地、FTP等） | 🚀 |
| **WebSocket** | 实时通信示例 | 🚀 |
| **API 日志** | 访问日志、异常日志 | 🚀 |
| **数据库监控** | MySQL连接池监控 | - |
| **Redis 监控** | Redis使用情况监控 | - |
| **消息队列** | Redis消息队列实现 | 🚀 |
| **Java 监控** | Spring Boot Admin监控 | 🚀 |
| **链路追踪** | SkyWalking链路追踪 | 🚀 |
| **日志中心** | 分布式日志管理 | 🚀 |
| **服务保障** | 分布式锁、幂等、限流 | 🚀 |
| **单元测试** | JUnit + Mockito测试 | 🚀 |

![基础设施](/.image/common/infra-feature.png)

</details>

### 📊 数据报表

| 功能 | 描述 |
|------|------|
| **报表设计器** | 数据报表、图形报表、打印设计 |
| **大屏设计器** | 拖拽生成数据大屏，内置图表组件 |

### 📱 微信公众号

| 功能 | 描述 |
|------|------|
| **账号管理** | 多公众号接入配置 |
| **数据统计** | 用户增减、消息概况、接口分析 |
| **粉丝管理** | 粉丝列表、同步、打标签 |
| **消息管理** | 消息列表、主动回复 |
| **自动回复** | 关注回复、消息回复、关键字回复 |
| **标签管理** | 标签CRUD操作 |
| **菜单管理** | 自定义菜单、同步菜单 |
| **素材管理** | 图片、语音、视频素材管理 |
| **图文管理** | 草稿箱、发表记录 |

### 🛒 商城系统

> 🌐 **演示地址**：[https://doc.iocoder.cn/mall-preview/](https://doc.iocoder.cn/mall-preview/)

![商城功能图](/.image/common/mall-feature.png)

![商城预览图](/.image/common/mall-preview.png)

### 👥 会员中心

| 功能 | 描述 |
|------|------|
| **会员管理** | C端消费者管理 |
| **会员标签** | 标签CRUD操作 |
| **会员等级** | 等级管理、成长值 |
| **会员分组** | 用户画像、内容推送 |
| **积分签到** | 积分奖励、消费抵现 |

### 📈 ERP 系统

> 🌐 **演示地址**：[https://doc.iocoder.cn/erp-preview/](https://doc.iocoder.cn/erp-preview/)

![ERP功能图](/.image/common/erp-feature.png)

### 🤝 CRM 系统

> 🌐 **演示地址**：[https://doc.iocoder.cn/crm-preview/](https://doc.iocoder.cn/crm-preview/)

![CRM功能图](/.image/common/crm-feature.png)

### 🤖 AI 大模型

> 🌐 **演示地址**：[https://doc.iocoder.cn/ai-preview/](https://doc.iocoder.cn/ai-preview/)

![AI功能图](/.image/common/ai-feature.png)

![AI预览图](/.image/common/ai-preview.gif)

## 🐨 技术栈

### 🏗️ 模块架构

| 模块 | 说明 |
|------|------|
| `yudao-dependencies` | Maven 依赖版本管理 |
| `yudao-framework` | Java 框架拓展 |
| `yudao-server` | 管理后台 + 用户 APP 服务端 |
| `yudao-module-system` | 系统功能模块 |
| `yudao-module-member` | 会员中心模块 |
| `yudao-module-infra` | 基础设施模块 |
| `yudao-module-bpm` | 工作流程模块 |
| `yudao-module-pay` | 支付系统模块 |
| `yudao-module-mall` | 商城系统模块 |
| `yudao-module-erp` | ERP 系统模块 |
| `yudao-module-crm` | CRM 系统模块 |
| `yudao-module-ai` | AI 大模型模块 |
| `yudao-module-mp` | 微信公众号模块 |
| `yudao-module-report` | 大屏报表模块 |

### 🛠️ 核心技术

| 技术 | 版本 | 说明 | 学习资料 |
|------|------|------|----------|
| [Spring Boot](https://spring.io/projects/spring-boot) | 2.7.18 | 应用开发框架 | [📚 学习指南](https://github.com/YunaiV/SpringBoot-Labs) |
| [MySQL](https://www.mysql.com/cn/) | 5.7 / 8.0+ | 数据库服务器 | - |
| [MyBatis Plus](https://mp.baomidou.com/) | 3.5.7 | MyBatis 增强工具包 | [📚 文档](http://www.iocoder.cn/Spring-Boot/MyBatis/?yudao) |
| [Redis](https://redis.io/) | 5.0 / 6.0 / 7.0 | key-value 数据库 | - |
| [Spring Security](https://github.com/spring-projects/spring-security) | 5.7.11 | Spring 安全框架 | [📚 文档](http://www.iocoder.cn/Spring-Boot/Spring-Security/?yudao) |
| [Flowable](https://github.com/flowable/flowable-engine) | 6.8.0 | 工作流引擎 | [📚 文档](https://doc.iocoder.cn/bpm/) |

<details>
<summary>📋 查看完整技术栈列表</summary>

| 技术 | 版本 | 说明 | 学习资料 |
|------|------|------|----------|
| [Druid](https://github.com/alibaba/druid) | 1.2.23 | JDBC 连接池、监控组件 | [📚 文档](http://www.iocoder.cn/Spring-Boot/datasource-pool/?yudao) |
| [Dynamic Datasource](https://dynamic-datasource.com/) | 3.6.1 | 动态数据源 | [📚 文档](http://www.iocoder.cn/Spring-Boot/datasource-pool/?yudao) |
| [Redisson](https://github.com/redisson/redisson) | 3.32.0 | Redis 客户端 | [📚 文档](http://www.iocoder.cn/Spring-Boot/Redis/?yudao) |
| [Spring MVC](https://github.com/spring-projects/spring-framework/tree/master/spring-webmvc) | 5.3.24 | MVC 框架 | [📚 文档](http://www.iocoder.cn/SpringMVC/MVC/?yudao) |
| [Hibernate Validator](https://github.com/hibernate/hibernate-validator) | 6.2.5 | 参数校验组件 | [📚 文档](http://www.iocoder.cn/Spring-Boot/Validation/?yudao) |
| [Quartz](https://github.com/quartz-scheduler) | 2.3.2 | 任务调度组件 | [📚 文档](http://www.iocoder.cn/Spring-Boot/Job/?yudao) |
| [Springdoc](https://springdoc.org/) | 1.7.0 | Swagger 文档 | [📚 文档](http://www.iocoder.cn/Spring-Boot/Swagger/?yudao) |
| [SkyWalking](https://skywalking.apache.org/) | 8.12.0 | 分布式应用追踪系统 | [📚 文档](http://www.iocoder.cn/Spring-Boot/SkyWalking/?yudao) |
| [Spring Boot Admin](https://github.com/codecentric/spring-boot-admin) | 2.7.10 | Spring Boot 监控平台 | [📚 文档](http://www.iocoder.cn/Spring-Boot/Admin/?yudao) |
| [Jackson](https://github.com/FasterXML/jackson) | 2.13.5 | JSON 工具库 | - |
| [MapStruct](https://mapstruct.org/) | 1.6.3 | Java Bean 转换 | [📚 文档](http://www.iocoder.cn/Spring-Boot/MapStruct/?yudao) |
| [Lombok](https://projectlombok.org/) | 1.18.34 | 消除冗长的 Java 代码 | [📚 文档](http://www.iocoder.cn/Spring-Boot/Lombok/?yudao) |
| [JUnit](https://junit.org/junit5/) | 5.8.2 | Java 单元测试框架 | - |
| [Mockito](https://github.com/mockito/mockito) | 4.8.0 | Java Mock 框架 | - |

</details>

## 🐷 演示图

### 🏛️ 系统功能

<details>
<summary>点击展开系统功能演示图</summary>

| 模块 | 截图1 | 截图2 | 截图3 |
|------|-------|-------|-------|
| **登录 & 首页** | ![登录](/.image/登录.jpg) | ![首页](/.image/首页.jpg) | ![个人中心](/.image/个人中心.jpg) |
| **用户 & 应用** | ![用户管理](/.image/用户管理.jpg) | ![令牌管理](/.image/令牌管理.jpg) | ![应用管理](/.image/应用管理.jpg) |
| **租户 & 套餐** | ![租户管理](/.image/租户管理.jpg) | ![租户套餐](/.image/租户套餐.png) | - |
| **部门 & 岗位** | ![部门管理](/.image/部门管理.jpg) | ![岗位管理](/.image/岗位管理.jpg) | - |
| **菜单 & 角色** | ![菜单管理](/.image/菜单管理.jpg) | ![角色管理](/.image/角色管理.jpg) | - |
| **审计日志** | ![操作日志](/.image/操作日志.jpg) | ![登录日志](/.image/登录日志.jpg) | - |
| **短信功能** | ![短信渠道](/.image/短信渠道.jpg) | ![短信模板](/.image/短信模板.jpg) | ![短信日志](/.image/短信日志.jpg) |
| **字典 & 敏感词** | ![字典类型](/.image/字典类型.jpg) | ![字典数据](/.image/字典数据.jpg) | ![敏感词](/.image/敏感词.jpg) |
| **错误码 & 通知** | ![错误码管理](/.image/错误码管理.jpg) | ![通知公告](/.image/通知公告.jpg) | - |

</details>

### 🔄 工作流程

<details>
<summary>点击展开工作流程演示图</summary>

| 模块 | 截图1 | 截图2 | 截图3 |
|------|-------|-------|-------|
| **流程模型** | ![流程模型-列表](/.image/流程模型-列表.jpg) | ![流程模型-设计](/.image/流程模型-设计.jpg) | ![流程模型-定义](/.image/流程模型-定义.jpg) |
| **表单 & 分组** | ![流程表单](/.image/流程表单.jpg) | ![用户分组](/.image/用户分组.jpg) | - |
| **我的流程** | ![我的流程-列表](/.image/我的流程-列表.jpg) | ![我的流程-发起](/.image/我的流程-发起.jpg) | ![我的流程-详情](/.image/我的流程-详情.jpg) |
| **待办 & 已办** | ![任务列表-审批](/.image/任务列表-审批.jpg) | ![任务列表-待办](/.image/任务列表-待办.jpg) | ![任务列表-已办](/.image/任务列表-已办.jpg) |
| **OA 请假** | ![OA请假-列表](/.image/OA请假-列表.jpg) | ![OA请假-发起](/.image/OA请假-发起.jpg) | ![OA请假-详情](/.image/OA请假-详情.jpg) |

</details>

### 🛠️ 基础设施

<details>
<summary>点击展开基础设施演示图</summary>

| 模块 | 截图1 | 截图2 | 截图3 |
|------|-------|-------|-------|
| **代码生成** | ![代码生成](/.image/代码生成.jpg) | ![生成效果](/.image/生成效果.jpg) | - |
| **文档功能** | ![系统接口](/.image/系统接口.jpg) | ![数据库文档](/.image/数据库文档.jpg) | - |
| **文件 & 配置** | ![文件配置](/.image/文件配置.jpg) | ![文件管理](/.image/文件管理2.jpg) | ![配置管理](/.image/配置管理.jpg) |
| **定时任务** | ![定时任务](/.image/定时任务.jpg) | ![任务日志](/.image/任务日志.jpg) | - |
| **API 日志** | ![访问日志](/.image/访问日志.jpg) | ![错误日志](/.image/错误日志.jpg) | - |
| **数据库监控** | ![MySQL](/.image/MySQL.jpg) | ![Redis](/.image/Redis.jpg) | - |
| **监控平台** | ![Java监控](/.image/Java监控.jpg) | ![链路追踪](/.image/链路追踪.jpg) | ![日志中心](/.image/日志中心.jpg) |

</details>

### 💰 支付系统

<details>
<summary>点击展开支付系统演示图</summary>

| 模块 | 截图1 | 截图2 | 截图3 |
|------|-------|-------|-------|
| **商家 & 应用** | ![商户信息](/.image/商户信息.jpg) | ![应用信息-列表](/.image/应用信息-列表.jpg) | ![应用信息-编辑](/.image/应用信息-编辑.jpg) |
| **支付 & 退款** | ![支付订单](/.image/支付订单.jpg) | ![退款订单](/.image/退款订单.jpg) | - |

</details>

### 📊 数据报表

<details>
<summary>点击展开数据报表演示图</summary>

| 模块 | 截图1 | 截图2 | 截图3 |
|------|-------|-------|-------|
| **报表设计器** | ![数据报表](/.image/报表设计器-数据报表.jpg) | ![图形报表](/.image/报表设计器-图形报表.jpg) | ![打印设计](/.image/报表设计器-打印设计.jpg) |
| **大屏设计器** | ![大屏列表](/.image/大屏设计器-列表.jpg) | ![大屏预览](/.image/大屏设计器-预览.jpg) | ![大屏编辑](/.image/大屏设计器-编辑.jpg) |

</details>

### 📱 移动端（管理后台）

<details>
<summary>点击展开移动端演示图</summary>

| | | |
|:---:|:---:|:---:|
| ![移动端1](/.image/admin-uniapp/01.png) | ![移动端2](/.image/admin-uniapp/02.png) | ![移动端3](/.image/admin-uniapp/03.png) |
| ![移动端4](/.image/admin-uniapp/04.png) | ![移动端5](/.image/admin-uniapp/05.png) | ![移动端6](/.image/admin-uniapp/06.png) |
| ![移动端7](/.image/admin-uniapp/07.png) | ![移动端8](/.image/admin-uniapp/08.png) | ![移动端9](/.image/admin-uniapp/09.png) |

**功能说明**：目前已实现登录、我的、工作台、编辑资料、头像修改、密码修改、常见问题、关于我们等基础功能。

</details>

## 🤝 项目外包

我们提供专业的项目外包服务，如有需要可以微信联系 **Aix9975**。

### 👨‍💼 团队优势

- 🏗️ **专业团队**：项目经理、架构师、前端工程师、后端工程师、测试工程师、运维工程师
- 🔄 **全流程服务**：需求分析 → 架构设计 → 开发实现 → 测试部署 → 运维支持
- 💼 **项目类型**：商城、SCRM、OA、物流、ERP、CMS、HIS、支付、IM聊天、微信公众号/小程序等

---

<div align="center">

**🎯 芋道快速开发平台，让开发更简单！**

如果这个项目对您有帮助，请 ⭐ Star 支持我们！

[🏠 官网](https://doc.iocoder.cn) | [📖 文档](https://doc.iocoder.cn/quick-start/) | [🎥 视频](https://doc.iocoder.cn/video/) | [💬 交流群](https://doc.iocoder.cn/qun/)

</div>
