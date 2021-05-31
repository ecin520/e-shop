# e-shop
### 面临的一些问题
#### 前端：
- 防止按钮多次点击多次提交问题
#### 后端：
- Generation异常的JSON转化问题
- 商品秒杀模块添加到项目该怎么做


|       技术       |            说明             |
| :--------------: | :-------------------------: |
|   SpringCloud    |         分布式架构          |
| Spring Security  |          权限框架           |
|     MyBatis      |           ORM框架           |
| MyBatisGenerator | mybatis逆向工程，代码生成器 |
|    Swagger-UI    |         文档生成器          |
|       SSO        |         SSO单点登录         |
|      Redis       |         分布式缓存          |
|      Docker      |         容器化部署          |
|      Druid       |        数据库连接池         |
|       JWT        |         JWT登陆支持         |
|     RabbitMQ     |          消息队列           |
|  Elasticsearch   |          搜索引擎           |

参数校验使用**Hibernate Validator**

### 前后端交互token问题

客户端拉起登陆验证，采用密码模式，正确输入账号密码后，后端返回token和刷新token，客户端每次请求都要带上token，如果访问需要登录的页面但是没有token，则进入登陆页面。如果服务端返回401，则token过期或者未验证授权，如果返回403则无权限，若返回码为401，向后端发送刷新token的请求（若存在刷新token），如果还是返回异常，则退出登录重新验证，反之则更新返回的token。

优惠券**叠加券**、**互斥券**

