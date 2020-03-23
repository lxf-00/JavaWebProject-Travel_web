package com.travelInfo.service;

import com.travelInfo.domain.Favorite;

public interface FavoriteService {
    /**
     * 根据用户id，线路id，查询唯一收藏记录
     * @param uid
     * @param rid
     * @return
     */
    Favorite findByUidAndRid(int uid, int rid);

    /**
     * 添加收藏功能
     * @param uid
     * @param rid
     * @return
     */
    Boolean addFavorite(int uid, int rid);
}
