package com.travelInfo.dao;

import com.travelInfo.domain.Favorite;

public interface FavoriteDao {
    /**
     * 根据uid, rid查询唯一收藏记录
     * @param uid
     * @param rid
     * @return
     */
    Favorite findByUidAndRid(int uid, int rid);

    /**
     * 添加收藏功能
     * @return
     */
    int addFavorite(int uid, int rid);

    /**
     * 查询收藏总数
     * @param rid
     * @return
     */
    int findTotal(int rid);
}
