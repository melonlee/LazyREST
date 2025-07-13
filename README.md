# LazyREST 🚀

> 一个功能完善的 Spring Boot REST API 脚手架，集成了现代化的最佳实践和丰富的功能特性。

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-8+-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com)

## ✨ 特性

### 🏗️ 现代化技术栈
- **Spring Boot 2.7.x** - 基于 Java 8 的企业级框架
- **Spring Data JPA** - 数据持久化解决方案
- **Spring Security** - JWT 认证授权
- **Spring Cache** - Redis 缓存支持
- **Spring Actuator** - 应用监控
- **Spring AOP** - 面向切面编程

### 🔐 安全框架
- **JWT 认证** - 无状态 Token 认证机制
- **RBAC 权限模型** - 角色基础访问控制
- **密码加密** - BCrypt + 盐值加密
- **接口保护** - 方法级别的安全控制
- **CORS 配置** - 跨域资源共享
- **XSS 防护** - 跨站脚本攻击防护
- **SQL 注入防护** - 数据库安全保护

### 🗄️ 数据库支持
- **MySQL 8.0+** - 主流关系型数据库
- **Druid 连接池** - 阿里巴巴高性能数据库连接池
- **自动建表** - 基于 JPA 的数据库迁移
- **性能优化** - 针对高并发场景的配置优化
- **审计日志** - 数据变更追踪

### ⚡ 缓存系统
- **Redis 集成** - 分布式缓存解决方案
- **灵活配置** - 多种缓存策略
- **缓存抽象** - 统一的缓存接口
- **缓存管理** - 缓存监控和管理端点

### 📚 API 文档
- **OpenAPI 3.0** - 标准化 API 文档
- **Swagger UI** - 交互式 API 测试界面
- **详细示例** - 完整的请求响应示例
- **版本控制** - API 版本管理支持

### 📊 审计日志
- **操作审计** - 基于 AOP 的操作记录
- **全面日志** - SLF4J 统一日志框架
- **性能监控** - 接口响应时间统计
- **用户行为** - 用户操作轨迹追踪
- **访问日志** - API 访问记录

### 🛠️ 工具类库
- **日期时间** - 基于 Java 8 Time API
- **字符串处理** - 丰富的字符串操作工具
- **加密工具** - AES、RSA、MD5、SHA256 等
- **验证工具** - 正则表达式验证器
- **文件处理** - 文件上传下载工具
- **JSON 处理** - JSON 序列化反序列化

## 📋 环境要求

- ☕ **JDK 1.8+**
- 🔧 **Maven 3.6+**
- 🐬 **MySQL 8.0+**
- 🔴 **Redis 6.0+**
- 📦 **Git**

## 🚀 快速开始

### 1️⃣ 克隆项目
```bash
git clone https://github.com/yourusername/LazyREST.git
cd LazyREST
```

### 2️⃣ 配置数据库
在 `application.yml` 中配置数据库连接：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/lazy_rest?useUnicode=true&characterEncoding=utf8&useSSL=false
    username: your_username
    password: your_password
```

### 3️⃣ 配置 Redis
在 `application.yml` 中配置 Redis：
```yaml
spring:
  redis:
    host: localhost
    port: 6379
    database: 0
```

### 4️⃣ 配置 JWT 密钥
在 `application.yml` 中配置 JWT：
```yaml
jwt:
  secret: your-secret-key-here-must-be-at-least-256-bits
  expiration: 86400
```

### 5️⃣ 构建项目
```bash
mvn clean package
```

### 6️⃣ 运行应用
```bash
java -jar target/lazy-rest-1.0.0.jar
```

### 7️⃣ 访问 Swagger UI
```
http://localhost:8080/api/swagger-ui.html
```

## 📁 项目结构

```
src/main/java/com/lazy/rest/
├── 📂 common/                   # 通用组件
│   ├── 📂 audit/               # 审计框架
│   │   ├── 📄 AuditLog.java    # 审计日志实体
│   │   ├── 📄 Auditable.java   # 审计注解
│   │   └── 📄 AuditAspect.java # 审计切面
│   ├── 📂 exception/           # 全局异常处理
│   ├── 📂 response/            # 统一响应处理
│   └── 📂 util/                # 通用工具类
│       ├── 📄 DateUtils.java   # 日期时间工具
│       ├── 📄 StringUtils.java # 字符串工具
│       ├── 📄 EncryptUtils.java# 加密工具
│       └── 📄 ValidatorUtils.java# 验证工具
├── 📂 config/                  # 配置类
│   ├── 📄 SecurityConfig.java  # 安全配置
│   └── 📄 OpenApiConfig.java   # Swagger 配置
├── 📂 security/                # 安全框架
│   ├── 📂 jwt/                 # JWT 实现
│   ├── 📂 model/               # 安全领域模型
│   └── 📂 service/             # 安全服务
└── 📄 LazyRestApplication.java
```

## 🔧 API 使用

### 🔐 用户认证

#### 注册新用户
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "user123",
  "password": "Password123",
  "email": "user@example.com"
}
```

