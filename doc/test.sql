select loginname,realname,rolename from users,role where users.roleid=role.roleid;


select * from role;


select * from role,middle,menu where role.roleid=middle.roleid and menu.menuid=middle.menuid and role.roleid=4;

select * from role where roleid=4;