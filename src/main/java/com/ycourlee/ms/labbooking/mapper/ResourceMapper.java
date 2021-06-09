package com.ycourlee.ms.labbooking.mapper;

import com.ycourlee.ms.labbooking.model.bo.ApiSearchBO;
import com.ycourlee.ms.labbooking.model.entity.ResourceEntity;
import org.apache.ibatis.annotations.Param;

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

    ResourceEntity selectByFclApiPathMethod(@Param("uri") String uri, @Param("method") String method);

    List<ResourceEntity> listOrderedSortByDclTypeParentId(@Param("type") Integer type, @Param("parentId") Integer parentId);

    List<ResourceEntity> listApiByDclSearchBo(@Param("bo") ApiSearchBO bo);
}