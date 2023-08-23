package com.example.tvshowsapp.data.local

import com.example.tvshowsapp.data.local.model.toTVShowDomain
import com.example.tvshowsapp.domain.FavoriteTVShowsRepository
import com.example.tvshowsapp.presentation.model.TVShow
import com.example.tvshowsapp.presentation.model.toTVShowEntity
import javax.inject.Inject

class FavoritesTVShowsRepositoryImpl @Inject constructor(
    private val favoritesTVShowsDao : TVShowsDAO
) : FavoriteTVShowsRepository {
    override suspend fun saveFavoriteTVShow(tvShow: TVShow) {
        favoritesTVShowsDao.saveTVShow(tvShow = tvShow.toTVShowEntity())
    }

    override suspend fun removeFavoriteTvShow(tvShow: TVShow) {
        favoritesTVShowsDao.deleteTVShow(tvShow = tvShow.toTVShowEntity())
    }

    override suspend fun getAllFavoriteTVShow(): List<TVShow> {
       val favoriteTVShowsEntity =  favoritesTVShowsDao.getAllFavoriteTVShow()

        return favoriteTVShowsEntity.map { it.toTVShowDomain() }
    }
}