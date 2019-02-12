1.励志个人完成整个项目，Android IOS PC 后台  -- 2019.1.24
    今日任务：
        1.完成项目的上传功能 -- ok
        2.学习python
            1.切片 与 倒数切片 [类似截取函数]
            2.列表生成器 [将操作的步骤放在for ... in 前面]
            3.生成器 ，迭代器-- ！！
            4.高阶函数 map(f,Iterable) reduce(f,Iterable)

    今日任务：2019.1.25
        1.搭建python后台开发框架 -- Django

2.今日任务：2019.1.28
    1.研究python的一些逻辑
    2.研究python的IDE -- Pycharm(了解 -- 安装)
        参考：https://blog.csdn.net/pdcfighting/article/details/80297499

3.今日任务：2019.1.29
    1.在pycharm中创建项目
    2.在pycharm中pymysql模块实现mysql操作 -- !!!!!
    3.搭建app组件开发整体框架 -- 在此app上搭建[熟悉整体架构]

4.今日任务：2019.1.30
    1.下载mysql服务器，设置密码为zhouliangnb12
    2.mysql 和 Navicat for MySQL 关联
    3.当navicat for mysql 报 can not connect to MySql server on '127.0.0.1'(Connection refused) -- 由于没有安装mysql服务器和启动它
        关闭mysql服务 -- sudo /usr/local/mysql/support-files/mysql.server stop
        启动mysql服务 -- sudo /usr/local/mysql/support-files/mysql.server start

    4.当navicat for mysql 报 Access denied for user 'zl'@'localhost' (using password: YES) -- 由于root未授权的问题
        终端里定位到mysql安装目录的bin目录下 -- cd usr/local/mysql/bin

    5.执行mysql -u root -p显示command not found呢？ -- 由于未配置sql环境变量
        参考：https://blog.csdn.net/qq_36004521/article/details/80637886

    6.退出不了vim  --  由于输入法不是英文状态下
        参考 https://jingyan.baidu.com/article/495ba8410ff14d38b30ede01.html

    7.通过命令 mysql -u root -p 时 报 Can't connect to local MySQL server through socket '/tmp/mysql.sock' (2) -- 由于操作不正确
        参考：https://blog.csdn.net/qq_36004521/article/details/80637886

5.今日任务：2019.1.31
    1.回顾pycharm中项目的逻辑
    2.解决29号遗留下的问题
    3.pycharm的有效期 -- 目前让其延期到4月
    4.navicat for mysql(12.1.15) 有效期 -- !!
    遇到的问题：
        1.cryptography is required for sha256_password or caching_sha2_password -- 由于pycharm设置配置mysql的用户和密码错误导致的
        2.django.db.utils.InternalError: (1049, "Unknown database 'mysite'") -- 由于在navicat for mysql中创建mysite数据库导致的
    5.python manage.py makemigratetions  和 python manage.py migrate 这两部操作的意义？？
        这两步就是创建表以及更新sql数据库中的数据

6.今日任务：2019.2.1
    1.pycharm中对数据的增删改查
    遇到的问题：
       1.之前models中创建了一个UserInfo，再新增一个Blog关联UserInfo，如何操作 -- 由于没有添加on_delete=models.CASCADE 导致的
       2.之前models中创建了一个Blog，如何修改其中的某一个字段 -- 由于userinfo_id让开发不直观
         参考：https://www.cnblogs.com/linxiyue/p/4106514.html
       3.//TODO 2019.2.1 django的模型修改以及数据迁移【 !!!!】
    2.研究python的后台程序项目 --- ！！！

    3.app组件化

    4.乐固签名在mac签名加固不出来
        问题：
        1.乐固mac -- 加固签名中  卡住 -- 不采用！！
        2.360加固助手 -- mac10.12以上版本不能启动

======================================================


















