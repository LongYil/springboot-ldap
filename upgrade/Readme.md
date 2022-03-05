## 0.0.1 使用内存存储数据
#### 版本功能介绍
本案例使用内存存储存储数据，并使用 resources/users.ldif 作为数据
存储文件，使用这种方式，数据是无法持久化的，只能存储在内存中，这种方式
只能做测试使用。

#### 使用docker启动一个openldap

docker run --detach -p 1389:1389 -p 1636:1636 --name openldap  --env LDAP_ADMIN_USERNAME=admin   --env LDAP_ADMIN_PASSWORD=Hc@Cloud01   --env LDAP_USERS=customuser   --env LDAP_PASSWORDS=Hc@Cloud01   --env  LDAP_ROOT=dc=springframework,dc=org  bitnami/openldap:latest