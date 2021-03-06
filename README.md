# JavaWebProject-Travel_web-
One exercise : build and complete some function for one website(User Model, Goods Model.....) by Java's frame(tomcat+servlet+maven+html+....)

### 项目思路
	（实体类的创建需要实现Serializable接口，好处：
		简单和可扩展的对象的存储和恢复机制；
		远程调用，对对象编码解码便捷；
		可将对象持久化到介质中；
		对象可自定义外部存储格式）

####1， 技术选型
- 1.1 WEB层
  - a). Servlet 前端控制器
  - b). html 视图
  - c). Filter 过滤器（解决全站请求响应中文乱码）
  - b). BeanUtils 数据封装
  - c). Jackson Json 数据序列化  
- 1.2 Service层
  - a). Javamail  Java发送邮件的功能
  - b). Rdis  Nosql内存数据库
  - c). Jedis Java Redis客户端
- 1.3 DAO层
  - a). MySQL 数据库
  - b). Druid 数据库连接池
  - c). JdbcTemplate jdbc连接工具

#### 2， 准备工作：
	创建数据库-表-插入基本信息


#### 3， 用户注册页面
  - 3.1 前端
    - a). JS表单数据验证
    - b). 发送异步ajax请求("/registerUserServlet") 传递用户注册信息给后端
 - 3.2 后端
    - a). 创建必要的包：domain dao service unti web
    - b). 编写registerUserServlet（校验验证码-获取数据-封装数据-调用方法-返回应答);
    - c). 根据需要编写Service相关方法（验证用户名是否存在，保存用户)
    - d). dao 中具体实现；
    - e). 邮件激活：
      - e.1): 发送邮件 JavamailUtils工具类
      - e.2): 用户点击邮件激活(修改数据库中status)


#### 4， 用户登录页面
  - 4.1 后端
    - a). 创建LoginServlet，实现主逻辑：验证验证码-调用UserService login方法, 判断返回数据给前端
    - b). Service 创建findUserByUsernameAndPassword() 查询指定用户（全部信息），返回封装后的User对象。
    - c). dao中实现具体的方法细节
  - 4.2 前端
    - a). js表单验证
    - b). ajax发送数据，获取响应

#### 5， 网站首页页面
  - 5.1 前端
    - a). 登录后实现用户名显示(登录状态，session中有用户名数据）  /user/
    - b). 退出登录（销毁session)  /user/
    - c). 分类数据的展示

	5.2 后端
		a). 实现登录跳转后，获取session中的用户，返回给首页，显示登录状态：/getNameServlet
		b). 销毁session,退出功能: /logoutServlet
		c). CategorySerlvet 下创建 show方法，编写查询所有category信息的代码


#### 6， 优化Servlet
    减少servlet的数量，将一个功能对应一个servlet,优化为一个模块一个servlet:数据库中一张表对应一个servlet,servlet中提供不同的方法，完成用户的请求。
		HttpServlet
		BaseServlet
		UserServlet("/user/*)
		完成方法的分发
			获取请求的uri
			提取请求的方法名
			获取方法对象的Method(this - getMethod - invoke())

		UserServlet的改写.

#### 7, 对分类数据优化：
  - 7.1 数据默认按id升序排序；
  - 7.2 缓存优化： redis


#### 8. 分类详情页展示
  - 8.1 前端：
    - a). 分类详情页的展示: 传递参数,获取参数，html之间（location.search index.html）
    - b). 页码的显示，翻页，跳转首末页，
    - c). 实现页码显示前5后4，总共显示10页
    - d). 页面跳转后，回到每一页的顶部（js-windown-scrollTo())
 - 8.2 后端：
    - a). 准备工作： domain - dao - service - servlet （需要自定义PageBean对象，保存相关页面数据，供前端读取，使用）
    - b). 获取参数，调用方法，查询数据，封装为PageBean（页面数据对象），传递该对象给前端 ("select * from xxx where xxx limit x1,x2;"  x1 x2 传递Int变量）


#### 9. 搜索功能：
  - 9.1 前端
    - a). 搜索框，异步传递参数（搜索输入框的值）
    - b). 列表页（结果展示的改写， 需多传递参数： rname）
    - c). 路径的拼接
  - 9.2 后端
    - a). 改写分类详情页的代码
    - b). 处理字符串编码问题(获取rname) ( new String(strName.getBytes("iso-8859-1"), "utf-8");
    - c). 逻辑代码的重构
#### 10. 商品详情页：
  - 10.1 前端
    - a). 有list.html界面传递参数rid(线路id)到route_detail,html页面
    - b). 发起ajax异步请求：/route/detail   params={"rid":rid}
    - c). 数据填充，页面的完善
  - 10.2 后端
    - a). 代码实现： 逻辑分析->代码编写
#### 11. 旅游线路收藏：
  - 11.1 前端：
    - a). 收藏按钮按客户登录状态和收藏与否，显示不同状态；
    - b). 收藏次数动态获取
  - 11.2 后端：
    - a). 逻辑实现

#### 12. 点击按钮实现收藏功能
  - 12.1 前端
    - a). 单击按钮实现添加收藏
    - b). 传递参数： rid, 访问/route/addFavorite
  - 12.2 后端
    - a).后端代码实现

#### 13. 简单优化
