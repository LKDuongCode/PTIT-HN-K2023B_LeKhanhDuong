create database employees_management;
use employees_management;

create table department (
    id int primary key auto_increment,
    name varchar(255) not null unique ,
    description text,
    status enum('ACTIVE','INACTIVE')
);

create table employee (
    id int primary key auto_increment,
    name varchar(100) not null ,
    email varchar(100) not null unique ,
    phone varchar(20) not null unique ,
    avatar text,
    status enum('ACTIVE','INACTIVE'),
    created_at datetime default current_timestamp,
    department_id int not null ,
    foreign key (department_id) references department(id)
);

-- store procedure --------------------------------------------------------------
-- DEPARTMENT---------
delimiter //
create procedure sp_get_all_department ()
begin
    select id, name, description, status from department;
end //
delimiter ;

delimiter //
create procedure sp_insert_department (
    in p_name varchar(255) ,
    in p_description text
)
begin
    insert into department (name, description,status)
        values (p_name,p_description,'ACTIVE');
end //
delimiter ;


delimiter //
create procedure sp_update_department (
    in p_id int,
    in p_name varchar(255),
    in p_description text,
    in p_status enum('ACTIVE','INACTIVE')
)
begin
    update department
    set name = if(p_name is null, name, p_name),
        description = if(p_description is null, description, p_description),
        status = if(p_status is null, status, p_status)
    where id = p_id;
end //
delimiter ;



delimiter //
create procedure sp_delete_department (
    in p_id int
)
begin
    delete from department where id = p_id;
end //
delimiter ;

delimiter //
create procedure sp_search_department_by_name_like (
    in p_name varchar(255)
)
begin
    select id, name, description, status from department where name like concat('%',p_name,'%');
end //
delimiter ;


delimiter //
create procedure sp_find_department_by_name (
    in p_name varchar(255)
)
begin
    select id, name, description, status from department where name = p_name;
end //
delimiter ;


delimiter //
create procedure sp_find_department_by_id (
    in p_id int
)
begin
    select id, name, description, status from department where id = p_id;
end //
delimiter ;

delimiter //
create procedure sp_check_department_empty (
    in p_id int
)
begin
    select count(e.id) as countEmployee
    from department d
             left join employee e on d.id = e.department_id
    where d.id = p_id;
end //
delimiter ;


# insert into department ( name, description, status)
# values
#     ('demo','test','ACTIVE'),
#     ('product','test','ACTIVE');
#
# insert into employee (name, email, phone, avatar, status, department_id)
# values
#     ('duong','duong@gmail.com','0345350131','link','ACTIVE', 1),
#     ('linh','linh@gmail.com','0869257506','link','ACTIVE', 1);


-- EMPLOYEE --------------

delimiter //
create procedure sp_get_employee_page (
    in p_limit int,
    in p_offset int
)
begin
    select e.id, e.name, e.email, e.phone, e.avatar, e.status, e.created_at,
           d.name as department_name
    from employee e
             join department d on e.department_id = d.id
    order by e.created_at desc
    limit p_limit offset p_offset;
end //
delimiter ;


delimiter //
create procedure sp_get_total_employee_pages (
    in p_limit int
)
begin
    select ceil(count(*) / p_limit) as total_pages from employee;
end //
delimiter ;

create procedure sp_search_employee_page (
    in p_keyword varchar(100),
    in p_limit int,
    in p_offset int
)
begin
    select e.id, e.name, e.email, e.phone, e.avatar, e.status, e.created_at,
           d.name as department_name
    from employee e
             join department d on e.department_id = d.id
    where e.name like concat('%', p_keyword, '%')
    order by e.created_at desc
    limit p_limit offset p_offset;
end;


delimiter //
create procedure sp_get_all_employee_in_department (
    in p_id int
)
begin
    select id, name, email, phone, avatar, status, created_at
    from employee
    where department_id = p_id;
end //
delimiter ;


delimiter //
create procedure sp_insert_employee (
    in p_name varchar(100),
    in p_email varchar(100),
    in p_phone varchar(20),
    in p_avatar text,
    in p_status enum('ACTIVE','INACTIVE'),
    in p_department_id int
)
begin
    insert into employee (name, email, phone, avatar, status, department_id)
        values (p_name,p_email,p_phone,p_avatar,p_status,p_department_id);
end //
delimiter ;


delimiter //
create procedure sp_update_employee (
    in p_id int,
    in p_name varchar(100),
    in p_email varchar(100),
    in p_phone varchar(20),
    in p_avatar text,
    in p_status enum('ACTIVE','INACTIVE'),
    in p_department_id int
)
begin
    update employee
        set name = if(p_name is null,name,p_name),
            email = if(p_email is null,email,p_email),
            phone = if(p_phone is null,phone,p_phone),
            avatar = if(p_avatar is null ,avatar,p_avatar),
            status = if(p_status is null ,status,p_status),
            department_id = if(p_department_id is null,department_id,p_department_id)
    where id = p_id;
end //
delimiter ;

delimiter //
create procedure sp_delete_employee (
    in p_id int
)
begin
    delete from employee where id = p_id;
end //
delimiter ;



delimiter //
create procedure sp_find_employee_by_email (
    in p_email varchar(100)
)
begin
    select id, name, email, phone, avatar, status, created_at, department_id from employee where email = p_email;
end //
delimiter ;

delimiter //
create procedure sp_find_employee_by_phone (
    in p_phone varchar(100)
)
begin
    select id, name, email, phone, avatar, status, created_at, department_id from employee where phone = p_phone;
end //
delimiter ;

delimiter //
create procedure sp_find_employee_by_id (
    in p_id int
)
begin
    select id, name, email, phone, avatar, status, created_at, department_id from employee where id = p_id;
end //
delimiter ;


