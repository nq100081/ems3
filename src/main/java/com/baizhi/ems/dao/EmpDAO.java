package com.baizhi.ems.dao;

import com.baizhi.ems.entity.Emp;

import java.util.List;

public interface EmpDAO {

    //查询所有的方法
    List<Emp> findAll();

    //保存员工的方法
    void save(Emp emp);

    //删除员工的方法
    void delete(String id);

    //根据id查询员工信息
    Emp findById(String id);

    //更新员工信息
    void update(Emp emp);
}