#### 用户登录获取 Token
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "user123",
  "password": "Password123"
}
```

#### 使用 Token 访问受保护资源
```http
GET /api/secure/resource
Authorization: Bearer your_token_here
```

### 👥 权限控制

基于角色的访问控制包含以下角色：
- **ADMIN** 🔑 - 管理员，拥有所有权限
- **USER** 👤 - 普通用户，访问用户相关接口
- **GUEST** 👻 - 访客，仅能访问公开接口

接口权限控制示例：
```java
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/admin/users")
public List<User> getAllUsers() {
    // 仅管理员可访问
}

@PreAuthorize("hasRole('USER')")
@GetMapping("/user/profile")
public User getProfile() {
    // 用户可访问
}
```

## 🛠️ 工具类使用

### 📅 日期工具
```java
// 格式化日期
String dateStr = DateUtils.formatDateTime(new Date());

// 解析日期
Date date = DateUtils.parseDateTime("2023-10-01 12:00:00");

// 日期计算
Date futureDate = DateUtils.addDays(new Date(), 7);
```

### 📝 字符串工具
```java
// 生成随机字符串
String random = StringUtils.randomString(10);

// 脱敏处理
String maskedEmail = StringUtils.maskEmail("user@example.com"); // u***@example.com
```

### 🔒 加密工具
```java
// 生成盐值
String salt = EncryptUtils.generateSalt();

// 密码加密
String encrypted = EncryptUtils.encryptPassword("password123", salt);
```

## 📊 审计日志

使用 `@Auditable` 注解记录操作：
```java
@Auditable(operation = "创建用户")
@PostMapping("/users")
public User createUser(@RequestBody User user) {
    // 方法实现
}
```

## 💡 最佳实践

### 🔐 安全建议
- ✅ 始终验证输入数据
- ✅ 生产环境使用 HTTPS
- ✅ 实施接口限流
- ✅ 定期更新安全补丁

### ⚡ 性能优化
- ✅ 合理使用缓存
- ✅ 优化数据库查询
- ✅ 实现分页查询
- ✅ 监控应用指标

### 👨‍💻 开发规范
- ✅ 遵循编码规范
- ✅ 编写单元测试
- ✅ 完善代码注释
- ✅ 使用有意义的提交信息

## ⚙️ 配置说明

关键配置项及其说明：

```yaml
server:
  port: 8080                 # 服务器端口
  servlet:
    context-path: /api       # API 基础路径

spring:
  datasource:
    # 数据库配置
  jpa:
    # JPA/Hibernate 配置
  redis:
    # Redis 配置

jwt:
  secret: your-secret        # JWT 签名密钥
  expiration: 86400          # Token 过期时间（秒）

springdoc:
  # Swagger/OpenAPI 配置
```

## 🤝 贡献指南

1. 🍴 Fork 本仓库
2. 🌟 创建特性分支 (`git checkout -b feature/amazing-feature`)
3. 📝 提交更改 (`git commit -m 'Add amazing feature'`)
4. 🚀 推送到分支 (`git push origin feature/amazing-feature`)
5. 🔄 创建 Pull Request

## 🐛 常见问题

### 数据库连接问题
- ✅ 确认 MySQL 服务正在运行
- ✅ 检查连接凭据
- ✅ 确保数据库存在

### Redis 连接问题
- ✅ 确认 Redis 服务正在运行
- ✅ 检查 Redis 连接设置
- ✅ 确保 Redis 配置正确

### JWT 相关问题
- ✅ 验证密钥配置
- ✅ 检查 Token 过期设置
- ✅ 确保正确使用 Token

## 📄 许可证

本项目基于 Apache License 2.0 许可证开源 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 🙏 致谢

- Spring Boot 和 Spring Framework 团队
- OpenAPI/Swagger 团队
- 所有为本项目做出贡献的开发者

---

⭐ 如果这个项目对你有帮助，请给个 Star 支持一下！

💬 有问题或建议？欢迎提交 Issue 或 Pull Request！



