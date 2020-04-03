# Library
软件体系结构——图书查询管理系统（基于 Interpreter 解释器风格）

基于JavaWeb，使用了解释器风格，实现了简单的图书查询管理功能。能做到基础的增删查改工作，在录入图书时使用了解释器模板，对图书的ISBN进行解析，提取其中的国家地区信息以及出版社信息，自动录入数据库，并根据输入的信息生成对应的索书号。（理论上只要国家地区和出版社的数据表够全，可以对ISBN进行离线解析。）

系统部署与运行说明

一、  项目工作环境

  1)  服务器端：
  
    ① Java环境：jdk 1.8u_241
    
    ② 服务器：Apache Tomcat 9.0.31
    
    ③ 数据库：MySQL 8.0.19
    
  2)  客户端：
  
    ④ 操作系统：Windows 10
    
    ⑤ 浏览器：Chrome / Edge Chromium

二、  项目部署

  1)  服务器部署：
  
    ① 安装配置Java环境jdk 1.8u_241
    
      a.  从官网下载对应版本jdk
      
      b.  下载完毕后，安装jdk
      
      c.  配置环境变量
      
      d.  环境变量配置完之后测试一下jdk是否安装成功
      
    ② 安装配置Apache Tomcat 9.0.31
    
      a.  进入官网，下载Tomcat的压缩包版本
      
      b.  解压到硬盘任意位置，在系统变量中添加设置环境变量
      
      c.  在控制台输入startup，在系统中启动Tomcat服务项。
      
      d.  打开浏览器，地址栏输入http://localhost:8080，如果出现tomcat主页，则表示服务器安装成功
      
      e.  在解压目录的bin文件夹中运行shutdown.bat可以关闭服务器
      
      f.  在解压目录的config文件夹中，找到server.xml文件，打开后在<Connector Port="8080" ...>中添加 URIEncoding="UTF-8" ，避免出现中文乱码问题
      
  2)数据库部署：
  
    ① 安装配置MySQL 8.0.19
    
      a.  下载Windows系统对应的MySQL Installer for Windows
      
      b.  双击安装，多数选项使用默认配置；设置服务器密码时选择传统模式（Legacy 5.x），root用户密码设置为root
      
      c.  启动服务项（默认名称MySQL80）

三、项目运行：

  1)  导入library.sql文件到服务器端数据库
  
      使用mysql命令行，输入密码root连接数据库；
    
      create database library;
    
      use database library;
    
      source ..../library.sql;  （library.sql文件的完整路径）
    
  2)  将项目导入Eclipse for JavaEE中，配置服务器环境和jdk环境
  
  3)  将项目部署到Tomcat安装目录的webapps文件夹，运行Apache Tomcat 9.0服务
  
  4)  在浏览器中键入 http://localhost:8080/Library/  进入图书查询管理系统首页开始运行项目
