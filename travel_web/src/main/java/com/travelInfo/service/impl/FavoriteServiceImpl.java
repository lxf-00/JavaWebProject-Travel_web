package com.travelInfo.service.impl;

import com.travelInfo.dao.FavoriteDao;
import com.travelInfo.dao.impl.FavoriteDaoImpl;
import com.travelInfo.domain.Favorite;
import com.travelInfo.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao dao = new FavoriteDaoImpl();
    @Override
    public Favorite findByUidAndRid(int uid, int rid) {
        Favorite favorite = dao.findByUidAndRid(uid, rid);
        System.out.println("service中查询到的收藏记录为：" + favorite);
        return favorite;
    }

    @Override
    public Boolean addFavorite(int uid, int rid) {
        int count = dao.addFavorite(uid, rid);
        boolean flag = false;
        if(count == 1){
            flag = true;
        }
        return flag;
    }
}
