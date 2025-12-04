# 📦 二手物品交易平台 (SecondHand Transaction Platform)

## 📋 项目简介

这是一个基于 **Java Servlet + JSP + MySQL** 开发的二手物品交易平台，用户可以注册、登录、发布物品、搜索物品、查看和管理自己发布的物品。

**开发时间**: 2025年12月  
**最后更新**: 2025年12月4日

---

## 🏗️ 系统架构

### 项目结构

```
secondHandTransaction/
├── src/
│   ├── main/
│   │   ├── java/com/boda/secondhandtransaction/
│   │   │   ├── Controller/          # Servlet 控制层
│   │   │   │   ├── LoginServlet.java       # 登录处理
│   │   │   │   ├── RegisterServlet.java    # 注册处理
│   │   │   │   ├── LogoutServlet.java      # 退出登录
│   │   │   │   ├── ItemServlet.java        # 发布物品
│   │   │   │   ├── SearchItem.java         # 搜索物品
│   │   │   │   ├── itemDetail.java         # 查看我的物品
│   │   │   │   ├── DeleteItemServlet.java  # 删除物品
│   │   │   │   └── UpdateItemServlet.java  # 修改物品
│   │   │   ├── Services/            # 业务逻辑层
│   │   │   │   ├── UserService.java        # 用户业务逻辑(含密码加密)
│   │   │   │   └── ItemsService.java       # 物品业务逻辑
│   │   │   ├── DAO/                 # 数据访问层
│   │   │   │   ├── DBUtil.java             # 数据库连接工具
│   │   │   │   ├── BaseDao.java            # 通用数据库操作
│   │   │   │   └── ResultSetHandler.java   # 结果集处理接口
│   │   │   └── POJO/                # 实体类
│   │   │       ├── User.java               # 用户实体
│   │   │       └── Item.java               # 物品实体
│   │   ├── resources/
│   │   │   └── schema.sql                  # 数据库建表脚本
│   │   └── webapp/                  # Web 前端页面
│   │       ├── login.jsp                   # 登录页面
│   │       ├── register.jsp                # 注册页面
│   │       ├── secondhand.jsp              # 首页(物品列表)
│   │       ├── item.jsp                    # 发布物品页面
│   │       ├── itemDetail.jsp              # 我的物品管理
│   │       ├── updateItem.jsp              # 修改物品页面
│   │       └── WEB-INF/
│   │           └── web.xml
│   └── test/                        # 测试代码
├── pom.xml                          # Maven 配置文件
└── README.md                        # 项目说明文档
```

---

## 🗄️ 数据库设计

### 数据库名称
```sql
items
```

### 数据库字符集
```
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci
```

---

### 表结构

#### 1️⃣ users 表(用户表)

| 字段 | 类型 | 约束 | 说明 |
|------|------|------|------|
| id | INT | PRIMARY KEY, AUTO_INCREMENT | 用户ID |
| username | VARCHAR(50) | NOT NULL, UNIQUE | 用户名(唯一) |
| password | VARCHAR(255) | NOT NULL | 密码(SHA-256加密后存储) |

**建表语句**:
```sql
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID(主键)',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名(唯一)',
    password VARCHAR(255) NOT NULL COMMENT '密码（存储加密后的密文）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 COMMENT='用户表';
```

---

#### 2️⃣ items 表(物品表)

| 字段 | 类型 | 约束 | 说明 |
|------|------|------|------|
| id | INT | PRIMARY KEY, AUTO_INCREMENT | 物品ID |
| name | VARCHAR(100) | NOT NULL | 物品名称 |
| description | TEXT | - | 物品描述 |
| price | DECIMAL(10, 2) | NOT NULL | 价格 |
| sellerId | INT | NOT NULL, FOREIGN KEY | 卖家ID(关联users.id) |

**建表语句**:
```sql
CREATE TABLE IF NOT EXISTS items (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '物品ID(主键)',
    name VARCHAR(100) NOT NULL COMMENT '物品名称',
    description TEXT COMMENT '物品描述',
    price DECIMAL(10, 2) NOT NULL COMMENT '价格',
    sellerId INT NOT NULL COMMENT '卖家ID(外键)',
    FOREIGN KEY (sellerId) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 COMMENT='二手物品表';
```

**外键约束说明**:
- `ON DELETE CASCADE`: 当删除用户时，该用户发布的所有物品也会被自动删除

## 🧪 测试账号

### 预设测试账号列表

| 用户名 | 密码 | 密码(SHA-256加密后) | 说明 |
|--------|------|-------------------|------|
| admin | 111111 | bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a | 测试账号1 |
| admin2 | 111111 | bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a | 测试账号2 |

## 📖 功能使用说明

### 1️⃣ 用户功能

#### 注册流程:
1. 访问注册页面
2. 填写用户名、密码、确认密码
3. 点击"注册"按钮
4. 系统自动使用 SHA-256 加密密码
5. 注册成功后跳转到登录页面

#### 登录流程:
1. 访问登录页面
2. 输入用户名和密码
3. 系统对输入密码进行 SHA-256 加密
4. 与数据库中的加密密码对比
5. 验证成功后跳转到首页

---

### 2️⃣ 物品功能

#### 发布物品流程:
1. 登录后点击"发布物品"
2. 填写物品名称、描述、价格
3. 点击"发布"按钮
4. 系统自动关联当前登录用户的ID作为 `sellerId`
5. 发布成功后返回首页

#### 搜索物品流程:
1. 在首页输入关键词
2. 点击"搜索"按钮
3. 系统在物品名称和描述中模糊搜索
4. 显示匹配的物品列表

#### 管理我的物品:
1. 点击"查看我发布的物品"
2. 显示当前用户发布的所有物品
3. 可以点击"修改"编辑物品信息
4. 可以点击"删除"删除物品
