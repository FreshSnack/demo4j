create or replace procedure pro_create_user(v_username   in varchar2, --用户名
                                            v_password   in varchar2, --密码
                                            v_dropcreate in char default '0') --0：不重新创建，1:删除重新创建
authid current_user as
  --create by codemaker
  v_cnt integer;
  --datafile路径
  v_df_path varchar2(200);
begin
  --查询用户是否存在
  select count(1)
    into v_cnt
    from dba_users u
   where u.username = upper(v_username);
  dbms_output.put_line(v_cnt);
  -- 若存在，且不是删除重建则抛出异常
  if v_cnt > 0 then
    if v_dropcreate = '0' then
      raise_application_error(-20001,'user[' || v_username || '] is exist');
    else
      --删除用户
      execute immediate 'drop user ' || v_username || ' cascade';
    end if;
  end if;
  --删除表空间
  select count(1)
    into v_cnt
    from dba_tablespaces t
   where t.tablespace_name = upper(v_username || '_data');
  if v_cnt > 0 then
    execute immediate 'drop tablespace ' || v_username || '_data' ||
                      ' including contents and datafiles';
  end if;
  --删除临时表空间
  select count(1)
    into v_cnt
    from dba_tablespaces t
   where t.tablespace_name = upper(v_username || '_temp');
  if v_cnt > 0 then
    execute immediate 'drop tablespace ' || v_username || '_temp' ||
                      ' including contents and datafiles';
  end if;
  --查询数据文件存放路径
  select substr(t.file_name, 0, instr(t.file_name, '\', -1))
    into v_df_path
    from user_users u
   inner join dba_data_files t
      on u.default_tablespace = t.tablespace_name
   where rownum = 1;
  --raise_application_error(-20002,''''||v_df_path || v_username || '_data.dbf''');
  --创建表空间
  execute immediate 'create tablespace ' || v_username ||
                    '_data datafile ''' || v_df_path || v_username ||
                    '_data.dbf'' size 10m autoextend on next 10m';
  --创建临时表空间
  execute immediate 'create temporary tablespace ' || v_username ||
                    '_temp tempfile ''' || v_df_path || v_username ||
                    '_temp.dbf'' size 10m autoextend on next 10m';
  --创建用户
  execute immediate 'create user ' || v_username || ' identified by "' ||
                    v_password || '" default tablespace ' || v_username ||
                    '_data temporary tablespace ' || v_username || '_temp';
  --赋权限
  execute immediate 'grant connect,resource,dba to ' || v_username;
end;
