-- ----------------------------


-- urp及权限管理


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
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
		`login_time` DATETIME DEFAULT NULL COMMENT '最后登录时间',
		`status` INT(1) DEFAULT 1 COMMENT '账户状态，1正常，0异常',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '认证授权中心用户表';

-- 角色表
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`(
		`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
		`name` VARCHAR(64) UNIQUE NOT NULL COMMENT '角色名称',
		`description` VARCHAR(500) DEFAULT NULL COMMENT '角色描述',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
		PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET=utf8mb4 COMMENT '角色表';

-- 权限组
DROP TABLE IF EXISTS `sys_permission_group`;
CREATE TABLE `sys_permission_group`(
		`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
		`name` VARCHAR(64) UNIQUE NOT NULL COMMENT '权限组名称',
		`description` VARCHAR(500) DEFAULT NULL COMMENT '权限组描述',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
		PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET=utf8mb4 COMMENT '权限分类表';

-- 权限表
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`(
		`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
		`group_id` BIGINT(20) NOT NULL COMMENT '权限组id',
		`name` VARCHAR(64) UNIQUE NOT NULL COMMENT '权限名称',
		`description` VARCHAR(500) DEFAULT NULL COMMENT '权限描述',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
		PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET=utf8mb4 COMMENT '权限表';

-- 用户角色表
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`(
		`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
		`user_id` BIGINT(20) DEFAULT NULL COMMENT '用户id',
		`role_id` BIGINT(20) DEFAULT NULL COMMENT '角色id',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
		PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET=utf8mb4 COMMENT '用户角色表';

-- 角色权限表
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`(
		`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
		`role_id` BIGINT(20) DEFAULT NULL COMMENT '角色id',
		`permission_id` BIGINT(20) DEFAULT NULL COMMENT '权限id',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
		PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

-- 商家表
DROP TABLE IF EXISTS es_merchant;
CREATE TABLE es_merchant (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`user_id` BIGINT NOT NULL COMMENT '用户id',
		`shop_id` BIGINT NOT NULL COMMENT '店铺id',
		`username` VARCHAR(64) COMMENT '用户名',
		`receive_payment` VARCHAR(1600) COMMENT '收款信息',
		`status` INT(1) COMMENT '店铺状态, 0 -> 禁用, 1 -> 正常营业, 2 -> 店铺关闭',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '开店时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '商家表';

-- 会员表
DROP TABLE IF EXISTS es_member;
CREATE TABLE es_member (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`user_id` BIGINT NOT NULL COMMENT '用户id',
		`member_number` VARCHAR(60) COMMENT '会员号',
		`username` VARCHAR(64) COMMENT '用户名',
		`create_time` DATETIME COMMENT '开始使用时间',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '会员表';

-- 客服表
DROP TABLE IF EXISTS es_customer_service;
CREATE TABLE es_customer_service (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`user_id` BIGINT NOT NULL COMMENT '用户id',
		`shop_id` BIGINT NOT NULL COMMENT '店铺id',
		`username` VARCHAR(64) COMMENT '用户名',
		`satisfaction` INT(100) COMMENT '满意程度',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '客服表';

-- ----------------------------


-- 商品管理


-- ----------------------------

-- 商品分类表
DROP TABLE IF EXISTS es_product_category;
CREATE TABLE es_product_category (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`name` VARCHAR ( 64 ) UNIQUE COMMENT '产品分类名称',
		`count` INT COMMENT '产品数量',
		`keywords` VARCHAR ( 260 ) COMMENT '关键词',
		`show_status` INT ( 1 ) COMMENT '是否显示：0 不显示 1 显示',
		`show_image` VARCHAR ( 260 ) COMMENT '显示图片或图标',
		`parameter` VARCHAR ( 3600 ) COMMENT '商品参数，使用Json键值对存储',
		`description` TEXT COMMENT '描述',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '商品分类表';

-- 商品分类详情表
DROP TABLE IF EXISTS es_product_category_detail;
CREATE TABLE es_product_category_detail (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`product_category_id` BIGINT NOT NULL COMMENT '产品分类id',
		`product_spec_id` BIGINT COMMENT '产品规格id，在选择分类后自动加载规格',
		`name` VARCHAR ( 64 ) UNIQUE COMMENT '分类详情名称',
		`count` INT COMMENT '产品数量',
		`keywords` VARCHAR ( 260 ) COMMENT '关键词',
		`show_status` INT ( 1 ) COMMENT '是否显示：0 不显示 1 显示',
		`show_image` VARCHAR ( 260 ) COMMENT '显示图片或图标',
		`description` TEXT COMMENT '描述',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '商品分类详情表';

-- 店铺表
DROP TABLE IF EXISTS es_shop;
CREATE TABLE es_shop (
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
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '店铺表';

-- 商品规格表
DROP TABLE IF EXISTS es_product_spec;
CREATE TABLE es_product_spec (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`name` VARCHAR ( 64 ) COMMENT '规格名称',
		`standard` INT(1) COMMENT '是否标准规格，标准规格拥有默认选项，否则都是商家自己添加的属性',
		`description` VARCHAR ( 260 ) COMMENT '规格描述',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '商品规格表';

-- 规格详情表
DROP TABLE IF EXISTS es_product_spec_detail;
CREATE TABLE es_product_spec_detail (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`product_spec_id` BIGINT NOT NULL COMMENT '商品规格id',
		`name` VARCHAR ( 64 ) COMMENT '名称',
		`standard` INT(1) COMMENT '是否标准规格，标准规格拥有默认选项，否则都是商家自己添加的属性',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '规格详情表';


-- 分类规格表
DROP TABLE IF EXISTS es_product_category_spec;
CREATE TABLE es_product_category_spec (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`product_category_id` BIGINT NOT NULL COMMENT '商品分类id',
		`product_spec_id` BIGINT NOT NULL COMMENT '商品规格id',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '分类规格关系表';

-- spu商品表
DROP TABLE IF EXISTS es_product;
CREATE TABLE es_product (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`product_category_detail_id` BIGINT NOT NULL COMMENT '商品分类详情id',
		`shop_id` BIGINT NOT NULL COMMENT '店铺id',
		`product_category_detail_name` VARCHAR ( 64 ) COMMENT '分类详情名称',
		`shop_name` VARCHAR ( 64 ) COMMENT '店铺名称',
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
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
    PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT 'spu商品表';


-- sku商品表
DROP TABLE IF EXISTS es_sku_product;
CREATE TABLE es_sku_product (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`product_id` BIGINT NOT NULL COMMENT '商品id',
		`name` VARCHAR(64) NOT NULL COMMENT '商品名称',
		`show_image` VARCHAR(260) COMMENT '展示图片',
		`stock` INT COMMENT '库存',
		`price` DECIMAL(10,2) COMMENT '价格',
		`sale` INT COMMENT '销量',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT 'sku商品表';

-- sku与spec_detail关系表
DROP TABLE IF EXISTS es_sku_spec_detail;
CREATE TABLE es_sku_spec (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`sku_id` BIGINT NOT NULL,
		`spec_detail_id` BIGINT NOT NULL,
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT 'sku与spec关系表';


-- 促销表
DROP TABLE IF EXISTS es_promotion;
CREATE TABLE es_promotion (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`product_id` BIGINT NOT NULL COMMENT '商品id',
		`promotion_price` DECIMAL(10,2) COMMENT '促销价格',
		`promotion_start_time` DATETIME COMMENT '促销开始时间',
		`promotion_end_time` DATETIME COMMENT '促销结束时间',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '促销表';;


-- 商品评论表
DROP TABLE IF EXISTS es_comment;
CREATE TABLE es_comment (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`product_id` BIGINT NOT NULL COMMENT '商品id',
		`member_id` BIGINT NOT NULL COMMENT '会员id',
		`star` INT(1) COMMENT '评分',
		`show_status` INT COMMENT '是否显示，0 -> 不显示, 1 -> 显示',
		`public_status` INT(1) COMMENT '可选公开或匿名, 0 -> 不公开， 1 -> 公开',
		`content` TEXT COMMENT '评论内容',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '商品评论表';


-- 商品评论回复表
DROP TABLE IF EXISTS es_comment_reply;
CREATE TABLE es_comment_reply (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`member_id` BIGINT NOT NULL COMMENT '会员id',
		`comment_id` BIGINT NOT NULL COMMENT '评论id',
		`merchant` INT(1) COMMENT '是否商家回复，0 -> 否, 1 -> 是',
		`content` VARCHAR(3600) COMMENT '回复内容',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '商品评论回复表';


-- 商品审核表
DROP TABLE IF EXISTS es_product_verify;
CREATE TABLE em_product_verify (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`product_id` BIGINT NOT NULL COMMENT '商品id',
		`inspector` BIGINT NOT NULL COMMENT '审核人员',
		`status` INT(1) COMMENT '审核状态, 0 -> 审核不通过， 1 -> 通过',
		`remark` VARCHAR(3600) COMMENT '备注',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '商品审核表';

-- ----------------------------


-- 订单管理


-- ----------------------------

-- 订单表
-- 一次付款可能包含多个店铺的商品，每个店铺生成一个订单
DROP TABLE IF EXISTS es_order;
CREATE TABLE es_order (
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
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '订单表';

-- 订单商品表
DROP TABLE IF EXISTS es_order_product;
CREATE TABLE es_order_product (
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
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '订单商品表';

-- 物流表
DROP TABLE IF EXISTS es_delivery;
CREATE TABLE es_delivery (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`order_id` BIGINT NOT NULL COMMENT '订单id',
		`shop_id` BIGINT NOT NULL COMMENT '店铺id',
		`delivery_company` VARCHAR(60) COMMENT '物流公司',
		`delivery_number` VARCHAR(60) COMMENT '物流单号',
		`delivery_status` VARCHAR(60) COMMENT '物流状态',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '物流表';

-- 订单设置表
DROP TABLE IF EXISTS es_order_setting;
CREATE TABLE es_order_setting (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`seckill_overtime` INT COMMENT '秒杀订单超时时间',
		`normal_order_overtime`INT COMMENT '正常订单超时时间',
		`confirm_overtime` INT COMMENT '订单发货后自动确认收货时间',
		`return_overtime` INT COMMENT '退货自动回款时间',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '订单设置表';

-- 购物车商品表
DROP TABLE IF EXISTS es_cart_product;
CREATE TABLE es_cart_product (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`product_id` BIGINT NOT NULL COMMENT '商品id',
		`member_id` BIGINT NOT NULL COMMENT '会员id',
		`coupon_id` BIGINT NOT NULL COMMENT '优惠券id',
		`num` INT NOT NULL COMMENT '购买数量',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
        PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '购物车商品表';

-- 收货地址表
DROP TABLE IF EXISTS es_receiver_address;
CREATE TABLE es_receiver_address (
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
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '收货地址表';

-- 退货申请表
DROP TABLE IF EXISTS es_order_return;
CREATE TABLE es_order_return (
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
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '退货申请表';

-- 退货原因表
DROP TABLE IF EXISTS es_order_return_reason;
CREATE TABLE es_order_return_reason (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`name` VARCHAR(100) COMMENT '退货原因',
		`status` INT(1) COMMENT '显示状态, 0 -> 不显示, 1 -> 显示',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '退货原因表';



-- ----------------------------Q


-- 营销管理


-- ----------------------------

-- 商品优惠券表
DROP TABLE IF EXISTS es_coupon;
CREATE TABLE es_coupon (
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
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '商品优惠券表';

-- 优惠券拥有表
DROP TABLE IF EXISTS es_coupon_ower;
CREATE TABLE es_coupon_ower (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`coupon_id` BIGINT NOT NULL COMMENT '优惠券id',
		`member_id` BIGINT NOT NULL COMMENT '会员id',
		`code` VARCHAR(60) COMMENT '券码',
		`status` INT(1) COMMENT '使用状态, 0 -> 未使用, 1 -> 已使用, 2 -> 已失效',
		`use_time` DATETIME COMMENT '使用时间',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		`get_way` VARCHAR(160) COMMENT '获取途径',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '优惠券拥有表';


-- 秒杀商品表
DROP TABLE IF EXISTS es_seckill_product;
CREATE TABLE es_seckill_product (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`product_id` BIGINT NOT NULL COMMENT '商品id',
		`seckill_price` DECIMAL(10,2) COMMENT '秒杀价',
		`stock_count` INT COMMENT '库存',
		`start_time` DATETIME COMMENT '开始时间',
		`end_time` DATETIME COMMENT '结束时间',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '秒杀商品表';


-- 首页店铺（品牌）推荐表
DROP TABLE IF EXISTS es_home_shop;
CREATE TABLE es_home_shop (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`shop_id` BIGINT NOT NULL COMMENT '店铺id',
		`shop_name` VARCHAR(60) COMMENT '店铺名称',
		`status` INT(1) COMMENT '推荐状态, 0 -> 不推荐, 1 -> 推荐',
		`power` INT COMMENT '权重',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '首页店铺（品牌）推荐表';


-- 新品推荐表
DROP TABLE IF EXISTS es_new_product_recommend;
CREATE TABLE es_new_product_recommend (
		`id` BIGINT NOT NULL AUTO_INCREMENT,
		`product_id` BIGINT NOT NULL COMMENT '商品id',
		`product_name` VARCHAR(60) COMMENT '商品名称',
		`status` INT(1) COMMENT '推荐状态, 0 -> 不推荐, 1 -> 推荐',
		`power` INT COMMENT '权重',
		`update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
		`create_time` DATETIME COMMENT '创建时间',
		PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT '新品推荐表';



-- ----------------------------
-- Records of em_product
-- ----------------------------
INSERT INTO `es_product` VALUES (1000, 1006, 1000, '5G手机', '小米官方旗舰店', 'iPhoneXR', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg', '42406667143817164141601049073494356422', NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 09:06:45');
INSERT INTO `es_product` VALUES (1001, 1009, 1001, '全面屏手机', '华为', '华为手机P30', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg', '5052327879256047113713237415651291399', NULL, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 09:28:06');
INSERT INTO `es_product` VALUES (1002, 1009, 1001, '全面屏手机', '华为', '华为手机P30', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg', '5052327879256047113713237415651291399', NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 09:28:06');
INSERT INTO `es_product` VALUES (1003, 1009, 1001, '全面屏手机', '华为', '华为手机P30', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg', '5052327879256047113713237415651291399', NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 09:28:06');
INSERT INTO `es_product` VALUES (1004, 1009, 1001, '全面屏手机', '华为', '华为手机P30', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg', '5052327879256047113713237415651291399', NULL, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 09:28:06');
INSERT INTO `es_product` VALUES (1005, 1009, 1001, '全面屏手机', '华为', '华为手机P30', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg', '5052327879256047113713237415651291399', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 09:28:06');
INSERT INTO `es_product` VALUES (1006, 1006, 1000, '5G手机', '小米官方旗舰店', 'iPhoneXR', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg', '42406667143817164141601049073494356422', NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 09:06:45');
INSERT INTO `es_product` VALUES (1007, 1009, 1001, '全面屏手机', '华为', '华为手机P30', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg', '5052327879256047113713237415651291399', NULL, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 09:28:06');
INSERT INTO `es_product` VALUES (1008, 1009, 1001, '全面屏手机', '华为', '华为手机P30', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg', '5052327879256047113713237415651291399', NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 09:28:06');
INSERT INTO `es_product` VALUES (1009, 1009, 1001, '全面屏手机', '华为', '华为手机P30', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg', '5052327879256047113713237415651291399', NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 09:28:06');
INSERT INTO `es_product` VALUES (1010, 1009, 1001, '全面屏手机', '华为', '华为手机P30', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg', '5052327879256047113713237415651291399', NULL, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 09:28:06');
INSERT INTO `es_product` VALUES (1011, 1009, 1001, '全面屏手机', '华为', '华为手机P30', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg', '5052327879256047113713237415651291399', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 09:28:06');
INSERT INTO `es_product` VALUES (1012, 1006, 1000, '5G手机', '小米官方旗舰店', 'iPhoneXR', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg', '42406667143817164141601049073494356422', NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 09:06:45');
INSERT INTO `es_product` VALUES (1013, 1009, 1001, '全面屏手机', '华为', '华为手机P30', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg', '5052327879256047113713237415651291399', NULL, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 09:28:06');
INSERT INTO `es_product` VALUES (1014, 1009, 1001, '全面屏手机', '华为', '华为手机P30', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg', '5052327879256047113713237415651291399', NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 09:28:06');
INSERT INTO `es_product` VALUES (1015, 1009, 1001, '全面屏手机', '华为', '华为手机P30', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg', '5052327879256047113713237415651291399', NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 09:28:06');
INSERT INTO `es_product` VALUES (1016, 1009, 1001, '全面屏手机', '华为', '华为手机P30', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg', '5052327879256047113713237415651291399', NULL, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 09:28:06');
INSERT INTO `es_product` VALUES (1017, 1009, 1001, '全面屏手机', '华为', '华为手机P30', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg', '5052327879256047113713237415651291399', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 09:28:06');


-- ----------------------------
-- Records of es_product_category
-- ----------------------------
INSERT INTO `es_product_category` VALUES (1000, '衣服', 0, NULL, 1, NULL, '女人如衣服', NULL, '2020-09-11 06:12:14');
INSERT INTO `es_product_category` VALUES (1001, '鞋子', 0, NULL, 1, NULL, '女人如鞋子', NULL, '2020-09-11 06:12:25');
INSERT INTO `es_product_category` VALUES (1002, '手机', 0, NULL, 1, NULL, NULL, NULL, '2020-09-11 07:05:55');



-- ----------------------------
-- Records of es_product_category
-- ----------------------------
INSERT INTO `es_product_category` VALUES (1000, '衣服', 0, NULL, 1, NULL, '女人如衣服', NULL, '2020-09-11 06:12:14');
INSERT INTO `es_product_category` VALUES (1001, '鞋子', 0, NULL, 1, NULL, '女人如鞋子', NULL, '2020-09-11 06:12:25');
INSERT INTO `es_product_category` VALUES (1002, '手机', 0, NULL, 1, NULL, NULL, NULL, '2020-09-11 07:05:55');


-- ----------------------------
-- Records of es_product_category_detail
-- ----------------------------
INSERT INTO `es_product_category_detail` VALUES (1000, 1000, NULL, '上衣', NULL, NULL, 0, NULL, '上面的衣服', NULL, '2020-09-11 06:13:15');
INSERT INTO `es_product_category_detail` VALUES (1001, 1000, NULL, 'T恤', NULL, NULL, NULL, NULL, 'TTTTT', NULL, '2020-09-11 06:13:36');
INSERT INTO `es_product_category_detail` VALUES (1002, 1000, NULL, '背心', NULL, NULL, NULL, NULL, '背心心', NULL, '2020-09-11 06:13:50');
INSERT INTO `es_product_category_detail` VALUES (1003, 1001, NULL, '拖鞋', NULL, NULL, 1, NULL, NULL, '2020-09-13 04:43:56', '2020-09-11 06:29:41');
INSERT INTO `es_product_category_detail` VALUES (1004, 1001, NULL, '凉鞋', NULL, NULL, 1, NULL, NULL, '2020-09-13 04:43:59', '2020-09-11 06:29:46');
INSERT INTO `es_product_category_detail` VALUES (1005, 1001, NULL, '运动鞋', NULL, NULL, 1, NULL, NULL, '2020-09-13 04:44:01', '2020-09-11 06:30:00');
INSERT INTO `es_product_category_detail` VALUES (1006, 1002, NULL, '5G手机', NULL, NULL, 1, NULL, NULL, '2020-09-11 07:07:24', '2020-09-11 07:06:31');
INSERT INTO `es_product_category_detail` VALUES (1007, 1002, NULL, '游戏手机', NULL, NULL, 1, NULL, NULL, '2020-09-11 07:07:26', '2020-09-11 07:06:38');
INSERT INTO `es_product_category_detail` VALUES (1008, 1002, NULL, '长续航手机', NULL, NULL, 1, NULL, NULL, '2020-09-11 07:07:28', '2020-09-11 07:06:45');
INSERT INTO `es_product_category_detail` VALUES (1009, 1002, NULL, '全面屏手机', NULL, NULL, 1, NULL, NULL, '2020-09-11 07:07:30', '2020-09-11 07:06:56');
INSERT INTO `es_product_category_detail` VALUES (1010, 1002, NULL, '拍照手机', NULL, NULL, 1, NULL, NULL, '2020-09-11 07:07:35', '2020-09-11 07:07:04');
INSERT INTO `es_product_category_detail` VALUES (1011, 1002, NULL, '手机配件', NULL, NULL, NULL, NULL, NULL, NULL, '2020-09-11 07:07:17');





-- ----------------------------
-- Records of es_product_spec
-- ----------------------------
INSERT INTO `es_product_spec` VALUES (1000, '颜色', 1, 'color', '2020-09-14 09:56:03', '2020-09-11 06:28:11');
INSERT INTO `es_product_spec` VALUES (1001, '衣服尺寸', 1, NULL, '2020-09-11 06:58:52', '2020-09-11 06:43:03');
INSERT INTO `es_product_spec` VALUES (1002, '鞋子尺寸', 1, NULL, NULL, '2020-09-11 06:59:01');
INSERT INTO `es_product_spec` VALUES (1003, '规格', 1, NULL, NULL, '2020-09-11 07:02:49');



-- ----------------------------
-- Records of es_product_spec_detail
-- ----------------------------
INSERT INTO `es_product_spec_detail` VALUES (1000, 1000, '红色', '2020-09-11 06:51:13', '2020-09-11 06:51:09');
INSERT INTO `es_product_spec_detail` VALUES (1001, 1000, '黄色', '2020-09-11 06:53:28', '2020-09-11 06:51:20');
INSERT INTO `es_product_spec_detail` VALUES (1002, 1000, '紫色', '2020-09-11 06:53:26', '2020-09-11 06:51:25');
INSERT INTO `es_product_spec_detail` VALUES (1003, 1000, '蓝色', NULL, '2020-09-11 06:51:29');
INSERT INTO `es_product_spec_detail` VALUES (1004, 1000, '黑色', '2020-09-11 06:53:25', '2020-09-11 06:51:36');
INSERT INTO `es_product_spec_detail` VALUES (1005, 1000, '白色', NULL, '2020-09-11 06:51:41');
INSERT INTO `es_product_spec_detail` VALUES (1006, 1000, '灰色', NULL, '2020-09-11 06:51:48');
INSERT INTO `es_product_spec_detail` VALUES (1007, 1000, '绿色', NULL, '2020-09-11 06:51:58');
INSERT INTO `es_product_spec_detail` VALUES (1008, 1000, '屎黄色', NULL, '2020-09-11 06:52:09');
INSERT INTO `es_product_spec_detail` VALUES (1009, 1000, '肉色', NULL, '2020-09-11 06:52:15');
INSERT INTO `es_product_spec_detail` VALUES (1011, 1002, '40', NULL, '2020-09-11 06:59:12');
INSERT INTO `es_product_spec_detail` VALUES (1012, 1002, '39', NULL, '2020-09-11 06:59:16');
INSERT INTO `es_product_spec_detail` VALUES (1013, 1002, '39.5', NULL, '2020-09-11 06:59:19');
INSERT INTO `es_product_spec_detail` VALUES (1014, 1002, '40.5', NULL, '2020-09-11 06:59:23');
INSERT INTO `es_product_spec_detail` VALUES (1015, 1002, '41', NULL, '2020-09-11 06:59:28');
INSERT INTO `es_product_spec_detail` VALUES (1016, 1002, '42', NULL, '2020-09-11 06:59:30');
INSERT INTO `es_product_spec_detail` VALUES (1017, 1002, '43', NULL, '2020-09-11 06:59:33');
INSERT INTO `es_product_spec_detail` VALUES (1018, 1002, '42.5', NULL, '2020-09-11 06:59:37');
INSERT INTO `es_product_spec_detail` VALUES (1020, 1003, '自由发挥', NULL, '2020-09-11 07:03:00');
INSERT INTO `es_product_spec_detail` VALUES (1021, 1003, 'iphonexs/265G/白色', NULL, '2020-09-11 07:03:17');
INSERT INTO `es_product_spec_detail` VALUES (1022, 1003, 'HuaWeiP100/1T/奶子色', NULL, '2020-09-11 07:03:46');



-- ----------------------------
-- Records of es_shop
-- ----------------------------
INSERT INTO `es_shop` VALUES (1000, '小米官方旗舰店', 1, 0, NULL, NULL, '垃圾小米', 0, 0, 0, '2020-09-11 09:40:24', '2020-09-10 16:03:23');
INSERT INTO `es_shop` VALUES (1001, '华为', 1, 0, NULL, '2123124', '华为手机、国产之光', 0, 0, 0, '2020-09-12 05:46:40', '2020-09-12 05:46:32');


-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1000, 1001, 'PER_ADD_PRODUCT', '添加商品', '2020-09-11 17:27:24', '2020-09-11 07:11:22');



-- ----------------------------
-- Records of sys_permission_group
-- ----------------------------
INSERT INTO `sys_permission_group` VALUES (1000, '商品', '商品管理', NULL, '2020-09-11 07:10:39');
INSERT INTO `sys_permission_group` VALUES (1001, '订单', '订单管理', NULL, '2020-09-11 07:10:46');
INSERT INTO `sys_permission_group` VALUES (1002, '营销', '营销管理', NULL, '2020-09-11 07:10:59');


-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1000, 'ROLE_USER', '基础用户', NULL, '2020-08-27 11:58:35');
INSERT INTO `sys_role` VALUES (1001, 'ROLE_SYS_ADMIN', '管理员', NULL, '2020-08-27 11:58:56');
INSERT INTO `sys_role` VALUES (1006, 'ROLE_PRODUCT_ADMIN', '商品管理员', NULL, '2020-09-07 06:58:08');
INSERT INTO `sys_role` VALUES (1007, 'ROLE_ORDER_ADMIN', '订单管理员', NULL, '2020-09-07 06:58:34');
INSERT INTO `sys_role` VALUES (1009, 'ROLE_SHOP_ADMIN', '店铺管理员', NULL, '2020-09-07 07:00:42');
INSERT INTO `sys_role` VALUES (1010, 'ROLE_MEMBER_USER', '会员', NULL, '2020-09-07 07:01:24');


-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1000, 1000, 1000, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (1001, 1001, 1000, NULL, NULL);



-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1011, 'pytap', '$2a$10$EB.Ql0FjrilQecQ9oTk17upnYZJsyUPl63d9H0RMgVzCGZFtgN/DC', '15607942340', 'http://www.pytap.com/source/1590173611156.jpg', 'demoData', 'pytap', 'ecin', '2020-09-11 02:19:42', '2020-08-26 09:56:56', '2020-09-15 02:44:15', 1);
INSERT INTO `sys_user` VALUES (1015, '875631208', '$2a$10$CdWKdLixX0jbEypdH8edgel1zHBVwS9Dkz4rq/IXRN4Wymwc.3wIa', '15607942340', 'http://www.pytap.com/source/1599099983813.jpg', 'dfsas', 'David', NULL, '2020-09-11 17:17:36', '2020-08-31 07:07:07', NULL, 1);
INSERT INTO `sys_user` VALUES (1016, 'nice', '$2a$10$UUFrDSqnTxe0158HEHpm6./WBD8r7tX9C8Brjxk3K57JAXjhBjeAO', '15607942340', 'http://www.pytap.com/source/1595919448586.jpg', 'dasb阿斯頓', 'David', NULL, NULL, '2020-08-31 07:08:32', NULL, 1);
INSERT INTO `sys_user` VALUES (1027, 'ecin520', '$2a$10$VpejE3trH50T2qTZwlCpp.LvICTwAC1T.bPoLmg/LKWcTnmamjv.O', NULL, 'http://www.pytap.com/source/1593767377313.jpg', '875631208@qq.com', 'ecin', '高合金钢合金钢', '2020-09-12 08:59:26', '2020-09-07 03:48:51', '2020-09-12 08:38:00', 1);
INSERT INTO `sys_user` VALUES (1028, 'ecin', '$2a$10$r.wnuSeWs/yE6x4M6PQSQevCiKOLdgBQCiwJv5jcaBu8On1GR2zAW', NULL, 'http://www.pytap.com/source/1600012277060.jpg', NULL, NULL, NULL, '2020-09-13 15:51:19', '2020-09-11 17:25:13', NULL, 1);
INSERT INTO `sys_user` VALUES (1029, 'asdqw', '$2a$10$cCMcL65v9kcGA4rtoE/xke5H06xDfdRDLfxUv5sIpyLX.I1UDwkz6', NULL, 'http://www.pytap.com/source/1600012327934.jpg', NULL, NULL, NULL, '2020-09-13 15:52:10', '2020-09-11 17:25:18', NULL, 1);
INSERT INTO `sys_user` VALUES (1030, 'sds', '$2a$10$KCIYGkDis6N1W.TV1xkuv.6VEKbm2wnVoLzzUv6h.llKbL6Wmy/Ru', NULL, 'http://www.pytap.com/source/1600012357341.jpg', NULL, NULL, NULL, '2020-09-13 15:52:39', '2020-09-11 17:25:23', NULL, 1);
INSERT INTO `sys_user` VALUES (1033, 'ecinds', '$2a$10$JUwuNGjYEgra/C.UKhLbA.da5NOPDLiGuHrlp/7/dTGR/CL0HbGTe', NULL, 'http://www.pytap.com/source/1600012370426.jpg', NULL, NULL, NULL, '2020-09-13 15:52:52', '2020-09-11 17:25:37', NULL, 1);


-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1016, 1016, 1000, NULL, NULL);
INSERT INTO `sys_user_role` VALUES (1028, 1027, 1000, NULL, NULL);
INSERT INTO `sys_user_role` VALUES (1029, 1027, 1007, NULL, NULL);
INSERT INTO `sys_user_role` VALUES (1030, 1027, 1010, NULL, NULL);
INSERT INTO `sys_user_role` VALUES (1043, 1011, 1000, NULL, NULL);
INSERT INTO `sys_user_role` VALUES (1044, 1011, 1001, NULL, NULL);
INSERT INTO `sys_user_role` VALUES (1045, 1011, 1006, NULL, NULL);
INSERT INTO `sys_user_role` VALUES (1046, 1011, 1007, NULL, NULL);
INSERT INTO `sys_user_role` VALUES (1047, 1011, 1009, NULL, NULL);
INSERT INTO `sys_user_role` VALUES (1048, 1015, 1000, NULL, NULL);
INSERT INTO `sys_user_role` VALUES (1049, 1015, 1006, NULL, NULL);







