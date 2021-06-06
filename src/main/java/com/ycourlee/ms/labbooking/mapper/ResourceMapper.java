package com.ycourlee.ms.labbooking.mapper;

import com.ycourlee.ms.labbooking.model.entity.ResourceEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;

/**
 * @author yongjiang
 */
public interface ResourceMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ResourceEntity record);

    int insertSelective(ResourceEntity record);

    ResourceEntity selectByPrimaryKey(Integer id);

    ResourceEntity selectByPrimaryKeyEvenIfRemoved(Integer id);

    int updateByPrimaryKeySelective(ResourceEntity record);

    int updateByPrimaryKey(ResourceEntity record);

    List<ResourceEntity> listByIdCollection(@Param("idCollection") Collection<Integer> idCollection);

    int batchUpdateParentIdByIdCollection(@Param("id") Integer id, @Param("idCollection") Collection<Integer> idCollection);

    int countByIdCollection(@Param("idCollection") Collection<Integer> idCollection);

    List<Integer> listResIdByParentResId(Integer parentId);

    int removeFromResourceAndParentResource(Integer id);

    ResourceEntity selectByApiPath(String uri);

    List<ResourceEntity> listOrderedSortByDclTypeParentId(@Param("type") Integer type, @Param("parentId") Integer parentId);

    List<ResourceEntity> listApiByDclNamePathContainPathVar(@Param("name") @Nullable String name,
                                                            @Param("path") @Nullable String path,
                                                            @Param("containPathVar") @Nullable Integer containPathVar);
}