```sql
-- ----------------------------


-- basic数据表


-- ----------------------------

-- 认证授权中心用户表，单点登录，类似拉取支付宝账号、微信账号、qq账号授权，授权服务器的用户信息。未来会分库分表。
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `username` VARCHAR(64) UNIQUE NOT NULL,
        `password` VARCHAR(64) NOT NULL,
        `telephone` VARCHAR(64) COMMENT '电话号码',
        `avatar` VARCHAR(500) DEFAULT NULL COMMENT '头像',
        `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
        `nickname` VARCHAR(200) DEFAULT NULL COMMENT '昵称',
        `note` VARCHAR(500) DEFAULT NULL COMMENT '备注信息',
        `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
        `login_time` DATETIME DEFAULT NULL COMMENT '最后登录时间',
        `status` INT(1) DEFAULT 1 COMMENT '账户状态，1正常，0异常',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '认证授权中心用户表';

-- ----------------------------


-- 用户管理


-- ----------------------------

-- em用户表，e-mall系统不使用授权中心的用户信息，而是和em_user进行映射
DROP TABLE IF EXISTS em_user;
CREATE TABLE em_user (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `username` VARCHAR(64) UNIQUE NOT NULL,
        `password` VARCHAR(64) NOT NULL,
        `telephone` VARCHAR(64) COMMENT '电话号码',
        `avatar` VARCHAR(500) DEFAULT NULL COMMENT '头像',
        `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
        `nickname` VARCHAR(200) DEFAULT NULL COMMENT '昵称',
        `note` VARCHAR(500) DEFAULT NULL COMMENT '备注信息',
		`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
		`login_way` INT(1) COMMENT '可选登陆方式, 0 -> 默认单点登录, 1 -> 用户名登录, 2 -> 电话登录, 3 -> 邮箱登录, 4 -> 会员号登录',
        `login_time` DATETIME DEFAULT NULL COMMENT '最后登录时间',
        `status` INT(1) DEFAULT 1 COMMENT '账户状态，1正常，0异常',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT 'em用户表';


-- 商家表
DROP TABLE IF EXISTS em_merchant;
CREATE TABLE em_merchant (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `user_id` BIGINT NOT NULL COMMENT '用户id',
        `shop_id` BIGINT NOT NULL COMMENT '店铺id',
		`username` VARCHAR(64) COMMENT '用户名',
		`receive_payment` VARCHAR(1600) COMMENT '收款信息',
        `status` INT(1) COMMENT '店铺状态, 0 -> 禁用, 1 -> 正常营业, 2 -> 店铺关闭',
        `create_time` DATETIME COMMENT '开店时间',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '商家表';

-- 会员表
DROP TABLE IF EXISTS em_member;
CREATE TABLE em_member (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `user_id` BIGINT NOT NULL COMMENT '用户id',
        `member_id` VARCHAR(60) COMMENT '会员号',
		`username` VARCHAR(64) COMMENT '用户名',
        `create_time` DATETIME COMMENT '开始使用时间',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '会员表';

-- 角色表
DROP TABLE IF EXISTS `em_role`;
CREATE TABLE `em_role`(
		`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
		`name` VARCHAR(64) UNIQUE NOT NULL COMMENT '角色名称',
		`description` VARCHAR(500) DEFAULT NULL COMMENT '角色描述',
		`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
		PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET=utf8mb4 COMMENT '角色表';

-- 权限表
DROP TABLE IF EXISTS `em_permission`;
CREATE TABLE `em_permission`(
		`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
		`name` VARCHAR(64) UNIQUE NOT NULL COMMENT '权限名称',
		`description` VARCHAR(500) DEFAULT NULL COMMENT '权限描述',
		`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
		PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET=utf8mb4 COMMENT '权限表';

-- 用户角色表
DROP TABLE IF EXISTS `em_user_role`;
CREATE TABLE `em_user_role`(
		`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
		`user_id` BIGINT(20) DEFAULT NULL COMMENT '用户id',
		`role_id` BIGINT(20) DEFAULT NULL COMMENT '角色id',
		`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
		PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET=utf8mb4 COMMENT '用户角色表';

-- 角色权限表
DROP TABLE IF EXISTS `em_role_permission`;
CREATE TABLE `em_role_permission`(
		`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
		`role_id` BIGINT(20) DEFAULT NULL COMMENT '角色id',
		`permission_id` BIGINT(20) DEFAULT NULL COMMENT '权限id',
		`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
		PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';
		

-- 附加权限表
DROP TABLE IF EXISTS `em_add_permission`;
CREATE TABLE `em_add_permission`(
		`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
		`name` VARCHAR(64) UNIQUE NOT NULL COMMENT '权限名称',
		`description` VARCHAR(500) DEFAULT NULL COMMENT '权限描述',
		`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
		PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET=utf8mb4 COMMENT='附加权限表';

-- 用户附加权限表
DROP TABLE IF EXISTS `em_user_add_permission`;
CREATE TABLE `em_user_add_permission`(
		`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
		`user_id` BIGINT(20) DEFAULT NULL COMMENT '用户id',
		`add_permission_id` BIGINT(20) DEFAULT NULL COMMENT '附加权限id',
		`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
		PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET=utf8mb4 COMMENT='用户附加权限表';





-- ----------------------------


-- 商品管理


-- ----------------------------

-- 商品分类表
DROP TABLE IF EXISTS em_product_category;
CREATE TABLE em_product_category (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `name` VARCHAR ( 64 ) COMMENT '产品分类名称',
        `count` INT COMMENT '产品数量',
        `keywords` VARCHAR ( 260 ) COMMENT '关键词',
        `show_status` INT ( 1 ) COMMENT '是否显示：0 不显示 1 显示',
        `show_image` VARCHAR ( 260 ) COMMENT '显示图片或图标',
        `description` TEXT COMMENT '描述',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '商品分类表';

-- 商品分类详情表
DROP TABLE IF EXISTS em_product_category_detail;
CREATE TABLE em_product_category_detail (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `product_category_id` BIGINT NOT NULL COMMENT '产品分类id',
        `product_spec` BIGINT COMMENT '产品规格id，在选择分类后自动加载规格',
        `name` VARCHAR ( 64 ) COMMENT '分类详情名称',
        `count` INT COMMENT '产品数量',
        `keywords` VARCHAR ( 260 ) COMMENT '关键词',
        `show_status` INT ( 1 ) COMMENT '是否显示：0 不显示 1 显示',
        `show_image` VARCHAR ( 260 ) COMMENT '显示图片或图标',
        `description` TEXT COMMENT '描述',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '商品分类详情表';

-- 店铺表
DROP TABLE IF EXISTS em_shop;
CREATE TABLE em_shop (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `name` VARCHAR ( 64 ) UNIQUE COMMENT '店铺名称',
        `show_status` INT ( 1 ) COMMENT '是否显示：0 不显示 1 显示',
        `product_count` INT COMMENT '产品数量',
        `logo` VARCHAR (260) COMMENT 'logo',
        `show_image` VARCHAR ( 260 ) COMMENT '展示图片',
        `description` TEXT COMMENT '店铺描述',
        `rank` INT COMMENT '排名',
        `hot` INT COMMENT '热度',
        `collection` INT COMMENT '收藏数',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '店铺表';


-- 商品规格表
DROP TABLE IF EXISTS em_product_spec;
CREATE TABLE em_product_spec (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `name` VARCHAR ( 64 ) COMMENT '规格名称',
        `child` INT (1) COMMENT '是否存在规格详情 0 -> 不存在，1 -> 存在',
		`standard` INT(1) COMMENT '是否标准规格，标准规格拥有默认选项，否则都是商家自己添加的属性',
        `description` VARCHAR ( 260 ) COMMENT '规格描述',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '商品规格表';

-- 规格详情表
DROP TABLE IF EXISTS em_product_spec_details;
CREATE TABLE em_product_spec_details (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `product_spec_id` BIGINT NOT NULL COMMENT '商品规格id',
        `name` VARCHAR ( 64 ) COMMENT '名称',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '规格详情表';

-- spu商品表
DROP TABLE IF EXISTS em_product;
CREATE TABLE em_product (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `product_category_detail_id` BIGINT NOT NULL COMMENT '商品分类详情id',
        `shop_id` BIGINT NOT NULL COMMENT '店铺id',
        `name` VARCHAR(64) NOT NULL COMMENT '商品名称',
        `show_image` VARCHAR(2600) COMMENT '展示图片，英文逗号隔开',
        `item_no` VARCHAR(64) NOT NULL COMMENT '货号',
        `delete_status` INT (1) COMMENT '删除状态 0 -> 删除, 1 -> 未删除',
        `shelf_status` INT (1) COMMENT '上架状态 0 -> 未上架, 1 -> 上架',
        `verify_status` INT (1) COMMENT '审核状态 0 -> 未审核, 1 -> 审核通过, 2 -> 审核不通过',
        `price` DECIMAL(10,2) COMMENT '价格',
        `growth_value` INT DEFAULT 0 COMMENT '成长值',
        `integral` INT DEFAULT 0 COMMENT '赠送积分',
        `sub_title` VARCHAR(260) COMMENT '副标题',
        `description` TEXT COMMENT '商品描述',
        `unit` VARCHAR(20) COMMENT '单位',
				
        `pre_status` INT(1) DEFAULT 0 COMMENT '是否为预告商品，0 -> 否, 1 -> 是',
				`sale_time` DATETIME COMMENT '开始出售时间',
        `keywords` VARCHAR(260) COMMENT '关键字',
        `detail` TEXT COMMENT '商品详情',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT 'spu商品表';


-- sku商品表
DROP TABLE IF EXISTS em_sku_product;
CREATE TABLE em_sku_product (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `product_id` BIGINT NOT NULL COMMENT '商品id',
        `product_spec_id` BIGINT COMMENT '商品规格id',
        `product_spec_details_id` BIGINT COMMENT '商品规格信息id',
        `name` VARCHAR(64) NOT NULL COMMENT '商品名称',
        `show_image` VARCHAR(260) COMMENT '展示图片',
        `stock` INT COMMENT '库存',
        `price` DECIMAL(10,2) COMMENT '价格',
        `sale` INT COMMENT '销量',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT 'sku商品表';


-- 促销表
DROP TABLE IF EXISTS em_promotion;
CREATE TABLE em_promotion (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `product_id` BIGINT NOT NULL COMMENT '商品id',
        `promotion_price` DECIMAL(10,2) COMMENT '促销价格',
        `promotion_start_time` DATETIME COMMENT '促销开始时间',
        `promotion_end_time` DATETIME COMMENT '促销结束时间',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '促销表';;


-- 商品评论表
DROP TABLE IF EXISTS em_comment;
CREATE TABLE em_comment (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `product_id` BIGINT NOT NULL COMMENT '商品id',
        `member_id` BIGINT NOT NULL COMMENT '会员id',
        `star` INT(1) COMMENT '评分',
        `show_status` INT COMMENT '是否显示，0 -> 不显示, 1 -> 显示',
        `public` INT(1) COMMENT '可选公开或匿名, 0 -> 不公开， 1 -> 公开',
        `content` TEXT COMMENT '评论内容',
        `create_time` DATETIME COMMENT '创建时间',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '商品评论表';


-- 商品评论回复表
DROP TABLE IF EXISTS em_comment_reply;
CREATE TABLE em_comment_reply (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `member_id` BIGINT NOT NULL COMMENT '会员id',
        `comment_id` BIGINT NOT NULL COMMENT '评论id',
        `merchant` INT(1) COMMENT '是否商家回复，0 -> 否, 1 -> 是',
        `content` VARCHAR(3600) COMMENT '回复内容',
        `create_time` DATETIME COMMENT '创建时间',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '商品评论回复表';


-- 商品审核表
DROP TABLE IF EXISTS em_product_verify;
CREATE TABLE em_product_verify (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `product_id` BIGINT NOT NULL COMMENT '商品id',
        `inspector` BIGINT NOT NULL COMMENT '审核人员',
        `status` INT(1) COMMENT '审核状态, 0 -> 审核不通过， 1 -> 通过',
        `remark` VARCHAR(3600) COMMENT '备注',
        `create_time` DATETIME COMMENT '创建时间',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '商品审核表';

-- ----------------------------


-- 订单管理


-- ----------------------------

-- 订单表
-- 一次付款可能包含多个店铺的商品，每个店铺生成一个订单
DROP TABLE IF EXISTS em_order;
CREATE TABLE em_order (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `order_number` BIGINT NOT NULL COMMENT '订单号',
        `member_id` BIGINT NOT NULL COMMENT '会员id',
        `receiver_address_id` BIGINT NOT NULL COMMENT '会员收货地址id',
        `freight` DECIMAL(10,2) COMMENT '运费总额',
        `total_price` DECIMAL(10,2) COMMENT '总价',
        `pay_type` INT(1) COMMENT '支付方式， 0 -> 未支付, 1 -> 支付宝, 2 -> 微信, 3 -> 其他',
        `order_source` VARCHAR(60) COMMENT '订单来源，网页 or App',
        `status` INT(1) COMMENT '订单状态, 0 -> 待付款, 1 -> 待发货, 2 -> 已发货, 3 -> 已完成， 4 -> 已关闭, 5 -> 无效订单',
        `order_type` INT(1) COMMENT '订单类型，0 -> 普通订单， 1 -> 秒杀订单',
        `pay_time` DATETIME COMMENT '支付时间',
        `create_time` DATETIME COMMENT '创建时间',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '订单表';

-- 订单商品表
DROP TABLE IF EXISTS em_order_product;
CREATE TABLE em_order_product (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `member_id` BIGINT NOT NULL COMMENT '会员id',
        `order_id` BIGINT NOT NULL COMMENT '订单id',
        `product_id` BIGINT NOT NULL COMMENT '商品id',
        `shop_id` BIGINT NOT NULL COMMENT '店铺id',
        `sku_id` BIGINT NOT NULL COMMENT 'sku id',
        `coupon_id` BIGINT NOT NULL COMMENT '优惠券id',
        `delivery_id` BIGINT NOT NULL COMMENT '物流id',
        `num` INT NOT NULL COMMENT '购买数量',
        `note` VARCHAR(500) COMMENT '订单备注',
        `confirm_status` INT(1) COMMENT '收货状态, 0 -> 未确认, 1 -> 已确认',
        `delivery_time` DATETIME COMMENT '发货时间',
        `receive_time` DATETIME COMMENT '确认收货时间',
        `product_status` INT(1) COMMENT '商品状态, 0 -> 待付款, 1 -> 待发货, 2 -> 已发货, 3 -> 已完成， 4 -> 已关闭, 5 -> 无效订单',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '订单商品表';

-- 物流表
DROP TABLE IF EXISTS em_delivery;
CREATE TABLE em_delivery (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `order_id` BIGINT NOT NULL COMMENT '订单id',
        `shop_id` BIGINT NOT NULL COMMENT '店铺id',
        `delivery_company` VARCHAR(60) COMMENT '物流公司',
        `delivery_number` VARCHAR(60) COMMENT '物流单号',
        `delivery_status` VARCHAR(60) COMMENT '物流状态',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '物流表';

-- 订单设置表
DROP TABLE IF EXISTS em_order_setting;
CREATE TABLE em_order_setting (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `seckill_overtime` INT COMMENT '秒杀订单超时时间',
        `normal_order_overtime`INT COMMENT '正常订单超时时间',
        `confirm_overtime` INT COMMENT '订单发货后自动确认收货时间',
        `return_overtime` INT COMMENT '退货自动回款时间',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '订单设置表';



-- 购物车商品表
DROP TABLE IF EXISTS em_cart_product;
CREATE TABLE em_cart_product (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `product_id` BIGINT NOT NULL COMMENT '商品id',
        `member_id` BIGINT NOT NULL COMMENT '会员id',
        `coupon_id` BIGINT NOT NULL COMMENT '优惠券id',
        `num` INT NOT NULL COMMENT '购买数量',
        `create_time` DATETIME COMMENT '创建时间',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '购物车商品表';

-- 收货地址表
DROP TABLE IF EXISTS em_receiver_address;
CREATE TABLE em_receiver_address (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `user_id` BIGINT NOT NULL COMMENT '用户id',
        `receiver_name` VARCHAR(100) COMMENT '收货人姓名',
        `receiver_tel` VARCHAR(20) COMMENT '收货人电话',
        `receiver_postcode` VARCHAR(20) COMMENT '收货人邮编',
        `receiver_province` VARCHAR(60) COMMENT '省份',
        `receiver_city` VARCHAR(60) COMMENT '市',
        `receiver_region` VARCHAR(60) COMMENT '地区',
        `receiver_detail_address` VARCHAR(200) COMMENT '详细地址',
				`default_status` INT(1) COMMENT '是否默认收获地址, 0 -> 否, 1 -> 是',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '收货地址表';

-- 退货申请表
DROP TABLE IF EXISTS em_order_return;
CREATE TABLE em_order_return (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `member_id` BIGINT NOT NULL COMMENT '会员id',
        `order_product_id` BIGINT NOT NULL COMMENT '订单商品id',
        `handler_id` BIGINT NOT NULL COMMENT '处理人',
        `member_address_id` BIGINT NOT NULL COMMENT '会员收货地址',
        `shop_address_id` BIGINT NOT NULL COMMENT '店铺收货地址，退回地址id',
        `status` INT(1) COMMENT '退货状态, 0 -> 待处理, 1 -> 退货中, 2 -> 已退货, 3 -> 拒绝退货',
        `handle_time` DATETIME COMMENT '处理时间',
        `reason` VARCHAR(200) COMMENT '退货原因',
        `description` VARCHAR(500) COMMENT '退货描述',
        `certificate_image` VARCHAR(1000) COMMENT '退货凭证（图片，以英文,隔开）',
        `handle_note` VARCHAR(200) COMMENT '处理备注',
        `create_time` DATETIME COMMENT '创建时间',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '退货申请表';

-- 退货原因表
DROP TABLE IF EXISTS em_order_return_reason;
CREATE TABLE em_order_return_reason (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `name` VARCHAR(100) COMMENT '退货原因',
        `status` INT(1) COMMENT '显示状态, 0 -> 不显示, 1 -> 显示',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '退货原因表';



-- ----------------------------


-- 营销管理


-- ----------------------------

-- 商品优惠券表
DROP TABLE IF EXISTS em_coupon;
CREATE TABLE em_coupon (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `product_id` BIGINT COMMENT '商品id',
        `shop_id` BIGINT COMMENT '店铺id',
        `category_id` BIGINT COMMENT '分类id',
        `range` INT(1) COMMENT '适用范围, 0 -> 指定商品, 1 -> 无门槛, 2 -> 店铺商品, 3 -> 分类商品',
        `name` VARCHAR(260) NOT NULL COMMENT '优惠券名称',
        `type` INT(1) NOT NULL COMMENT '优惠券类型，0 -> 打折券, 1 -> 立减券',
        `discount` INT(10) COMMENT '折扣',
        `reduction` DECIMAL(10,2) COMMENT '立减金额',
        `start` DECIMAL(10,2) COMMENT '优惠券使用起点',
        `start_time` DATETIME COMMENT '开始时间',
        `deadline` DATETIME COMMENT '过期时间',
        `num` INT COMMENT '优惠券数量',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '商品优惠券表';

-- 优惠券拥有表
DROP TABLE IF EXISTS em_coupon_ower;
CREATE TABLE em_coupon_ower (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `coupon_id` BIGINT NOT NULL COMMENT '优惠券id',
        `member_id` BIGINT NOT NULL COMMENT '会员id',
        `code` VARCHAR(60) COMMENT '券码',
        `status` INT(1) COMMENT '使用状态, 0 -> 未使用, 1 -> 已使用, 2 -> 已失效',
        `use_time` DATETIME COMMENT '使用时间',
        `create_time` DATETIME COMMENT '创建时间',
				`get_way` VARCHAR(160) COMMENT '获取途径',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '优惠券拥有表';


-- 秒杀商品表
DROP TABLE IF EXISTS em_seckill_product;
CREATE TABLE em_seckill_product (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `product_id` BIGINT NOT NULL COMMENT '商品id',
        `seckill_price` DECIMAL(10,2) COMMENT '秒杀价',
        `stock_count` INT COMMENT '库存',
        `start_time` DATETIME COMMENT '开始时间',
        `end_time` DATETIME COMMENT '结束时间',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '秒杀商品表';


-- 首页店铺（品牌）推荐表
DROP TABLE IF EXISTS em_home_shop;
CREATE TABLE em_home_shop (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `shop_id` BIGINT NOT NULL COMMENT '店铺id',
        `shop_name` VARCHAR(60) COMMENT '店铺名称',
        `status` INT(1) COMMENT '推荐状态, 0 -> 不推荐, 1 -> 推荐',
        `power` INT COMMENT '权重',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '首页店铺（品牌）推荐表';


-- 新品推荐表
DROP TABLE IF EXISTS em_new_product_recommend;
CREATE TABLE em_new_product_recommend (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `product_id` BIGINT NOT NULL COMMENT '商品id',
        `product_name` VARCHAR(60) COMMENT '商品名称',
        `status` INT(1) COMMENT '推荐状态, 0 -> 不推荐, 1 -> 推荐',
        `power` INT COMMENT '权重',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '新品推荐表';
```


