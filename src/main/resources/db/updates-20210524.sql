use lab_booking_db;
create table t_user
(
    id          int primary key auto_increment,
    username    varchar(50) not null default '' comment '登录账户名',
    password    varchar(50) not null default '' comment '登录密码 SHA1',
    email       varchar(50) not null default '' comment '绑定的邮箱',
    wechat      varchar(50) not null default '' comment '绑定的微信',
    phone       varchar(20) not null default '' comment '绑定的手机号',
    type        tinyint     not null default 1 comment '用户类型',
    ref_id      int         not null default 0 comment '用户详细id',
    create_time datetime    not null default current_timestamp() comment '注册时间',
    update_time datetime    not null default current_timestamp() on update current_timestamp comment '更新时间',
    is_delete   tinyint     not null default 0 comment '已删除'
) engine = InnoDB
  default charset = utf8mb4 comment 'RBAC 用户表';

create table t_role
(
    id          int primary key auto_increment,
    name        varchar(50) not null default '' comment '角色名称',
    create_time datetime    not null default current_timestamp() comment '创建时间',
    update_time datetime    not null default current_timestamp() on update current_timestamp comment '更新时间',
    is_delete   tinyint     not null default 0 comment '已删除'
) engine = InnoDB
  default charset = utf8mb4 comment 'RBAC 角色表';

create table t_resource
(
    id               int primary key auto_increment,
    type             tinyint      not null default 0 comment '类型 1.菜单 2.API',
    name             varchar(50)  not null default '' comment '资源名',
    parent_id        int          not null default 0 comment '父id',
    route            varchar(100) not null default '' comment 'page route.',
    path             varchar(250) not null default '' comment 'API path.',
    contain_path_var tinyint      not null default 0 comment '包含path variable.',
    sort             int          not null default 0 comment '排序',
    memo             varchar(50)  not null default '' comment '备注',
    create_time      datetime     not null default current_timestamp() comment '创建时间',
    update_time      datetime     not null default current_timestamp() on update current_timestamp comment '更新时间',
    is_delete        tinyint      not null default 0 comment '已删除'
) engine = InnoDB
  default charset = utf8mb4 comment 'RBAC 资源表';

create table t_user_role
(
    id          int primary key auto_increment,
    user_id     int      not null default 0 comment '用户id',
    role_id     int      not null default 0 comment '角色id',
    create_time datetime not null default current_timestamp() comment '创建时间',
    update_time datetime not null default current_timestamp() on update current_timestamp comment '更新时间',
    is_delete   tinyint  not null default 0 comment '已删除'
) engine = InnoDB
  default charset = utf8mb4 comment 'RBAC 用户角色表';

create table t_role_resource
(
    id          int primary key auto_increment,
    role_id     int      not null default 0 comment '角色id',
    resource_id int      not null default 0 comment '资源id',
    create_time datetime not null default current_timestamp() comment '创建时间',
    update_time datetime not null default current_timestamp() on update current_timestamp comment '更新时间',
    is_delete   tinyint  not null default 0 comment '已删除'
) engine = InnoDB
  default charset = utf8mb4 comment 'RBAC 角色资源表';

create table t_admin
(
    id          int primary key auto_increment,
    name        varchar(50) not null default '' comment '姓名',
    nickname    varchar(50) not null default '' comment '昵称',
    create_time datetime    not null default current_timestamp() comment '注册时间',
    update_time datetime    not null default current_timestamp() on update current_timestamp comment '更新时间',
    is_delete   tinyint     not null default 0 comment '已删除'
) engine = InnoDB
  default charset = utf8mb4 comment '用户之管理员';

create table t_teacher
(
    id          int primary key auto_increment,
    name        varchar(50) not null default '' comment '姓名',
    nickname    varchar(50) not null default '' comment '昵称',
    academy     varchar(50) not null default '' comment '学院',
    department  varchar(50) not null default '' comment '系',
    create_time datetime    not null default current_timestamp() comment '注册时间',
    update_time datetime    not null default current_timestamp() on update current_timestamp comment '更新时间',
    is_delete   tinyint     not null default 0 comment '已删除'
) engine = InnoDB
  default charset = utf8mb4 comment '用户之教师';

create table t_lab
(
    id                int primary key auto_increment,
    name              varchar(50)  not null default '' comment '实验室名称',
    max_capacity      int          not null default 0 comment '最大容纳人数',
    instrument_amount int          not null default 0 comment '仪器数量',
    instrument_memo   text comment '仪器备注',
    status            tinyint      not null default 0 comment '状态',
    location          varchar(200) not null default '' comment '实验室位置',
    open_time         varchar(5)   not null default '00:00' comment '开放时间 24小时制 02:00',
    close_time        varchar(5)   not null default '23:59' comment '关闭时间 24小时制 19:00',
    memo              text comment '备注',
    create_time       datetime     not null default current_timestamp() comment '创建时间',
    create_user_id    int          not null default 0 comment '创建人id',
    create_username   varchar(50)  not null default '' comment '创建人name',
    update_time       datetime     not null default current_timestamp() on update current_timestamp comment '更新时间',
    update_user_id    int          not null default 0 comment '更新人id',
    update_username   varchar(50)  not null default '' comment '更新人name',
    is_delete         tinyint      not null default 0 comment '已删除'
) engine = InnoDB
  default charset = utf8mb4 comment '实验室表';

create table t_course
(
    id              int primary key auto_increment,
    name            varchar(50) not null default '' comment '课程名称',
    teacher_id      int         not null default 0 comment '上课教师id',
    student_amount  int         not null default 0 comment '上课人数',
    class_hours     int         not null default 0 comment '课时数',
    memo            text comment '备注',
    create_time     datetime    not null default current_timestamp() comment '创建时间',
    create_user_id  int         not null default 0 comment '创建人id',
    create_username varchar(50) not null default '' comment '创建人name',
    update_time     datetime    not null default current_timestamp() on update current_timestamp comment '更新时间',
    update_user_id  int         not null default 0 comment '更新人id',
    update_username varchar(50) not null default '' comment '更新人name',
    is_delete       tinyint     not null default 0 comment '已删除'
) engine = InnoDB
  default charset = utf8mb4 comment '实验课程表';

create table t_booking_record
(
    id              int primary key auto_increment,
    lab_id          int         not null default 0 comment '实验室id',
    course_id       int         not null default 0 comment '实验课程id',
    memo            text comment '备注',
    create_time     datetime    not null default current_timestamp() comment '创建时间',
    create_user_id  int         not null default 0 comment '创建人id',
    create_username varchar(50) not null default '' comment '创建人name',
    update_time     datetime    not null default current_timestamp() on update current_timestamp comment '更新时间',
    update_user_id  int         not null default 0 comment '更新人id',
    update_username varchar(50) not null default '' comment '更新人name',
    is_delete       tinyint     not null default 0 comment '已删除'
) engine = InnoDB
  default charset = utf8mb4 comment '预约记录表';

create table t_booking_record_time
(
    id                int primary key auto_increment,
    booking_record_id int      not null default 0 comment '预约记录id',
    week_no           int      not null default 0 comment '周编号 1-18',
    section_no        int      not null default 0 comment '节编号 1-5',
    status            tinyint  not null default 0 comment '1.等待上课 2.已下课 3.被取消',
    create_time       datetime not null default current_timestamp() comment '创建时间',
    update_time       datetime not null default current_timestamp() on update current_timestamp comment '更新时间',
    is_delete         tinyint  not null default 0 comment '已删除'
) engine = InnoDB
  default charset = utf8mb4 comment '预约记录所选时间表';
