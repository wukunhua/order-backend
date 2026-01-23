# 点单系统后端

基于 Spring Boot + MyBatis + MySQL 的后端服务

## 技术栈

- Java 17
- Spring Boot 3.2.0
- MyBatis 3.0.3
- MySQL 8.0
- JWT
- Lombok

## 项目结构

```
src/main/java/com/ordering/system/
├── controller/       # 控制器层
├── service/          # 业务逻辑层
├── mapper/           # 数据访问层
├── entity/           # 实体类
├── dto/              # 数据传输对象
├── config/           # 配置类
└── common/           # 公共类

src/main/resources/
├── mapper/           # MyBatis XML映射文件
└── application.yml   # 配置文件
```

## 快速开始

### 1. 修改配置

编辑 `src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    username: root
    password: your_password

wechat:
  miniapp:
    app-id: your_app_id
    app-secret: your_app_secret

jwt:
  secret: your-secret-key
```

### 2. 编译运行

```bash
mvn clean install
mvn spring-boot:run
```

## API文档

详见项目根目录的 `DEPLOYMENT.md`

## 开发说明

### 添加新的接口

1. 在 `entity` 包中创建实体类
2. 在 `mapper` 包中创建Mapper接口和XML文件
3. 在 `service` 包中创建Service类
4. 在 `controller` 包中创建Controller类

### 数据库操作

使用MyBatis注解或XML映射文件进行数据库操作

## 注意事项

- 所有时间字段使用 `LocalDateTime` 类型
- 使用Lombok简化代码
- 统一使用 `Result` 类封装返回结果
