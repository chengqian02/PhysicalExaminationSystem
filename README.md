## 健康体检结果查询平台的设计与实现

### 使用方法

- 下载

```
git clone https://github.com/chengqian02/PhysicalExaminationSystem.git
```

- 配置idea的maven
- 使用idea打开pom.xml文件
- 安装mysql5.7版本数据，并安装数据库文件
- 在application.yml文件中配置数据库地址为自己的数据库

```xml
url: jdbc:mysql://localhost:3306/llu_cat?useUnicode=true&useSSL=false&characterEncoding=utf-8
```

### 系统介绍

#### 管理员角色

![image-20220728164432811](https://github.com/chengqian02/PhysicalExaminationSystem/raw/master/image/readme1.png)

#### 普通用户角色

![image-20220728164220140](https://github.com/chengqian02/PhysicalExaminationSystem/raw/master/image/readme3.png)