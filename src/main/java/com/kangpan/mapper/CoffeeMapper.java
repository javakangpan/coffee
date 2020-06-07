package com.kangpan.mapper;

import com.kangpan.model.Coffee;

import java.util.List;


public interface CoffeeMapper {
    List<Coffee> selectAll();
    List<Coffee> select(Coffee coffee);
    int insert(Coffee coffee);
    int update(Coffee coffee);
    int delete(long id);
    List<Coffee> batchSelect(List<Coffee> items);
    int batchInsert(List<Coffee> items);
    int batchDelete(long[] ids);
    int batchUpdate(List<Coffee> items);
}
